package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.visit.VisitCreateRequest;
import com.mustache.bbs.domain.dto.visit.VisitResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.entity.Visit;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.UserNotFoundException;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void createVisits(VisitCreateRequest visitCreateRequest, String userName){
        Hospital hospital = hospitalRepository.findById(visitCreateRequest.getHospitalId()).orElseThrow(
                () -> new RuntimeException("존재하지 않은 병원입니다"));

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException(ErrorCode.NOT_FOUND_USER.getHttpStatus(), ""));

        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(visitCreateRequest.getDisease())
                .amount(visitCreateRequest.getAmount())
                .build();

        visitRepository.save(visit);
    }

    public List<VisitResponse> findAllByPage(Pageable pageable){
        Page<Visit> visits = visitRepository.findAll(pageable);
        return visits.stream().map(Visit::toResponse).collect(Collectors.toList());
    }

    public List<VisitResponse> findVisitByUser(Long id, Pageable pageable) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(ErrorCode.NOT_FOUND_USER.getHttpStatus(), "존재하지 않는 유저입니다."));

        Page<Visit> visits = visitRepository.findVisitByUser(user,pageable);

        return visits.stream().map(Visit::toResponse).collect(Collectors.toList());
    }
}
