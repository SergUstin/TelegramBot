package com.example.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendPhotoCommand {
    SendPhoto setCommand(Update update);
}