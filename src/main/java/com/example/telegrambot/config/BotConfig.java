package com.example.telegrambot.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Configuration
@EnableScheduling
@Validated
@ConfigurationProperties(prefix = "com.example.telegrambot.resources.application.properties")
public class BotConfig extends AppProperties {

    @NotBlank
    String botName;

    @NotBlank
    String token;

    @NotBlank
    Long ownerId;
}
