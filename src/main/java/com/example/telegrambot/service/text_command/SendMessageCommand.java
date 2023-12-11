package com.example.telegrambot.service.text_command;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface SendMessageCommand {
    BotApiMethod setCommand(Update update);
}
