package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalDto;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public List<HospitalDto> findAllByDTO(Pageable pageable){
        return hospitalRepository.findAllByDTO(pageable);
    }
}
