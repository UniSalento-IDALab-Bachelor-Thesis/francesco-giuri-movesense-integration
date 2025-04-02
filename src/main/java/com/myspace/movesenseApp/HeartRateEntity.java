package com.myspace.movesenseApp;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "heart_rate_data")
public class HeartRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "avg_bpm")
    private double avgBpm;

    @Column(name = "date", columnDefinition = "TEXT")
    private String date;

    @Column(name = "battery_level")
    private int batteryLevel;

    @Column(name = "sensor_position")
    private String sensorPosition;

    @Column(columnDefinition = "TEXT") // Memorizzo JSON come testo
    private String chartData;

    @Column(columnDefinition = "TEXT") // Memorizzo JSON come testo
    private String chartLabels;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public HeartRateEntity() {
    }

    public HeartRateEntity(double avgBpm, String chartSnapshot, int batteryLevel, String sensorPosition) {
        this.avgBpm = avgBpm;
        this.date = chartSnapshot;
        this.batteryLevel = batteryLevel;
        this.sensorPosition = sensorPosition;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAvgBpm() {
        return avgBpm;
    }

    public void setAvgBpm(double avgBpm) {
        this.avgBpm = avgBpm;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getSensorPosition() {
        return sensorPosition;
    }

    public void setSensorPosition(String sensorPosition) {
        this.sensorPosition = sensorPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeartRateEntity)) return false;
        HeartRateEntity that = (HeartRateEntity) o;
        return id == that.id; // o id.equals(that.id) se usi Long
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
