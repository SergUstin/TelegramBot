package com.example.telegrambot.service.text_command;

import com.example.telegrambot.model.User;
import com.example.telegrambot.model.UserRepository;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;

@Slf4j
@Component("/start")
public class StartCommand extends SendText {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SendMessage setCommand(Update update) {

        String answer = EmojiParser.parseToUnicode("Hi, " +
                update.getMessage().getChat().getFirstName() +
                ", nice to meet you!" + " :blush:");


        registerUser(update.getMessage());
        log.info("Replied to user " + update.getMessage().getChat().getFirstName());

        return sendMessage(update.getMessage().getChatId(), answer);
    }

    private void registerUser(Message msg) {
        if (userRepository.findById(msg.getChatId()).isEmpty()) {
            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setLastName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
            log.info("user saved: " + user);
        }

    }
}
