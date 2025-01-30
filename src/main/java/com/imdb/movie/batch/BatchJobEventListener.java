package com.imdb.movie.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchJobEventListener {

    private final JobLauncher jobLauncher;
    private final Job titleBasicsImportJob;
    private final Job titleRatingsImportJob;
    private final Job titlePrincipalsImportJob;
    private final Job titleCrewImportJob;
    private final Job titleEpisodeImportJob;
    private final Job titleAkasImportJob;
    private final Job nameBasicsImportJob;
    private final TaskExecutor taskExecutorSpringBatch;

    private static final String JOB_PARAM_KEY = "startTime";

    @EventListener(ApplicationReadyEvent.class)
    public void launchJobsOnApplicationReady() {
        log.info("Starting batch jobs after the application is ready.");

        try {
            JobParameters jobParameters = createJobParameters();

            executeJobAsync(titleBasicsImportJob, jobParameters, "titleBasicsImportJob");
            executeJobAsync(titleRatingsImportJob, jobParameters, "titleRatingsImportJob");
            executeJobAsync(titlePrincipalsImportJob, jobParameters, "titlePrincipalsImportJob");
            executeJobAsync(titleCrewImportJob, jobParameters, "titleCrewImportJob");
            executeJobAsync(titleEpisodeImportJob, jobParameters, "titleEpisodeImportJob");
            executeJobAsync(titleAkasImportJob, jobParameters, "titleAkasImportJob");
            executeJobAsync(nameBasicsImportJob, jobParameters, "nameBasicsImportJob");

        } catch (Exception e) {
            log.error("Failed to start batch jobs: {}", e.getMessage(), e);
        }
    }

    private JobParameters createJobParameters() {
        return new JobParametersBuilder()
                .addLong(JOB_PARAM_KEY, System.currentTimeMillis())
                .toJobParameters();
    }

    private void executeJobAsync(Job job, JobParameters jobParameters, String jobName) {
        taskExecutorSpringBatch.execute(() -> {
            try {
                jobLauncher.run(job, jobParameters);
                log.info("Job '{}' completed successfully.", jobName);
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                     JobParametersInvalidException e) {
                log.error("Error executing job '{}': {}", jobName, e.getMessage(), e);
            }
        });
    }
}
