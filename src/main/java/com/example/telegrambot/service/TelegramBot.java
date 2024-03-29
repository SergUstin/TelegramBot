package com.example.telegrambot.service;

import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.model.User;
import com.example.telegrambot.model.UserRepository;
import com.example.telegrambot.service.command.photo.SelectFileCommand;
import com.example.telegrambot.service.command.photo.SendFileCommand;
import com.example.telegrambot.service.command.text.SelectTextCommand;
import com.example.telegrambot.service.command.text.SendTextCommand;
import com.example.telegrambot.util.RowUtil;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final UserRepository userRepository;

    private final BotConfig config;

    private final SelectTextCommand selectTextCommand;

    static final String ERROR_TEXT = "Error occurred: ";

    public TelegramBot(UserRepository userRepository, BotConfig config, SelectTextCommand selectTextCommand) {
        this.userRepository = userRepository;
        this.config = config;
        this.selectTextCommand = selectTextCommand;
        List<BotCommand> listOfCommands = new ArrayList<>();
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
        String messageText;

        if (update.hasMessage() && update.getMessage().hasText()) {
            messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (messageText.contains("/send") && config.getOwnerId() == chatId) {
                var textToSend = EmojiParser.parseToUnicode(update.getMessage().getText().substring(update.getMessage().getText().indexOf(" ")));
                var users = userRepository.findAll();
                for (User user : users) {
                    try {
                        execute(new SendMessage(String.valueOf(user.getChatId()), textToSend));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                List<SendTextCommand> commandByName = selectTextCommand.getCommandByName(messageText);

                for (SendTextCommand command : commandByName) {

                    try {
                        execute(command.setCommand(update));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
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
