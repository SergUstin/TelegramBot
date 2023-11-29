package com.example.telegrambot.service.command;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class HelpCommand extends Command {

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "Toy can execute commands from thr main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /getPhoto yu can get a random photo\n\n" +
            "Type /register you can register your data in stored\n\n" +
            "Type /settings to see setting bot\n\n" +
            "Type /help to see description bot";

//    @Override
//    public boolean setCommand(String msg) {
//        return msg.equals("/help");
//    }

    @Override
    public SendMessage generateAnswerForUser(Long chatId) {
        return sendMessage(chatId, HELP_TEXT);
    }
}
