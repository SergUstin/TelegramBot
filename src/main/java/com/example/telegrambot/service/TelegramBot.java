package com.example.telegrambot.service;

import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.model.Ads;
import com.example.telegrambot.model.AdsRepository;
import com.example.telegrambot.model.User;
import com.example.telegrambot.model.UserRepository;
import com.example.telegrambot.service.command.Command;
import com.example.telegrambot.service.strategy.HelpCommandStrategy;
import com.example.telegrambot.service.strategy.RegisterCommandStrategy;
import com.example.telegrambot.service.strategy.StartCommandStrategy;
import com.example.telegrambot.service.strategy.YourTelegramBot;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private AdsRepository adsRepository;
    final BotConfig config;

    @Autowired
    private YourTelegramBot bot;

//    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
//            "Toy can execute commands from thr main menu on the left or by typing a command:\n\n" +
//            "Type /start to see a welcome message\n\n" +
//            "Type /getPhoto yu can get a random photo\n\n" +
//            "Type /register you can register your data in stored\n\n" +
//            "Type /settings to see setting bot\n\n" +
//            "Type /help to see description bot";

    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";

    static final String ERROR_TEXT = "Error occurred: ";

    public TelegramBot(UserRepository userRepository, BotConfig config) {
        this.userRepository = userRepository;
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList();
        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/getPhoto", "get a photo"));
        listOfCommands.add(new BotCommand("/register", "user registration"));
        listOfCommands.add(new BotCommand("/send", "send a message to all users. only for admin"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));
        listOfCommands.add(new BotCommand("/help", "info how to use this bot"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bots command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();


            // Рассылка сообщений
            if (messageText.contains("/send") && config.getOwnerId() == chatId) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                var users = userRepository.findAll();
                for (User user : users) {
                    prepareAndSendMessage(user.getChatId(), textToSend);
                }
                // Начало стратегии
            }
//            bot.setStartCommandStrategy(new StartCommandStrategy());
//            bot.setHelpCommandStrategy(new HelpCommandStrategy());
//            bot.setRegisterCommandStrategy(new RegisterCommandStrategy());
//
//            bot.onUpdateReceived(update, userRepository);


//            else if (messageText.equals("/start")) {
//
//                commandList.stream().filter(command -> command.setCommand(messageText))
//                        .forEach(command -> {
//                            registerUser(update.getMessage());
//                            startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
//                        });
//
//            } else {
//
//                if (messageText.equals("/help")) {
//
//                    commandList.stream().filter(command -> command.setCommand(messageText))
//                            .forEach(command -> prepareAndSendMessage(chatId, HELP_TEXT));
//
//                } else if (messageText.equals("/register")) {
//
//                    commandList.stream().filter(command -> command.setCommand(messageText))
//                            .forEach(command -> register(chatId));
//
//                } else if (messageText.equals("/photo")) {
//
//                    commandList.stream().filter(command -> command.setCommand(messageText))
//                            .forEach(command -> sendPhoto(chatId));
//
//                } else {
//                    prepareAndSendMessage(chatId, "Sorry, command was not recognized");
//                }
//            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callbackData.equals(YES_BUTTON)) {

                String text = "You pressed YES button";
                executeEditMessageText(text, chatId, messageId);

            } else if (callbackData.equals(NO_BUTTON)) {

                String text = "You pressed NO button";
                executeEditMessageText(text, chatId, messageId);
            }
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

    // Все что ниже убрать из класса!!!!

//    private void register(long chatId) {
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText("Do you really want to register?");
//
//        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        var yesButton = new InlineKeyboardButton();
//
//        yesButton.setText("Yes");
//        yesButton.setCallbackData(YES_BUTTON);
//
//        var noButton = new InlineKeyboardButton();
//        noButton.setText("No");
//        noButton.setCallbackData(NO_BUTTON);
//
//        rowInline.add(yesButton);
//        rowInline.add(noButton);
//
//        rowsInline.add(rowInline);
//
//        markupInline.setKeyboard(rowsInline);
//        message.setReplyMarkup(markupInline);
//
//        executeMessage(message);
//
//
//    }

//    private void registerUser(Message msg) {
//        if (userRepository.findById(msg.getChatId()).isEmpty()) {
//            var chatId = msg.getChatId();
//            var chat = msg.getChat();
//
//            User user = new User();
//            user.setChatId(chatId);
//            user.setFirstName(chat.getFirstName());
//            user.setLastName(chat.getLastName());
//            user.setLastName(chat.getUserName());
//            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
//
//            userRepository.save(user);
//            log.info("user saved: " + user);
//        }
//
//    }
//
//    private void startCommandReceived(long chatId, String name) {
//
//        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + " :blush:");
//        log.info("Replied to user " + name);
//
//        sendMessage(chatId, answer);
//    }
//
//    private void sendMessage(long chatId, String texToSend) {
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(texToSend);
//
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("weather");
//        row.add("get random joke");
//
//        keyboardRows.add(row);
//
//        row = new KeyboardRow();
//
//        row.add("register");
//        row.add("check my data");
//        row.add("delete my data");
//
//        keyboardRows.add(row);
//        keyboardMarkup.setKeyboard(keyboardRows);
//        message.setReplyMarkup(keyboardMarkup);
//
//        executeMessage(message);
//    }

    private void sendPhoto(long chatId) {
        File file = null;
        try {
            file = ResourceUtils.getFile("C:\\Users\\Serg\\TelegramBot\\src\\main\\resources\\Java.jpg");
        } catch (FileNotFoundException e) {
            log.error("File not found" + e.getMessage());
        }
        SendPhoto sendPhoto = new SendPhoto();
        if (file != null) {
            sendPhoto.setPhoto(new InputFile(file));
        }
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setCaption("Вот ваша картинка!");
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }

    }

    private void executeEditMessageText(String text, long chatId, long messageId) {
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }

    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void prepareAndSendMessage(long chatId, String texToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(texToSend);
        executeMessage(message);
    }

    //    @Scheduled(cron = "${cron.scheduler}")
    private void sendAds() {

        var ads = adsRepository.findAll();
        var users = userRepository.findAll();

        for (Ads ad : ads) {
            for (User user : users) {
                prepareAndSendMessage(user.getChatId(), ad.getAd());
            }

        }

    }
}
