package com.aymer.sirketimceptebackend.stokkart.listener;

import com.aymer.sirketimceptebackend.stokkart.service.StokKartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<StokKartViewHolder> stokKartDtoList = null;
        try {
            stokKartDtoList = objectMapper.readValue(stokKartJSON, new TypeReference<List<StokKartViewHolder>>(){});
            // invalidate olmus stok kartlar siliniyor.
            stokKartService.updateInvalidateStokKart(stokKartDtoList);
            // stok kartlar aktarılıyor.
            stokKartDtoList.forEach(stokKartViewHolder -> stokKartService.syncStokKart(stokKartViewHolder));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
