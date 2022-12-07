package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs.domain.dto.review.ReviewResponseDTO;
import com.mustache.bbs.service.HospitalService;
import com.mustache.bbs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/hospital")
@RequiredArgsConstructor
@Controller
public class HospitalController {

    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    @GetMapping("")
    public String index(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("hospitalList", hospitalService.findAllByDTO(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }

    @GetMapping("/{id}")
    public String findHospitalReview(@PathVariable Integer id, Model model){
        HospitalResponse hospitalResponse = hospitalService.getById(id);
        List<ReviewResponseDTO> reviewList = reviewService.findByHospitalId(id);

        model.addAttribute("hospitals", hospitalResponse);
        model.addAttribute("review", reviewList);

        return "hospitals/show";
    }

    @GetMapping("/search")
    public String findHospitalByRoadAddress(@RequestParam(required = false, value="keyword", defaultValue = "") String keyword, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("hospitalList", hospitalService.findByRoadnameAddress(keyword, pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("keyword", keyword);
        return "hospitals/list";
    }
}
