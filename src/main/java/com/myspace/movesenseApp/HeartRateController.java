package com.myspace.movesenseApp;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class HeartRateController {

    @Autowired
    private HeartRateService heartRateService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveHeartRateData(@RequestBody HeartRateDataDTO heartRateDataDTO) {
        //System.out.println("Received HeartRateDataDTO: " + heartRateDataDTO);

        heartRateService.saveHeartRateData(heartRateDataDTO);

        // Crea una risposta JSON
        Map<String, String> response = new HashMap<>();
        response.put("message", "Dati salvati correttamente");

        return ResponseEntity.ok(response); // Restituisci una risposta JSON
    }

    @GetMapping("/user-data")
    public ResponseEntity<List<HeartRateResponse>> getUserHeartRateData(HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail != null && !userEmail.equals("guest")) {
            // Recupera le entità di frequenza cardiaca
            List<HeartRateEntity> heartRateDataList = heartRateService.getHeartRateEntityByEmail(userEmail);

            // Mappa le entità a un oggetto di risposta
            List<HeartRateResponse> responseData = heartRateDataList.stream().map(data -> new HeartRateResponse(
                    data.getAvgBpm(),
                    data.getBatteryLevel(),
                    data.getDate(),
                    data.getSensorPosition(),
                    data.getChartData(),
                    data.getChartLabels()
            )).collect(Collectors.toList());

            return ResponseEntity.ok(responseData); // Restituisce la risposta con il nuovo tipo
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Non autorizzato se l'utente non è loggato
        }
    }


    public class HeartRateResponse {
        private Double avgBpm;
        private int batteryLevel;
        private String date;
        private String sensorPosition;
        private String chartData;
        private String chartLabels;

        // Costruttore
        public HeartRateResponse(Double avgBpm, int batteryLevel, String date, String sensorPosition, String chartData, String chartLabels) {
            this.avgBpm = avgBpm;
            this.batteryLevel = batteryLevel;
            this.date = date;
            this.sensorPosition = sensorPosition;
            this.chartData = chartData;
            this.chartLabels = chartLabels;
        }

        // Getters e Setters
        public Double getAvgBpm() {
            return avgBpm;
        }

        public void setAvgBpm(Double avgBpm) {
            this.avgBpm = avgBpm;
        }

        public int getBatteryLevel() {
            return batteryLevel;
        }

        public void setBatteryLevel(int batteryLevel) {
            this.batteryLevel = batteryLevel;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSensorPosition() {
            return sensorPosition;
        }

        public void setSensorPosition(String sensorPosition) {
            this.sensorPosition = sensorPosition;
        }

        public String getChartData() {
            return chartData;
        }

        public void setChartData(String chartData) {
            this.chartData = chartData;
        }

        public String getChartLabels() {
            return chartLabels;
        }

        public void setChartLabels(String chartLabels) {
            this.chartLabels = chartLabels;
        }
    }


}
