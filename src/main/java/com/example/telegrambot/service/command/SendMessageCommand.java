package com.example.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface SendMessageCommand {
    PartialBotApiMethod setCommand(Update update);
}
