package com.myspace.movesenseApp;

import org.apache.catalina.User;

public class HeartRateDataDTO {
    private double avgBpm;  // BPM medio
    private String date;
    private int batteryLevel;  // Livello della batteria
    private String sensorPosition;  // Posizione del sensore
    private Long user;  // ID dell'utente (foreign key)
    private String chartData;
    private String chartLabels;

    // Getters and Setters
    public double getAvgBpm() {
        return avgBpm;
    }

    public void setAvgBpm(double avgBpm) {
        this.avgBpm = avgBpm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "HeartRateDataDTO{" +
                "avgBpm=" + avgBpm +
                ", date='" + date + '\'' +
                ", batteryLevel=" + batteryLevel +
                ", sensorPosition='" + sensorPosition + '\'' +
                ", user=" + user +
                '}';
    }
}
