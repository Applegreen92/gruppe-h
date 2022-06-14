package com.example.application.data.service;

import org.springframework.beans.factory.annotation.Autowired;

public class BewertungService {

    @Autowired
    public BewertungService(BewertungsRepository bewertungsRepository) {
        this.bewertungsRepository = bewertungsRepository;
    }

    private final BewertungsRepository bewertungsRepository;



}
