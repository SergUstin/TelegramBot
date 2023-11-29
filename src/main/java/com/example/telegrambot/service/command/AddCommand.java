package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;

@Component
public class AddCommand {
    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

}
