package com.emailservice.entity.repository;

import com.emailservice.entity.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailConfirmation, Integer> {

    Optional<EmailConfirmation> findByConfirmationToken(String confirmationToken);
}
