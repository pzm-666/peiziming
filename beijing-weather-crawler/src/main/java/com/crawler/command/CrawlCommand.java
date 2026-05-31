package com.crawler.command;

import com.crawler.exception.CrawlerException;
import com.crawler.model.WeatherData;
import com.crawler.strategy.WeatherCrawlStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

public class CrawlCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CrawlCommand.class);
    private WeatherCrawlStrategy strategy;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private String outputFile;

    public CrawlCommand(WeatherCrawlStrategy strategy, String city, LocalDate startDate, LocalDate endDate, String outputFile) {
        this.strategy = strategy;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.outputFile = outputFile;
    }

    @Override
    public void execute() throws CrawlerException {
        logger.info("开始执行爬取命令: {}, 城市: {}, 时间范围: {} - {}", strategy.getStrategyName(), city, startDate, endDate);
        try {
            List<WeatherData> dataList = strategy.batchCrawl(city, startDate, endDate);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(WeatherData.getCSVHeader());
                writer.newLine();
                for (WeatherData data : dataList) {
                    writer.write(data.toCSV());
                    writer.newLine();
                }
            }
            
            logger.info("爬取完成，共获取 {} 条记录，已保存到: {}", dataList.size(), outputFile);
        } catch (Exception e) {
            throw new CrawlerException("爬取失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getName() {
        return "CrawlCommand";
    }
}
