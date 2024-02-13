package com.example.telegrambot.service.command.text;

import com.example.telegrambot.service.TelegramBot;
import com.example.telegrambot.service.command.SendCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public abstract class SendText implements SendCommand<SendMessage> {

    private final TelegramBot bot;

    public SendText(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(SendMessage sendMessage) throws TelegramApiException {
        bot.execute(sendMessage);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        SendMessage sendMessage = setCommand(update);
        execute(sendMessage);
    }

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
}
