package com.aymer.sirketimceptebackend.listener.skorkart;

import com.aymer.sirketimceptebackend.controller.stokkart.dto.StokKartDto;
import com.aymer.sirketimceptebackend.service.StokKartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 20:18
 */
@Service
public class StokKartListener {

    @Autowired
    private StokKartService stokKartService;

    @RabbitListener(queues = "stok-kart-queue")
    public void handleStokKartMessage(String stokKartJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        StokKartViewHolder stokKartDto = null;
        try {
            stokKartDto = objectMapper.readValue(stokKartJSON, StokKartViewHolder.class);
            stokKartService.syncStokKart(stokKartDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
