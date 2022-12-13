package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.visit.VisitCreateRequest;
import com.mustache.bbs.domain.dto.visit.VisitResponse;
import com.mustache.bbs.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
@RestController
public class VisitApiController {

    private final VisitService visitService;

    @PostMapping("")
    public ResponseEntity<String> createVisits(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication){
        log.info("authenticated User name" + authentication.getName());
        visitService.createVisits(visitCreateRequest, authentication.getName());
        return ResponseEntity.ok().body(authentication.getName() + "님의 방문 기록이 등록되었습니다.");
    }

    @GetMapping("")
    public ResponseEntity<List<VisitResponse>> findAllVisits(Pageable pageable){
        return ResponseEntity.ok().body(visitService.findAllByPage(pageable));
    }

    //특정 user의 기록 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<List<VisitResponse>> findAllVisitsByUser(@PathVariable Long id, Pageable pageable){
        return ResponseEntity.ok().body(visitService.findVisitByUser(id, pageable));
    }
}
