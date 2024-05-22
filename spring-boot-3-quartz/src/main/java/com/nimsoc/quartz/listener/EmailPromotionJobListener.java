package com.nimsoc.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static com.nimsoc.quartz.jobs.EmailPromotionJob.EMAIL_PROMOTION_JOB_ATTEMPTS_COUNT;

@Slf4j
public class EmailPromotionJobListener implements JobListener {
  @Override
  public String getName() {
    return "Email promotion job listener";
  }

  @Override
  public void jobToBeExecuted(JobExecutionContext context) {
    log.info("email promotion job about to be executed");
  }

  @Override
  public void jobExecutionVetoed(JobExecutionContext context) {
    log.info("email promotion job vetoed");
  }

  @Override
  public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
    if (jobException == null) { //3
      log.info("job executed successfully");
      return;
    }

    log.info("job encountered an exception: {}", jobException.getMessage());

    rescheduleEmailJob(context);
  }

  private void rescheduleEmailJob(JobExecutionContext context) {
    log.info("email promotion job threw an error, attempting to reschedule");

    try {
      Scheduler scheduler = context.getScheduler();

      JobDataMap oldJobData = context.getMergedJobDataMap();

      Integer jobAttemptCount = oldJobData.getInt(EMAIL_PROMOTION_JOB_ATTEMPTS_COUNT);

      Duration nextExecutionTime = getNextExecutionTime(jobAttemptCount);

      Date nextExecutionDateTime = Date.from(Instant.now().plusSeconds(nextExecutionTime.toSeconds()));

      JobDataMap newJobData = (JobDataMap) oldJobData.clone();
      newJobData.putAsString(EMAIL_PROMOTION_JOB_ATTEMPTS_COUNT, jobAttemptCount + 1);

      Trigger oldJobTrigger = context.getTrigger();

      Trigger newJobTrigger = TriggerBuilder.newTrigger() //9
          .forJob(context.getJobDetail())
          .withIdentity(oldJobTrigger.getKey().getName())
          .startAt(nextExecutionDateTime)
          .usingJobData(newJobData)
          .build();

      if (jobAttemptCount > 5) {
        log.info("job has run for max attempts....pausing job");

        try {
          pauseEmailPromotionJob(context, scheduler);
          log.info("job with ID: {} paused", newJobTrigger.getKey().getName());
        } catch (Exception exc) {
          log.error("failed to pause job with ID: {} due to {}", newJobTrigger.getKey().getName(), exc);
        }
        return;
      }

      scheduler.rescheduleJob(oldJobTrigger.getKey(), newJobTrigger);

      log.info("job rescheduled in duration -> {}", nextExecutionTime.getSeconds());

    } catch (Exception e) {
      log.error("failed to reschedule job due to exception: ", e);
    }
  }


  private Duration getNextExecutionTime(Integer jobAttemptCount) {
    switch (jobAttemptCount) {
      case 1: return Duration.ofSeconds(5);
      case 2: return Duration.ofSeconds(10);
      case 3: return Duration.ofSeconds(15);
      case 4: return Duration.ofSeconds(20);
      case 5: return Duration.ofSeconds(25);
      default: return Duration.ofMinutes(1);
    }
  }


  private void pauseEmailPromotionJob(JobExecutionContext context, Scheduler scheduler) throws SchedulerException {
    log.info("pausing job with key -> {}", context.getJobDetail().getKey().getName());
    scheduler.pauseJob(context.getJobDetail().getKey());

    Trigger jobTrigger = context.getTrigger();
    Trigger.TriggerState currentJobState = scheduler.getTriggerState(jobTrigger.getKey());

    log.info("job state -> {}", currentJobState.name());
    log.info("job paused -> {}", currentJobState == Trigger.TriggerState.PAUSED);
  }
}
