package com.myspace.movesenseApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRateRepository extends JpaRepository<HeartRateEntity, Long> {
    List<HeartRateEntity> findByUserEmail(String email);
}
