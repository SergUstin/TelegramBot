package com.example.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendCommand<T> {
    PartialBotApiMethod setCommand(Update update);
    Class getType();
}
