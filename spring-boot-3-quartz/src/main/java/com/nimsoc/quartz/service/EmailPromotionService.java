package com.nimsoc.quartz.service;

import com.nimsoc.quartz.jobs.EmailPromotionJob;
import com.nimsoc.quartz.objects.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.nimsoc.quartz.jobs.EmailPromotionJob.*;

@Slf4j
@Service
public class EmailPromotionService {



  private final Scheduler quartzScheduler;

  private List<Recipient> recipients = Arrays.asList(
      new Recipient("1", "John Smith", "johnSmith@exampleEmail.com"),
      new Recipient("2", "Sarah Connor", "sarahConnor@exampleEmail.com"),
      new Recipient("3", "Mario Appleseed", "marioAppleseed@exampleEmail.com"),
      new Recipient("4", "Anthony Taylor", "anthonytaylor@exampleEmail.com"),
      new Recipient("5", "John Reese", "johnReese@exampleEmail.com")
  );

  public EmailPromotionService(Scheduler quartzScheduler) {
    this.quartzScheduler = quartzScheduler;
  }

  public void scheduleEmailJobs() {
    recipients.forEach(
        recipient -> createAndTriggerEmailJobToRecipient(recipient)
    );
  }

  private void createAndTriggerEmailJobToRecipient(Recipient recipient) {

    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put(RECIPIENT_ID, recipient.getUserId());
    jobDataMap.put(RECIPIENT_NAME_KEY, recipient.getName());
    jobDataMap.put(RECIPIENT_EMAIL_ADDRESS_KEY, recipient.getEmailAddress());
    jobDataMap.putAsString(EMAIL_PROMOTION_JOB_ATTEMPTS_COUNT, 1);

    JobDetail job = JobBuilder.newJob(EmailPromotionJob.class)
        .withIdentity("" + recipient.getUserId())
        .usingJobData(jobDataMap)
        .requestRecovery(true)
        .storeDurably(true)
        .withDescription("Send promotional email to " + recipient.getName())
        .build();


    Date jobStartTime = Date.from(Instant.now().plus(1, ChronoUnit.MINUTES));


    long scheduledFutureTime = jobStartTime.getTime() - (new Date()).getTime();

    log.info("scheduling job to start at -> {} minutes from now", TimeUnit.MILLISECONDS.toMinutes(scheduledFutureTime));

    Trigger jobTrigger = TriggerBuilder
        .newTrigger()
        .withIdentity("Trigger for Promotional email to ", recipient.getName())
        .forJob(job)
        .startAt(jobStartTime)
        .build();

    try {
      quartzScheduler.scheduleJob(job, jobTrigger);
      quartzScheduler.start();
    } catch (Exception e) {
      log.error("error scheduling and start ", e);
      throw new RuntimeException(e);
    }
  }

}
