package com.example.telegrambot.service.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class Command {

    // Выбор команды
//    public abstract boolean setCommand(String msg);

    // Генерация сообщения по определенной команде
    public abstract SendMessage generateAnswerForUser(Long chatId);

    // Методы отправки сообщения и фото
    SendMessage sendMessage (Long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        return message;

        // Для кнопок - реализую позже

//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("weather");
//        row.add("get random joke");
//
//        keyboardRows.add(row);
//
//        row = new KeyboardRow();
//
//        row.add("register");
//        row.add("check my data");
//        row.add("delete my data");
//
//        keyboardRows.add(row);
//        keyboardMarkup.setKeyboard(keyboardRows);
//        message.setReplyMarkup(keyboardMarkup);
    }

    SendPhoto sendPhoto (long chatId, String pathToPhoto) {
        File file = null;
        try {
            file = ResourceUtils.getFile(pathToPhoto);
        } catch (FileNotFoundException e) {
            log.error("File not found" + e.getMessage());
        }
        SendPhoto sendPhoto = new SendPhoto();
        if (file != null) {
            sendPhoto.setPhoto(new InputFile(file));
        }
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setCaption("Вот ваша картинка!");
        return sendPhoto;
    }
}
