package com.example.telegrambot.service.text.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service // Правильнее же использовать эту аннотацию? У нас же тут бизнес логика!
public class SelectTextCommand {
    private final List<SendMessageCommand> commandList; // Такое название подойдет? Выбрал и того что идея предлагала

    @Autowired
    public SelectTextCommand(List<SendMessageCommand> commandList) {
        this.commandList = commandList;
    }

    public List<SendMessageCommand> getCommandByName(String command) {
        return commandList.stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command)) // добавил filter
                .findFirst()
                .map(Collections::singletonList)
                .orElseGet(() ->  // поменял
                        commandList.stream()
                                .filter(clazz -> clazz.getClass().getAnnotation(Component.class)
                                        .value().equals("/incorrect"))
                                .findFirst().stream().toList()
                );
    }
}
