package com.aymer.sirketimceptebackend.marketplace.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.marketplace.dto.MarketPlaceConfDto;
import com.aymer.sirketimceptebackend.marketplace.mapper.MarketPlaceMapper;
import com.aymer.sirketimceptebackend.marketplace.model.MarketPlaceSettings;
import com.aymer.sirketimceptebackend.marketplace.repository.MarketPlaceRepository;
import com.aymer.sirketimceptebackend.marketplace.service.MarketPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/market-place")
public class MarketPlaceController {

    private MarketPlaceRepository marketPlaceRepository;
    private MarketPlaceService marketPlaceService;
    private MarketPlaceMapper marketPlaceMapper;

    @Autowired
    public MarketPlaceController(MarketPlaceRepository marketPlaceRepository, MarketPlaceService marketPlaceService, MarketPlaceMapper marketPlaceMapper) {
        this.marketPlaceRepository = marketPlaceRepository;
        this.marketPlaceService = marketPlaceService;
        this.marketPlaceMapper = marketPlaceMapper;
    }


    @GetMapping("/loadMarketPlace/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> loadMarketPlace(@Valid @PathVariable(name = "id") Long marketPlaceConfId) {
        Optional<MarketPlaceSettings> marketPlaceSettings = marketPlaceRepository.findById(marketPlaceConfId);
        MarketPlaceConfDto marketPlaceConfDto = marketPlaceMapper.marketPlaceConfToDto(marketPlaceSettings.get());
        return ResponseEntity.ok(new AppResponse(marketPlaceConfDto));
    }

    @GetMapping("/loadMarketPlaces")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> loadMarketPlaces() {
        List<MarketPlaceSettings> marketPlaceSettings = marketPlaceRepository.findAll();
        return ResponseEntity.ok(new AppResponse(marketPlaceMapper.marketPlaceConfToDtoList(marketPlaceSettings)));
    }

}
