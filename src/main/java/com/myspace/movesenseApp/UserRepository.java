package com.myspace.movesenseApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Trova un utente dall'email
    UserEntity findByEmail(String email);
}
