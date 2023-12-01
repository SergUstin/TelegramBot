package com.example.telegrambot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SelectCommand {

    @Autowired
    private StartCommand startCommand;

    @Autowired
    private SendAddCommand sendAddCommand;

    @Autowired
    private PhotoCommand photoCommand;

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

    public void setPhotoCommand(PhotoCommand photoCommand) {
        this.photoCommand = photoCommand;
    }

    public void setRegisterCommand(RegisterCommand registerCommand) {
        this.registerCommand = registerCommand;
    }

    public void setHelpCommand(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
    }

    public void changCommand(Update update) {
        switch (update.getMessage().getText()) {
            case "/start" -> startCommand.setCommand(update);
            case "/send" -> sendAddCommand.setCommand(update);
            case "/photo" -> photoCommand.setCommand(update);
            case "/register" -> registerCommand.setCommand(update);
            case "/help" -> helpCommand.setCommand(update);
        }
    }


}
