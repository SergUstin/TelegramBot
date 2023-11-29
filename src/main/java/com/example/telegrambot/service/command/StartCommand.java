package com.example.telegrambot.service.command;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Collections;

@Component
public class StartCommand extends Command {

    //    @Override
//    public boolean setCommand(String msg) {
//        return msg.equals("/start");
//    }

    @Override
    public SendMessage generateAnswerForUser(Long chatId) {
        String answer = "Hi, nice to meet you! :blush:";
        String answerUser = EmojiParser.parseToUnicode(answer);
        return sendMessage(chatId, answerUser);
    }

}
