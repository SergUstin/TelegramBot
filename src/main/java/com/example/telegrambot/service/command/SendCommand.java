package com.example.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendCommand<T> {
    T setCommand(Update update);
    T getType();
}
