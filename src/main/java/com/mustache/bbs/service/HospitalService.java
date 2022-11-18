package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalDto;
import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public List<HospitalDto> findAllByDTO(Pageable pageable) {
        return hospitalRepository.findAllByDTO(pageable);
    }

    public HospitalResponse getById(Integer id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id); // Entity
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital); // DTO
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }

    public List<HospitalDto> findByRoadnameAddress(String keyword, Pageable pageable) {
        return hospitalRepository.findByRoadNameAddressContains(keyword, pageable);
    }
}
