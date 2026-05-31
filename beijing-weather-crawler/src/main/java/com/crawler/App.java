package com.crawler;

import com.crawler.controller.CrawlerController;
import com.crawler.exception.CrawlerException;
import com.crawler.view.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            runTest();
        } else {
            ConsoleView view = new ConsoleView();
            view.run();
        }
    }

    private static void runTest() {
        logger.info("运行测试模式");
        System.out.println("===== 天气爬虫系统 - 测试模式 =====\n");

        CrawlerController controller = new CrawlerController();

        try {
            System.out.println("测试爬取所有网站...");
            controller.crawlAllSites("北京", LocalDate.now(), LocalDate.now().plusDays(2));
            System.out.println("测试成功！已生成CSV文件。\n");

            System.out.println("生成的CSV文件：");
            System.out.println("- china_weather_北京.csv");
            System.out.println("- accu_weather_北京.csv");
            System.out.println("- openweather_北京.csv");

        } catch (CrawlerException e) {
            logger.error("测试失败: {}", e.getMessage());
            System.out.println("测试失败: " + e.getMessage());
        }
    }
}
