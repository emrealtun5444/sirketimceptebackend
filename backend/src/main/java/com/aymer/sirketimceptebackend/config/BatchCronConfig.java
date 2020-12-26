package com.aymer.sirketimceptebackend.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:41
 */
@EnableScheduling
@Configuration
public class BatchCronConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext context;

    @Scheduled(cron = "0 0/30 6-20 * * *")
    //@Scheduled(cron = "*/10 * * * * *")
    public void siparisBildirimCronConf() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        Job job = context.getBean("siparisBildirimJob", Job.class);
        JobExecution jobExecution = jobLauncher.run(job, params);
    }

}
