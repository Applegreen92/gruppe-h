package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    public Person update(Person entity) {
        return personRepository.save(entity);
    }

    public void delete(int personID) {
        personRepository.deleteById(personID);
    }

    public void addNewPerson(Person person) {personRepository.save(person);}
}
