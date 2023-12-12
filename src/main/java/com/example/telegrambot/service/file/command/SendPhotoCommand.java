package com.example.telegrambot.service.file.command;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.InputStream;

public interface SendPhotoCommand {
    SendPhoto setCommand(Update update);
}
