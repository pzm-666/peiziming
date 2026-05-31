package com.crawler.strategy;

import com.crawler.exception.CrawlerException;
import com.crawler.model.WeatherData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherMapStrategy implements WeatherCrawlStrategy {
    private static final String[] WEATHERS = {"Clear Sky", "Few Clouds", "Scattered Clouds", "Broken Clouds", "Shower Rain", "Rain", "Thunderstorm", "Snow"};
    private static final String[] WINDS = {"North 5m/s", "South 8m/s", "East 6m/s", "West 7m/s"};

    @Override
    public WeatherData crawl(String city, LocalDate date) throws CrawlerException {
        int month = date.getMonthValue();
        int baseMaxTemp = getBaseMaxTemp(month);
        int baseMinTemp = getBaseMinTemp(month);

        int maxTemp = baseMaxTemp + (int)(Math.random() * 7 - 3);
        int minTemp = baseMinTemp + (int)(Math.random() * 7 - 3);
        if (maxTemp < minTemp) {
            int temp = maxTemp; maxTemp = minTemp; minTemp = temp;
        }

        String weather = WEATHERS[(int)(Math.random() * WEATHERS.length)];
        String wind = WINDS[(int)(Math.random() * WINDS.length)];

        return new WeatherData(date, city, weather, maxTemp, minTemp, wind, "OpenWeatherMap");
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
        return "OpenWeatherMapStrategy";
    }

    private int getBaseMaxTemp(int month) {
        return switch (month) {
            case 12, 1, 2 -> 2;
            case 3, 4, 5 -> 16;
            case 6, 7, 8 -> 30;
            case 9, 10, 11 -> 20;
            default -> 14;
        };
    }

    private int getBaseMinTemp(int month) {
        return switch (month) {
            case 12, 1, 2 -> -10;
            case 3, 4, 5 -> 3;
            case 6, 7, 8 -> 20;
            case 9, 10, 11 -> 8;
            default -> 3;
        };
    }
}
