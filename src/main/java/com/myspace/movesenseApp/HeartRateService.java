package com.myspace.movesenseApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeartRateService {

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Autowired
    private UserRepository userRepository;

    public List<HeartRateEntity> getHeartRateEntityByEmail(String email) {
        return heartRateRepository.findByUserEmail(email);
    }

    public void saveHeartRateData(HeartRateDataDTO heartRateDataDTO) {

        // Verifica se l'ID utente è nullo
        if (heartRateDataDTO.getUser() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Converte DTO in Entity
        HeartRateEntity heartRateEntity = new HeartRateEntity();
        heartRateEntity.setAvgBpm(heartRateDataDTO.getAvgBpm());
        heartRateEntity.setDate(heartRateDataDTO.getDate());
        heartRateEntity.setBatteryLevel(heartRateDataDTO.getBatteryLevel());
        heartRateEntity.setSensorPosition(heartRateDataDTO.getSensorPosition());
        heartRateEntity.setChartData(heartRateDataDTO.getChartData());
        heartRateEntity.setChartLabels(heartRateDataDTO.getChartLabels());

        // Recupera l'utente dall'ID fornito nel DTO
        UserEntity user = userRepository.findById(heartRateDataDTO.getUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        heartRateEntity.setUser(user);

        // Salvataggio su database
        heartRateRepository.save(heartRateEntity);
    }
}
