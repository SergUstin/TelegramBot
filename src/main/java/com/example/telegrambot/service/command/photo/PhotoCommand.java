package com.example.telegrambot.service.command.photo;

import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.service.command.SendCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;

@Component("/photo")
public class PhotoCommand implements SendCommand<SendPhoto> {

    private final static String JPG = "Java.jpg";
    private final TelegramBot bot;

    public PhotoCommand(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public SendPhoto setCommand(Update update) {
        return sendPhoto(update.getMessage().getChatId());
    }

    @Override
    public void execute(SendPhoto sendPhoto) throws TelegramApiException {
        bot.execute(sendPhoto);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        SendPhoto sendPhoto = setCommand(update);
        execute(sendPhoto);
    }

    private SendPhoto sendPhoto(long chatId) {
        InputStream resource = getClass().getResourceAsStream("/".concat(JPG));
        InputFile inputFile = new InputFile(resource, JPG);
        return SendPhoto.builder()
                .chatId(String.valueOf(chatId))
                .photo(inputFile)
                .build();
    }
}
