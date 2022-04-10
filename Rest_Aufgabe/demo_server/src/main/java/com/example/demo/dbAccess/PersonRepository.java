package com.example.demo.dbAccess;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findPersonByNameContaining(String s);
    List<Person> findPersonBySurnameContaining(String s);
    List<Person> findPersonByPersonIdEquals(Long l);


}
