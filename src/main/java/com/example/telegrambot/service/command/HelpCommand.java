package com.example.telegrambot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommand extends SendObject implements SendMessageCommand {

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "Toy can execute commands from thr main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /send you can sand message all users(only admin)\n\n" +
            "Type /photo you can see photo\n\n" +
            "Type /register you can register you data\n\n" +
            "Type /settings to see setting bot\n\n" +
            "Type /help to see description bot";

    @Override
    public SendMessage setCommand(Update update) {
        return sendMessage(update.getMessage().getChatId(), HELP_TEXT);
    }
}
