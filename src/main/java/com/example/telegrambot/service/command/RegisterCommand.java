package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;

@Component
public class RegisterCommand extends Command {
    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/register");
    }
}
