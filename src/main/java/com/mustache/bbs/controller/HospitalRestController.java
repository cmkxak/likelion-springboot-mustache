package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RequestMapping("api/v1/hospitals")
@RequiredArgsConstructor
@RestController
public class HospitalRestController {

    private final HospitalService hospitalService;

    @GetMapping(value="/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id){
        Optional<Hospital> hospital = hospitalService.findById(id);
        return ResponseEntity.ok().body(Hospital.of(hospital.get()));
    }
}
