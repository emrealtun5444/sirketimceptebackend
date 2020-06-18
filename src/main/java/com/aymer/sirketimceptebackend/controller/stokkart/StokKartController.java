package com.aymer.sirketimceptebackend.controller.stokkart;

import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.controller.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.controller.stokkart.mapper.StokKartMapper;
import com.aymer.sirketimceptebackend.model.StokKart;
import com.aymer.sirketimceptebackend.controller.stokkart.dto.StokKartDto;
import com.aymer.sirketimceptebackend.repository.StokKartRepository;
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
@RequestMapping("/api/stokKart")
public class StokKartController {

    private StokKartRepository stokKartRepository;
    private StokKartMapper stokKartMapper;

    @Autowired
    public StokKartController(StokKartRepository stokKartRepository, StokKartMapper stokKartMapper) {
        this.stokKartRepository = stokKartRepository;
        this.stokKartMapper = stokKartMapper;
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> sorgula(@Valid @RequestBody StokKartSorguKriteri stokKartSorguKriteri) {
        List<StokKart> stokKarts = stokKartRepository.findAll();
        return ResponseEntity.ok(new AppResponse(stokKartMapper.stokkartToDtoList(stokKarts)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> stokKartById(@Valid @PathVariable(name = "id") Long stokKartId) {
        Optional<StokKart> stokKart = stokKartRepository.findById(stokKartId);
        StokKartDto stokKartDto = stokKartMapper.stokkartToDto(stokKart.get());
        return ResponseEntity.ok(new AppResponse(stokKartDto));
    }

    @GetMapping("/stokKarts")
    public ResponseEntity<?> allStokKarts() {
        List<StokKart> stokKarts = stokKartRepository.findAll();
        return ResponseEntity.ok(new AppResponse(stokKartMapper.stokkartToDtoList(stokKarts)));
    }

}
