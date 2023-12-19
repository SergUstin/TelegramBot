package com.example.telegrambot.service.text.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SelectTextCommand {
    private final List<SendMessageCommand> list;

    @Autowired
    public SelectTextCommand(List<SendMessageCommand> list) {
        this.list = list;
    }

    public List<SendMessageCommand> getCommandByName(String command) {
        return list.stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class)
                        && clazz.getClass().getAnnotation(Component.class).value().equals(command))
                .findFirst()
                .map(Collections::singletonList)
                .orElse(
                        list.stream()
                                .filter(clazz -> clazz.getClass().getAnnotation(Component.class)
                                        .value().equals("/incorrect"))
                                .findFirst().stream().toList()
                );
    }
}
