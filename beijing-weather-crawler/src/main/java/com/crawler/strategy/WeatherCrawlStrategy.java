package com.crawler.strategy;

import com.crawler.exception.CrawlerException;
import com.crawler.model.WeatherData;

import java.time.LocalDate;
import java.util.List;

public interface WeatherCrawlStrategy {
    WeatherData crawl(String city, LocalDate date) throws CrawlerException;
    List<WeatherData> batchCrawl(String city, LocalDate startDate, LocalDate endDate) throws CrawlerException;
    String getStrategyName();
}
