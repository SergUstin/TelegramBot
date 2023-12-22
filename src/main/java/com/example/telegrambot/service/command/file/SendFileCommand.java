package com.example.telegrambot.service.command.file;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendFileCommand {
    PartialBotApiMethod setCommand(Update update);

}
