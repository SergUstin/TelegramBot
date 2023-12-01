package com.example.telegrambot.service.command;

public class SandAddCommand extends Command {
    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/send");
    }
}
