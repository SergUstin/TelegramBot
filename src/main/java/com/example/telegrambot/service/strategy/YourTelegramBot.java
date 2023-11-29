package com.example.telegrambot.service.strategy;

import com.example.telegrambot.model.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class YourTelegramBot {
    private CommandStrategy startCommandStrategy;
    private CommandStrategy helpCommandStrategy;
    private CommandStrategy registerCommandStrategy;
    private CommandStrategy defaultCommandStrategy;

    public void setStartCommandStrategy(CommandStrategy startCommandStrategy) {
        this.startCommandStrategy = startCommandStrategy;
    }

    public void setHelpCommandStrategy(CommandStrategy helpCommandStrategy) {
        this.helpCommandStrategy = helpCommandStrategy;
    }

    public void setRegisterCommandStrategy(CommandStrategy registerCommandStrategy) {
        this.registerCommandStrategy = registerCommandStrategy;
    }

    public void setDefaultCommandStrategy(CommandStrategy defaultCommandStrategy) {
        this.defaultCommandStrategy = defaultCommandStrategy;
    }

    public void onUpdateReceived(Update update, UserRepository userRepository) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    startCommandStrategy.execute(update, userRepository);
                    break;
                case "/help":
                    helpCommandStrategy.execute(update, userRepository);
                    break;
                case "/register":
                    registerCommandStrategy.execute(update, userRepository);
                    break;
                default:
                    defaultCommandStrategy.execute(update, userRepository);
            }
        }
    }
}
