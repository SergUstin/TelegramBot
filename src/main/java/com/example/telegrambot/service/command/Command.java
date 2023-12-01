package com.example.telegrambot.service.command;

import lombok.extern.slf4j.Slf4j;


public abstract class Command {
    public abstract boolean setCommand(String msg);
}
