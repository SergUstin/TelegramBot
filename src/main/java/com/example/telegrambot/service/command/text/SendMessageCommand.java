package com.example.telegrambot.service.command.text;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface SendMessageCommand {
    SendMessage setCommand(Update update);
}
