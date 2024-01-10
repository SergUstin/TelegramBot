package com.example.telegrambot.service.command;

import com.example.telegrambot.service.command.photo.SendPhotoCommand;
import com.example.telegrambot.service.command.text.SendTextCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SelectCommand {

    private final List<SendTextCommand> sendTextCommands;
    private final List<SendPhotoCommand> sendPhotoCommands;

    @Autowired
    public SelectCommand(List<SendTextCommand> sendTextCommands, List<SendPhotoCommand> sendPhotoCommands) {
        this.sendTextCommands = sendTextCommands;
        this.sendPhotoCommands = sendPhotoCommands;
    }

    public List<? extends PartialBotApiMethod> getCommandByName(String command) {
        List<PartialBotApiMethod> commandList = new ArrayList<>();
        for (SendTextCommand textCommand : sendTextCommands) {
            commandList.add((PartialBotApiMethod) textCommand);
        }

        for (SendPhotoCommand photoCommand : sendPhotoCommands) {
            commandList.add((PartialBotApiMethod) photoCommand);
        }

        return commandList.stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
                .filter(clazz -> {
                    Component annotation = clazz.getClass().getAnnotation(Component.class);
                    return annotation != null && annotation.value().equals(command);
                })
                .findFirst()
                .map(Collections::singletonList)
                .orElseGet(() ->
                        commandList.stream()
                                .filter(clazz -> {
                                    Component annotation = clazz.getClass().getAnnotation(Component.class);
                                    return annotation != null && annotation.value().equals("/incorrect");
                                })
                                .findFirst()
                                .stream()
                                .toList()
                );
    }
}
