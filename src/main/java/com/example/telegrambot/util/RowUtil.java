package com.example.telegrambot.util;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RowUtil {

    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";

    @SneakyThrows
    public static EditMessageText rowSet(Update update) {
        EditMessageText message = new EditMessageText();
        String callbackData = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (callbackData.equals(YES_BUTTON)) {
            String text = "You pressed YES button";
            message.setChatId(String.valueOf(chatId));
            message.setText(text);
            message.setMessageId((int) messageId);
        } else if (callbackData.equals(NO_BUTTON)) {
            String text = "You pressed NO button";
            message.setChatId(String.valueOf(chatId));
            message.setText(text);
            message.setMessageId((int) messageId);
        }
        return message;
    }
}
