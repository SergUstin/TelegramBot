package com.example.telegrambot.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Configuration
@EnableScheduling
@Validated
@ConfigurationProperties(prefix = "bot")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotConfig extends AppProperties {

    @NotBlank
    String name;

    @NotBlank
    String token;

    @NotNull
    Long ownerId;
}
