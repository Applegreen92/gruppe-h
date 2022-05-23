package com.example.application.data.service;

import com.example.application.data.entity.Part;
import com.example.application.data.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    private final PartRepository partRepository;
    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<Part> getPart(){
        return partRepository.findAll();
    }

    public Part update(Part entity) {
        return partRepository.save(entity);
    }

    public void delete(int partID) {
        partRepository.deleteById(partID);
    }

    public void addNewPart(Part part) {partRepository.save(part);}
}
