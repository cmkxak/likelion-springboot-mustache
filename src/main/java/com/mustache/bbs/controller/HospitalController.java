package com.mustache.bbs.controller;

import com.mustache.bbs.service.HospitalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/hospitals")
@AllArgsConstructor
@Controller
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("")
    public String index(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        model.addAttribute("hospitalList", hospitalService.findAllByDTO(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }

    //1. requestParam 활용해서, 페이징 처리가 안됨 keyword가 고정적이지 않고 계속 동적으로 변함 (뷰에서 동적으로 처리할시)
    @GetMapping("/search")
    public String findHospitalByRoadAddress(@RequestParam String keyword, Model model,@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        model.addAttribute("hospitalList", hospitalService.findByRoadnameAddress(keyword, pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("keyword", keyword);
        return "hospitals/list";
    }
}
