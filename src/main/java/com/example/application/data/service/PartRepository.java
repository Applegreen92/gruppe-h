package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
    List<Part> findAllByPartID(int partID);
    List<Part> findAllByPartName(String partName);
}
