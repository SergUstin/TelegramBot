package com.example.telegrambot.service.command.text;

import com.example.telegrambot.service.TelegramBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/incorrect")
public class IncorrectCommand extends SendText {

    private String textWrong = "Sorry, command was not recognized";

    public IncorrectCommand(TelegramBot bot) {
        super(bot);
    }

    @Override
    public SendMessage setCommand(Update update) {
        return sendMessage(update.getMessage().getChatId(), textWrong);
    }
}
