package com.example.telegrambot.service.file_command;//todo не называй пакеты с нижним подчеркиванием, плиз

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/photo")
public class PhotoCommand extends SendFile {

    private final static String PATH = "src/main/resources/Java.jpg";

    @Override
    public SendPhoto setCommand(Update update) {
        return sendPhoto(update.getMessage().getChatId(), PATH);
    }
}
