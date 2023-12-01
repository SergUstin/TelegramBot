package com.example.telegrambot.service.command;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class HelpCommand extends Command {

    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/help");
    }
}
