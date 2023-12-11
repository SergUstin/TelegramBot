package com.example.telegrambot.service.file_command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectFileCommand {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private List<SendPhotoCommand> sendObjects;

    public SendPhotoCommand getCommandByName(String commandName) {
        return (SendPhotoCommand) applicationContext.getBean(commandName, sendObjects.stream().findFirst().get());
    }


}
