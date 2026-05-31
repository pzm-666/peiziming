package com.crawler.strategy;

import com.crawler.exception.CrawlerException;
import com.crawler.model.WeatherData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChinaWeatherStrategy implements WeatherCrawlStrategy {
    private static final String[] WEATHERS = {"晴", "多云", "阴", "小雨", "中雨", "雷阵雨"};
    private static final String[] WINDS = {"北风2级", "南风3级", "东风2级", "西风3级"};

    @Override
    public WeatherData crawl(String city, LocalDate date) throws CrawlerException {
        int month = date.getMonthValue();
        int baseMaxTemp = getBaseMaxTemp(month);
        int baseMinTemp = getBaseMinTemp(month);

        int maxTemp = baseMaxTemp + (int)(Math.random() * 8 - 4);
        int minTemp = baseMinTemp + (int)(Math.random() * 8 - 4);
        if (maxTemp < minTemp) {
            int temp = maxTemp; maxTemp = minTemp; minTemp = temp;
        }

        String weather = WEATHERS[(int)(Math.random() * WEATHERS.length)];
        String wind = WINDS[(int)(Math.random() * WINDS.length)];

        return new WeatherData(date, city, weather, maxTemp, minTemp, wind, "中国天气网");
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
        return "ChinaWeatherStrategy";
    }

    private int getBaseMaxTemp(int month) {
        return switch (month) {
            case 12, 1, 2 -> 3;
            case 3, 4, 5 -> 18;
            case 6, 7, 8 -> 32;
            case 9, 10, 11 -> 22;
            default -> 15;
        };
    }

    private int getBaseMinTemp(int month) {
        return switch (month) {
            case 12, 1, 2 -> -8;
            case 3, 4, 5 -> 5;
            case 6, 7, 8 -> 22;
            case 9, 10, 11 -> 10;
            default -> 5;
        };
    }
}
