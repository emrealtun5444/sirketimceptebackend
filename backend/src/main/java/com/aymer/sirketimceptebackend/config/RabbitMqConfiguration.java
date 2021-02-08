package com.aymer.sirketimceptebackend.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 20:26
 */
@Configuration
public class RabbitMqConfiguration {

    @Value("${exchange.direct}")
    private String exchangeName;


    @Value("${stok.kart.rabbit.queue.name}")
    private String stokQueueName;

    @Value("${stok.kart.rabbit.routing.name}")
    private String stokRoutingName;

    @Value("${cari.kart.rabbit.queue.name}")
    private String cariQueueName;

    @Value("${cari.kart.rabbit.routing.name}")
    private String cariRoutingName;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }


    @Bean
    public Queue stokQueue() {
        return new Queue(stokQueueName);
    }

    @Bean
    public Queue cariQueue() {
        return new Queue(cariQueueName);
    }

    @Bean
    Binding bindingStokQueue() {
        return BindingBuilder.bind(stokQueue()).to(directExchange()).with(stokRoutingName);
    }

    @Bean
    Binding bindingCariQueue() {
        return BindingBuilder.bind(cariQueue()).to(directExchange()).with(cariRoutingName);
    }
}
