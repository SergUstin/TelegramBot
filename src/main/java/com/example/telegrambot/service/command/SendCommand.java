package com.example.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface SendCommand<T> {

    T setCommand(Update update);

    void execute(T t) throws TelegramApiException;

    void execute(Update update) throws TelegramApiException;
}
