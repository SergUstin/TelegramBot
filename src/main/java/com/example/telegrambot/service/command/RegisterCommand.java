package com.example.telegrambot.service.command;

public class RegisterCommand extends Command {
    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/register");
    }
}
