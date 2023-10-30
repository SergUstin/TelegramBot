package com.example.telegrambot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
//@PropertySource("application.properties")
public class BotConfig {

//    @Value("${bot.name}")
    String botName = "StonanysBot";
//    @Value("${bot.token}")
    String token = "6764043787:AAEFa_YrXC2oVE2tacBpePNjvtJJ12Wg_84";
}
