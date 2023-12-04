package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/incorrect")
public class IncorrectCommand extends SendObject {
    private String textWrong = "Sorry, command was not recognized";
    @Override
    public SendMessage setCommand(Update update) {
        return sendMessage(update.getMessage().getChatId(), textWrong);
    }
}
