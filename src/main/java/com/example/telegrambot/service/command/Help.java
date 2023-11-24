package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/help")
public class Help implements Command {

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "Toy can execute commands from thr main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /mydata to see data stored message again\n\n" +
            "Type /deletedata you can delete your data in stored\n\n" +
            "Type /help to see description bot\n\n" +
            "Type /settings to see setting bot";

    @Override
    public SendMessage commandSelection() {
        Update update = new Update();
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(HELP_TEXT);
        return message;
    }
}
