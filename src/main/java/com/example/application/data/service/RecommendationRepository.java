package com.example.application.data.service;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
