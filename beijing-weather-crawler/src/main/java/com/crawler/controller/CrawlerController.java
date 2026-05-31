package com.crawler.controller;

import com.crawler.command.Command;
import com.crawler.command.CrawlCommand;
import com.crawler.exception.CrawlerException;
import com.crawler.strategy.AccuWeatherStrategy;
import com.crawler.strategy.ChinaWeatherStrategy;
import com.crawler.strategy.OpenWeatherMapStrategy;
import com.crawler.strategy.WeatherCrawlStrategy;

import java.time.LocalDate;

public class CrawlerController {

    public void crawlAllSites(String city, LocalDate startDate, LocalDate endDate) throws CrawlerException {
        WeatherCrawlStrategy chinaWeather = new ChinaWeatherStrategy();
        WeatherCrawlStrategy accuWeather = new AccuWeatherStrategy();
        WeatherCrawlStrategy openWeather = new OpenWeatherMapStrategy();

        Command cmd1 = new CrawlCommand(chinaWeather, city, startDate, endDate, "china_weather_" + city + ".csv");
        Command cmd2 = new CrawlCommand(accuWeather, city, startDate, endDate, "accu_weather_" + city + ".csv");
        Command cmd3 = new CrawlCommand(openWeather, city, startDate, endDate, "openweather_" + city + ".csv");

        cmd1.execute();
        cmd2.execute();
        cmd3.execute();
    }

    public void crawlBySite(String site, String city, LocalDate startDate, LocalDate endDate) throws CrawlerException {
        WeatherCrawlStrategy strategy = switch (site.toLowerCase()) {
            case "china" -> new ChinaWeatherStrategy();
            case "accu" -> new AccuWeatherStrategy();
            case "open" -> new OpenWeatherMapStrategy();
            default -> throw new CrawlerException("不支持的网站: " + site);
        };

        Command cmd = new CrawlCommand(strategy, city, startDate, endDate, site + "_weather_" + city + ".csv");
        cmd.execute();
    }
}
