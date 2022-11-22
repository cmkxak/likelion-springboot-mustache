package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.domain.dto.ReviewResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<ReviewResponseDTO> findByHospitalId(Integer id);
}
