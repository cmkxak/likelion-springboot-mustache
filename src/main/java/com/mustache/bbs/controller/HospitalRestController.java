package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/hospitals")
@RequiredArgsConstructor
@RestController
public class HospitalRestController {

    private final HospitalService hospitalService;

    @GetMapping(value="/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id){
        HospitalResponse hospitalResponse = hospitalService.getById(id);
        return ResponseEntity.ok().body(hospitalResponse);
    }
}
