package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;

@Component
public class PhotoCommand extends Command {
    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/photo");
    }
}
