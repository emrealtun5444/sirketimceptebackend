package com.aymer.sirketimceptebackend.cariKart.listener;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.cariKart.service.CariKartService;
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
public class  CariKartListener {

    @Autowired
    private CariKartService cariKartService;

    @RabbitListener(queues = "cari-kart-queue")
    public void handleCariKartMessage(String stokKartJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        CariKartViewHolder cariKartViewHolder = null;
        try {
            cariKartViewHolder = objectMapper.readValue(stokKartJSON, CariKartViewHolder.class);
            cariKartService.syncCariKart(cariKartViewHolder);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
