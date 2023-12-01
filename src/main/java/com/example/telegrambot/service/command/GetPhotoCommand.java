package com.example.telegrambot.service.command;

public class GetPhotoCommand extends Command {
    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/photo");
    }
}
