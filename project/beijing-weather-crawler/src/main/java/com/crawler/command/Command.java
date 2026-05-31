package com.crawler.command;

import com.crawler.exception.CrawlerException;

public interface Command {
    void execute() throws CrawlerException;
    String getName();
}
