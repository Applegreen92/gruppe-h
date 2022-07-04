package com.example.application.data.service;

import com.example.application.data.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void findByUsername() {
        User user = new User(
                "Felix","test1.","Felix","Bernardi", LocalDate.now(),"Felix.Bernardi@web.de"
        );
        underTest.save(user);
        //when
        underTest.findByUsername(user.getUsername());
        boolean expected = underTest.findByUsername(user.getUsername()) == (user);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void findById() {
    }

    @Test
    void search() {
    }

    public long save(User user) {
        underTest.save(user);
        return user.getId();
    }
}