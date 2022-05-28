package com.example.application.data.service;

import com.example.application.data.entity.MoviePersonPartLink;
import com.example.application.data.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePersonPartLinkRepository extends JpaRepository<MoviePersonPartLink, Integer> {
}
