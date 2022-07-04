package com.example.application.data.service;

import com.example.application.data.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserServiceTest {

    private final UserRepositoryTest repository;

    UserServiceTest(UserRepository repository) {
        this.repository = (UserRepositoryTest) repository;
    }

    @Test
    void registerUser() {
        //given
        User user = new User(
                "Felix","test1.","Felix","Bernardi", LocalDate.now(),"Felix.Bernardi@web.de"
        );

        //when
        long userID = repository.save(user);
        //then
        assertThat(userID != 0).isTrue();
    }
}