package com.aymer.sirketimceptebackend.stokkart.service;

import com.aymer.sirketimceptebackend.stokkart.model.Marka;
import com.aymer.sirketimceptebackend.stokkart.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Marka> allBrandList() {
        return brandRepository.findAll();
    }

}
