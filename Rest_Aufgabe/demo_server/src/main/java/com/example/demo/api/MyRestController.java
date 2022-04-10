package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.services.MyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestController {

    private MyService myService;

    public MyRestController(MyService myCustomerService) {
        this.myService = myCustomerService;
    }


    @GetMapping(path = "persons/select", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getPersons(
            @RequestParam(required = false, value = "search") String searchVal
            ) {
        return myService.findPerson(searchVal);
    }


    @PostMapping(path = "persons/create")
    public ResponseEntity<String> setCustomer(@RequestBody Person customer) {
        return myService.createPerson(customer);
    }
}
