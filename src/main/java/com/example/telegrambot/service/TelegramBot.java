package com.example.telegrambot.service;

import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.service.file_command.SelectFileCommand;
import com.example.telegrambot.service.text_command.SelectTextCommand;
import com.example.telegrambot.service.text_command.SendMessageCommand;
import com.example.telegrambot.util.RowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final SelectTextCommand selectTextCommand;

    private final SelectFileCommand selectFileCommand;

    static final String ERROR_TEXT = "Error occurred: ";

    public TelegramBot(BotConfig config, SelectTextCommand selectCommand, SelectFileCommand selectFileCommand) {
        this.config = config;
        this.selectTextCommand = selectCommand;
        this.selectFileCommand = selectFileCommand;
        List<BotCommand> listOfCommands = new ArrayList();
        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/send", "sand message all users(only admin)"));
        listOfCommands.add(new BotCommand("/register", "register you data"));
        listOfCommands.add(new BotCommand("/photo", "get a photo"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));
        listOfCommands.add(new BotCommand("/help", "description bot"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();




        } else if (update.hasCallbackQuery()) {
            try {
                execute(RowUtil.rowSet(update));
            } catch (TelegramApiException e) {
                log.error(ERROR_TEXT + e.getMessage());
            }
        }
    }

//        @Scheduled(cron = "${cron.scheduler}")
//    private void sendAds() {
//        var ads = adsRepository.findAll();
//        var users = userRepository.findAll();
//        for (Ads ad : ads) {
//            for (User user : users) {
//                prepareAndSendMessage(user.getChatId(), ad.getAd());
//            }
//        }
//    }
}
