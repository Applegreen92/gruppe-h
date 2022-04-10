package com.example.demo.services;

import com.example.demo.model.Person;
import com.example.demo.dbAccess.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MyService {

    private final PersonRepository repository;

    public MyService(PersonRepository repo) {
        this.repository = repo;
    }

    public ResponseEntity<List<Person>> findPerson(String searchVal) {

        List<Person> personList = new ArrayList<>();

        if(searchVal == null){ //all customers
            Iterable<Person> found = repository.findAll();
            return new ResponseEntity(found, HttpStatus.OK);
        }

        try{
            long l = Long.parseLong(searchVal);
            personList.addAll(repository.findPersonByPersonIdEquals(l));
        }catch (Exception e){

        }

        for(Person p : repository.findPersonByNameContaining(searchVal) ){
            if (!personList.contains(p)) personList.add(p);
        }
        for(Person p : repository.findPersonBySurnameContaining(searchVal) ){
            if (!personList.contains(p)) personList.add(p);
        }

        return new ResponseEntity(personList, HttpStatus.OK);
    }

    public ResponseEntity<String> createPerson(Person person) {

        if(isValidPerson(person)){
            repository.save(person);
            String response = "created: "+person.toString();
            return new ResponseEntity(response, HttpStatus.OK);
        }

        String response = "could not create Person";
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    private boolean isValidPerson(Person p) {
        boolean isValid = true;
        if (p.getName().equals("")) isValid = false;
        if (p.getSurname().equals("")) isValid = false;

        return isValid;
    }
}
