package com.crawler.view;

import com.crawler.controller.CrawlerController;
import com.crawler.exception.CrawlerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleView {
    private static final Logger logger = LogManager.getLogger(ConsoleView.class);
    private CrawlerController controller;
    private Scanner scanner;
    private DateTimeFormatter formatter;

    public ConsoleView() {
        this.controller = new CrawlerController();
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void run() {
        logger.info("天气爬虫系统启动");
        System.out.println("===== 天气爬虫系统 =====");
        
        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1" -> crawlAllSites();
                    case "2" -> crawlBySite();
                    case "3" -> showHelp();
                    case "4" -> {
                        System.out.println("感谢使用，再见！");
                        logger.info("天气爬虫系统退出");
                        return;
                    }
                    default -> System.out.println("无效选项，请重新选择");
                }
            } catch (Exception e) {
                logger.error("操作失败: {}", e.getMessage());
                System.out.println("操作失败: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\n请选择操作:");
        System.out.println("1. 爬取所有网站");
        System.out.println("2. 爬取指定网站");
        System.out.println("3. 帮助");
        System.out.println("4. 退出");
        System.out.print("请输入选择: ");
    }

    private void crawlAllSites() throws CrawlerException {
        System.out.print("请输入城市: ");
        String city = scanner.nextLine().trim();
        
        System.out.print("请输入开始日期(yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine().trim(), formatter);
        
        System.out.print("请输入结束日期(yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine().trim(), formatter);

        System.out.println("正在爬取所有网站...");
        controller.crawlAllSites(city, startDate, endDate);
        System.out.println("爬取完成！");
    }

    private void crawlBySite() throws CrawlerException {
        System.out.println("\n可选网站:");
        System.out.println("china - 中国天气网");
        System.out.println("accu  - AccuWeather");
        System.out.println("open  - OpenWeatherMap");
        System.out.print("请输入网站代码: ");
        String site = scanner.nextLine().trim();

        System.out.print("请输入城市: ");
        String city = scanner.nextLine().trim();

        System.out.print("请输入开始日期(yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine().trim(), formatter);

        System.out.print("请输入结束日期(yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine().trim(), formatter);

        System.out.println("正在爬取 " + site + "...");
        controller.crawlBySite(site, city, startDate, endDate);
        System.out.println("爬取完成！");
    }

    private void showHelp() {
        System.out.println("\n===== 帮助信息 =====");
        System.out.println("本系统支持爬取以下天气网站:");
        System.out.println("1. 中国天气网 (china)");
        System.out.println("2. AccuWeather (accu)");
        System.out.println("3. OpenWeatherMap (open)");
        System.out.println("\n使用说明:");
        System.out.println("- 选择选项1可同时爬取所有网站");
        System.out.println("- 选择选项2可指定单个网站爬取");
        System.out.println("- 数据将保存为CSV文件");
    }
}
