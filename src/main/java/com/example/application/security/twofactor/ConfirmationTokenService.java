package com.example.application.security.twofactor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);

    }

    public boolean checkConfirmationToken(String confirmToken) {
        if (confirmationTokenRepository.findAllByToken(confirmToken) == null) {
            return true;
        }
        else if (confirmationTokenRepository.findAllByToken(confirmToken).getExpiresAt().isBefore(LocalDateTime.now())) {
            return true;
        }
        else if (confirmationTokenRepository.findAllByToken(confirmToken).getConfirmedAt() == null) {
            confirmationTokenRepository.findAllByToken(confirmToken).setConfirmedAt(LocalDateTime.now());
            return  confirmationTokenRepository.findAllByToken(confirmToken) == null;
        }
        return true;
    }


}
