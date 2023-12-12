package com.example.telegrambot.service.text_command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SelectTextCommand {

    private ApplicationContext applicationContext;

    private List<SendMessageCommand> sendObjects;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Autowired
    public void setSendObjects(List<SendMessageCommand> sendObjects) {
        this.sendObjects = sendObjects;
    }

    public SendMessageCommand getCommandByName(String commandName) {
        if (applicationContext.containsBean(commandName) && sendObjects != null) {
            return (SendMessageCommand) applicationContext.getBean(commandName,
                    sendObjects.stream().findFirst().get());
        } else {
            return applicationContext.getBean(IncorrectCommand.class);
        }
    }
}
