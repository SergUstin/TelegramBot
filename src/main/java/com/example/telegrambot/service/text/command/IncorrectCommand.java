package com.example.telegrambot.service.text.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/incorrect")
public class IncorrectCommand extends SendText {
    private String textWrong = "Sorry, command was not recognized";
    @Override
    public SendMessage setCommand(Update update) {
        return sendMessage(update.getMessage().getChatId(), textWrong);
    }
}
