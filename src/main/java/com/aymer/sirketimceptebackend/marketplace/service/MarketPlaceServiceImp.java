package com.aymer.sirketimceptebackend.marketplace.service;

import com.aymer.sirketimceptebackend.marketplace.repository.MarketPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class MarketPlaceServiceImp implements MarketPlaceService {

    @Autowired
    private MarketPlaceRepository marketPlaceRepository;


}
