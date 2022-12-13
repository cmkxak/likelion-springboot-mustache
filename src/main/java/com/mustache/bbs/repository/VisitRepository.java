package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    Page<Visit> findVisitByUser(User user, Pageable pageable);
}
