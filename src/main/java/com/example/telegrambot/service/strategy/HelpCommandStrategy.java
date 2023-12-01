package com.example.telegrambot.service.strategy;

import com.example.telegrambot.model.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommandStrategy implements CommandStrategy {

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "Toy can execute commands from thr main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /getPhoto yu can get a random photo\n\n" +
            "Type /register you can register your data in stored\n\n" +
            "Type /settings to see setting bot\n\n" +
            "Type /help to see description bot";

    @Override
    public void execute(Update update, UserRepository userRepository) {
        long chatId = update.getMessage().getChat().getId();
        prepareAndSendMessage(chatId, HELP_TEXT);
    }

    private SendMessage prepareAndSendMessage(long chatId, String texToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(texToSend);
        return message;
    }
}
