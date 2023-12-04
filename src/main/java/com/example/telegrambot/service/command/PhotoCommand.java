package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/photo")
public class PhotoCommand extends SendObject {

    private String path = "C:\\Users\\Serg\\TelegramBot\\src\\main\\resources\\Java.jpg";

    @Override
    public SendPhoto setCommand(Update update) {
        return sendPhoto(update.getMessage().getChatId(), path);
    }
}
