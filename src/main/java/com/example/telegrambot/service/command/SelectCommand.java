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

    //Метод выбирающий нужный объект по тексту команды
    public SendMessageCommand getCommandByName(String commandName) {

        return (SendMessageCommand) applicationContext.getBean(commandName,
                // Тестил stream в дебагере, доступ к полям есть!!
                sendObjects.stream().findFirst().get());

    }

}
