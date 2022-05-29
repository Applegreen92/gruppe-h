package com.example.application.security.twofactor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

   // ConfirmationToken findConfirmationTokenByToken(String token);

    ConfirmationToken findAllByToken(String token);
}

