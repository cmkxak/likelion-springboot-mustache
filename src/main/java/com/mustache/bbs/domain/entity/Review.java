package com.mustache.bbs.domain.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    private Long id;
    private String title;
    private String contents;
    private String username;

    @ManyToOne(targetEntity = Hospital.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
