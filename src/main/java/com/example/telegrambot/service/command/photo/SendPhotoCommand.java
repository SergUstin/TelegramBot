package com.example.telegrambot.service.command.photo;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendPhotoCommand {
    PartialBotApiMethod setCommand(Update update);
}
