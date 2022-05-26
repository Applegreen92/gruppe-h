package com.example.application.data.service;

import com.example.application.data.entity.SamplePerson;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamplePersonRepository extends JpaRepository<SamplePerson, UUID> {

}