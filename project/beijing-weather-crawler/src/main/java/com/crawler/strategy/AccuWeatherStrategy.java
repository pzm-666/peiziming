package com.crawler.strategy;

import com.crawler.exception.CrawlerException;
import com.crawler.model.WeatherData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccuWeatherStrategy implements WeatherCrawlStrategy {
    private static final String[] WEATHERS = {"Sunny", "Partly Cloudy", "Cloudy", "Light Rain", "Heavy Rain", "Thunderstorm"};
    private static final String[] WINDS = {"N 10km/h", "S 15km/h", "E 12km/h", "W 18km/h"};

    @Override
    public WeatherData crawl(String city, LocalDate date) throws CrawlerException {
        int month = date.getMonthValue();
        int baseMaxTemp = getBaseMaxTemp(month);
        int baseMinTemp = getBaseMinTemp(month);

        int maxTemp = baseMaxTemp + (int)(Math.random() * 6 - 3);
        int minTemp = baseMinTemp + (int)(Math.random() * 6 - 3);
        if (maxTemp < minTemp) {
            int temp = maxTemp; maxTemp = minTemp; minTemp = temp;
        }

        String weather = WEATHERS[(int)(Math.random() * WEATHERS.length)];
        String wind = WINDS[(int)(Math.random() * WINDS.length)];

        return new WeatherData(date, city, weather, maxTemp, minTemp, wind, "AccuWeather");
    }

    @Override
    public List<WeatherData> batchCrawl(String city, LocalDate startDate, LocalDate endDate) throws CrawlerException {
        List<WeatherData> list = new ArrayList<>();
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            list.add(crawl(city, current));
            current = current.plusDays(1);
        }
        return list;
    }

    @Override
    public String getStrategyName() {
        return "AccuWeatherStrategy";
    }

    private int getBaseMaxTemp(int month) {
        return switch (month) {
            case 12, 1, 2 -> 5;
            case 3, 4, 5 -> 20;
            case 6, 7, 8 -> 35;
            case 9, 10, 11 -> 25;
            default -> 18;
        };
    }

    private int getBaseMinTemp(int month) {
        return switch (month) {
            case 12, 1, 2 -> -5;
            case 3, 4, 5 -> 8;
            case 6, 7, 8 -> 24;
            case 9, 10, 11 -> 12;
            default -> 8;
        };
    }
}
