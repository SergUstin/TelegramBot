package com.example.telegrambot.service.command;

import com.example.telegrambot.service.command.text.IncorrectCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.Collections;
import java.util.List;

@Service
public class SelectCommand {

    private final List<SendCommand<?>> sendCommands;

    @Autowired
    public SelectCommand(List<SendCommand<?>> sendCommands) {
        this.sendCommands = sendCommands;
    }

    public Class getCommandByName(String command) {

//        return (SendCommand<?>) sendCommands.stream()
//                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
//                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command))
//                .findFirst()
//                .map(SendCommand::getType)
//                .filter(commandInstance -> commandInstance instanceof SendPhoto || commandInstance instanceof SendMessage)
//                .orElse(new IncorrectCommand());

        return (SendCommand<?>) sendCommands.stream()
                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command))
                .findFirst()
                .map(clazz -> {
                    clazz.
                })
                .map(SendCommand::getType)
                .map(Collections::singletonList)
                .orElseGet(() ->
                        Collections.singletonList(Collections.singletonList(sendCommands.stream()
                                .filter(clazz -> clazz.getClass().getAnnotation(Component.class)
                                        .value().equals("/incorrect"))
                                .findFirst().stream().toList()))
                );
//        return null;
    }
}
