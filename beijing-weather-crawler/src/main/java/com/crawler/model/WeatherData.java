package com.crawler.model;

import java.time.LocalDate;

public class WeatherData {
    private LocalDate date;
    private String city;
    private String weather;
    private int maxTemp;
    private int minTemp;
    private String wind;
    private String source;

    public WeatherData() {}

    public WeatherData(LocalDate date, String city, String weather, int maxTemp, int minTemp, String wind, String source) {
        this.date = date;
        this.city = city;
        this.weather = weather;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.wind = wind;
        this.source = source;
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%d,%d,%s,%s",
                date, city, weather, maxTemp, minTemp, wind, source);
    }

    public static String getCSVHeader() {
        return "日期,城市,天气,最高温度(℃),最低温度(℃),风向风力,来源";
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }
    public int getMaxTemp() { return maxTemp; }
    public void setMaxTemp(int maxTemp) { this.maxTemp = maxTemp; }
    public int getMinTemp() { return minTemp; }
    public void setMinTemp(int minTemp) { this.minTemp = minTemp; }
    public String getWind() { return wind; }
    public void setWind(String wind) { this.wind = wind; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
