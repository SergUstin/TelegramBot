package com.example.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        log.info("Before Starting application");
        SpringApplication.run(TelegramBotApplication.class, args);
        log.debug("Starting my application in debug with {} args", args.length);
        log.info("Starting my application with {} args.", args.length);
    }

}
