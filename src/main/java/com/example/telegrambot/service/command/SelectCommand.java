package com.example.telegrambot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectCommand {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private List<SendMessageCommand> sendObjects;

    // Пишем реализацию поиска бинов по названию
    // Загуглить как искать бин по названию


}
