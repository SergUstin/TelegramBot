package com.example.telegrambot.service.command.text;

import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.model.User;
import com.example.telegrambot.model.UserRepository;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

@Component("/send")
public class SendAddCommand extends SendText {

    private final UserRepository userRepository;

    private final BotConfig config;

    @Autowired
    public SendAddCommand(UserRepository userRepository, BotConfig config) {
        this.userRepository = userRepository;
        this.config = config;
    }

    @Override
    public SendMessage setCommand(Update update) {
        SendMessage sendMessage = null;
        if (config.getOwnerId().equals(update.getMessage().getChatId())) {
            var messageText = update.getMessage().getText();
            var spaceIndex = messageText.indexOf(4, update.getMessage().getText().length());
            if (spaceIndex != -1) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(spaceIndex + 1));
                var users = userRepository.findAll();
                for (User user : users) {
                    sendMessage = sendMessage(user.getChatId(), textToSend);
                }
            }
        }
        return sendMessage;
    }
}
