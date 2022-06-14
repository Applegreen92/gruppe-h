package com.example.application.data.service;

import com.example.application.data.entity.Bewertung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewertungsRepository extends JpaRepository<Bewertung,Integer> {

}
