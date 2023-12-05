package com.example.telegrambot.service.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class SendObject implements SendMessageCommand {

    SendMessage sendMessage(long chatId, String texToSend) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(texToSend)
                .build();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("weather");
        row.add("get random joke");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("register");
        row.add("check my data");
        row.add("delete my data");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

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
