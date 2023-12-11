package com.example.telegrambot.service.text_command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectTextCommand {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private List<SendMessageCommand> sendObjects;

    public SendMessageCommand getCommandByName(String commandName) {
        if (applicationContext.containsBean(commandName)) {
            return (SendMessageCommand) applicationContext.getBean(commandName,
                    sendObjects.stream().findFirst().get());
        } else {
            return applicationContext.getBean(IncorrectCommand.class);
        }
    }
}
