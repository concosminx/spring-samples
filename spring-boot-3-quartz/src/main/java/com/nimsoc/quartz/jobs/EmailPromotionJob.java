package com.nimsoc.quartz.jobs;

import com.nimsoc.quartz.listener.EmailPromotionJobListener;
import com.nimsoc.quartz.objects.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Optional;

@Slf4j
public class EmailPromotionJob implements Job {

  public static final String RECIPIENT_NAME_KEY = "recipient_name";
  public static final String RECIPIENT_EMAIL_ADDRESS_KEY = "recipient_email_Address";
  public static final String RECIPIENT_ID = "recipient_id";
  public static final String EMAIL_PROMOTION_JOB_ATTEMPTS_COUNT = "retry_number";

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
      JobDataMap jobDataMap = context.getMergedJobDataMap();
      if (jobDataMap == null) {
        log.error("jobData not found");
        return;
      }

      String recipientName = jobDataMap.getString(RECIPIENT_NAME_KEY);
      String recipientEmailAddress = jobDataMap.getString(RECIPIENT_EMAIL_ADDRESS_KEY);
      String recipientId = jobDataMap.getString(RECIPIENT_ID);

      //NPE ...
      if (recipientName.isEmpty() || recipientEmailAddress.isEmpty() || recipientId.isEmpty()) {
        log.error("recipient details not found. exiting....");
        return;
      }


      Recipient recipient = new Recipient(recipientId, recipientName, recipientEmailAddress);

      addJobListener(recipientId, context.getScheduler());

      sendEmailsToRecipient(recipient);

      removeJobListener(context.getScheduler(), recipientId);

    } catch (Exception exception) {
      log.error("exception executing email promotion job ", exception);
      JobExecutionException jobExecutionException = new JobExecutionException(exception);
      //jobExecutionException.setRefireImmediately(true);
      throw jobExecutionException;
    }

  }

  private void sendEmailsToRecipient(Recipient recipient) {
    if (1 > 0) {
      throw new IllegalArgumentException("Bad email address format");
    }

    // logic to send out email
    log.info("sending promotional email to -> {}", recipient.getEmailAddress());
  }

  private void addJobListener(String jobId, Scheduler scheduler) throws SchedulerException {
    Matcher<JobKey> jobMatcher = new Matcher<>() {
      @Override
      public boolean isMatch(JobKey key) {
        return key.getName().equals(jobId);
      }
    };

    scheduler.getListenerManager().addJobListener(
        new EmailPromotionJobListener(),
        jobMatcher
    );
  }

  private void removeJobListener(Scheduler scheduler, String jobId) throws SchedulerException {
    scheduler.getListenerManager().removeJobListener(jobId);

    Optional<JobListener> jobListenerRemoved = scheduler.getListenerManager().getJobListeners().stream().filter( x-> x.getName().equals(jobId)).findFirst();

    log.info("job listener removed -> {}", jobListenerRemoved);
  }
}
