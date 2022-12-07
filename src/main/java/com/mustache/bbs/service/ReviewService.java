package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.review.ReviewResponseDTO;
import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDTO> findByHospitalId(Integer id) {
        return reviewRepository.findByHospitalId(id);
    }
}
