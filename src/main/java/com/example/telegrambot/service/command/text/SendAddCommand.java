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

    @Autowired // Я не понимаю почему я удалил!! Вернул
    private UserRepository userRepository;

    private BotConfig config;

    @Override
    public SendMessage setCommand(Update update) {
        SendMessage sendMessage = null;
        if (Objects.equals(config.getOwnerId(), update.getMessage().getChatId())) {
            var textToSend = EmojiParser.parseToUnicode(update.getMessage().getText().substring(update.getMessage().getText().indexOf(" ")));
            var users = userRepository.findAll();
            for (User user : users) {
                sendMessage = sendMessage(user.getChatId(), textToSend);
            }
        }
        return sendMessage;
    }
}
