package com.example.telegrambot.service.strategy;

import com.example.telegrambot.model.UserRepository;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandStrategy {
    void execute(Update update, UserRepository userRepository);
}
