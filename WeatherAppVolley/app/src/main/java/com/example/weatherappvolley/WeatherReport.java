package com.example.weatherappvolley;

public class WeatherReport {

    private String last_updated;
    private Float temp_c;
    private Double temp_f;
    private Float wind_mph;
    private Double humidity;


    public WeatherReport(String last_updated, Float temp_c, Double temp_f, Float wind_mph, Double humidity) {
        this.last_updated = last_updated;
        this.temp_c = temp_c;
        this.temp_f = temp_f;
        this.wind_mph = wind_mph;
        this.humidity = humidity;
    }

    public WeatherReport() {
    }

    @Override
    public String toString() {
        return
                "last_updated= " + last_updated  + "\n"+
                " temperature in C= " + temp_c + "\n"+
                " temperature in F= " + temp_f + "\n"+
                " wind mph= " + wind_mph + "\n"+
                " humidity= " + humidity + "\n";
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(Float temp_c) {
        this.temp_c = temp_c;
    }

    public Double getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(Double temp_f) {
        this.temp_f = temp_f;
    }

    public Float getWind_mph() {
        return wind_mph;
    }

    public void setWind_mph(Float wind_mph) {
        this.wind_mph = wind_mph;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
