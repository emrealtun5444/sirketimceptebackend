package com.aymer.sirketimceptebackend.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:41
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private ApplicationContext context;

    @Bean
    public Step siparisBildirimStep(){
        Tasklet service = context.getBean("siparisBildirimService", Tasklet.class);
        return steps.get("siparisBildirimStep")
                .tasklet(service)
                .build();
    }

    @Bean(name = "siparisBildirimJob")
    public Job siparisBildirimJob(){
        return jobs.get("siparisBildirimJob")
                .incrementer(new RunIdIncrementer())
                .start(siparisBildirimStep())
                .build();
    }

}
