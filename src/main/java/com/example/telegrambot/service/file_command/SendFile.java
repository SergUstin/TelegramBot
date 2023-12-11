package com.example.telegrambot.service.file_command;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.InputStream;

public abstract class SendFile implements SendPhotoCommand {

    SendPhoto sendPhoto(long chatId, String path) {
        InputStream resource = getClass().getResourceAsStream(path);
        InputFile inputFile = new InputFile(resource, "Java.jpg");
        return SendPhoto.builder()
                .photo(inputFile)
                .chatId(String.valueOf(chatId))
                .caption("Вот ваша картинка")
                .build();
    }
}
