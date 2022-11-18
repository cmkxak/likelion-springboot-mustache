package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);
    private HospitalService hospitalService;

    @BeforeEach
    void setUp(){
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("페업, 영업중 로직 테스트")
    void businessStatusCodeTest(){
        //given
        Hospital hospital = Hospital.builder()
                .id(1)
                .hospitalName("효치과의원")
                .businessStatusCode(3)
                .build();

        Hospital openedHospital = Hospital.builder()
                .id(33)
                .hospitalName("강남한의원")
                .businessStatusCode(13)
                .build();

        //when
        Mockito.when(hospitalRepository.findById(hospital.getId()))
                .thenReturn(Optional.of(hospital));

        Mockito.when(hospitalRepository.findById(openedHospital.getId()))
                .thenReturn(Optional.of(openedHospital));

        HospitalResponse hospitalResponse = hospitalService.getById(hospital.getId());
        HospitalResponse openedhospitalResponse = hospitalService.getById(openedHospital.getId());

        //then
        Assertions.assertEquals("폐업", hospitalResponse.getBusinessStatusName());
        Assertions.assertEquals("영업중", openedhospitalResponse.getBusinessStatusName());
    }
}