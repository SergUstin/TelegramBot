//package com.example.telegrambot.service.command;
//
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//
//@Component
//public class PhotoCommand extends Command {
//    @Override
//    public boolean setCommand(String msg) {
//        return msg.equals("/photo");
//    }
//
//    @Override
//    public SendMessage generateAnswerForUser(String chatId, String path) throws Exception {
//        sendPhoto(Long.parseLong(chatId), path);
//    }
//}
