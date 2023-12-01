package com.example.telegrambot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectCommand {

    @Autowired
    private StartCommand startCommand;

    @Autowired
    private SendAddCommand sendAddCommand;

    @Autowired
    private PhotoCommand getPhotoCommand;

    @Autowired
    private RegisterCommand registerCommand;

    @Autowired
    private HelpCommand helpCommand;

    public void setStartCommand(StartCommand startCommand) {
        this.startCommand = startCommand;
    }

    public void setSendAddCommand(SendAddCommand sendAddCommand) {
        this.sendAddCommand = sendAddCommand;
    }

    public void setGetPhotoCommand(PhotoCommand getPhotoCommand) {
        this.getPhotoCommand = getPhotoCommand;
    }

    public void setRegisterCommand(RegisterCommand registerCommand) {
        this.registerCommand = registerCommand;
    }

    public void setHelpCommand(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
    }
}
