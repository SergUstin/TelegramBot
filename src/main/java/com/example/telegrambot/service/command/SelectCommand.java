package com.example.telegrambot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class SelectCommand {

    private final SendCommand<?> sendCommands;

    public SelectCommand(@Qualifier("/start") SendCommand<?> sendCommands) {
        this.sendCommands = sendCommands;
    }

    public SendCommand<?> getCommandByName(String command) {

//        return sendCommands.stream()
//                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
//                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command))
//                .findFirst()
//                .orElse(null);

//        return sendCommands.stream()
//                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
//                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command))
//                .findFirst()
//                .map(Collections::singletonList)
//                .orElseGet(() ->
//                        sendCommands.stream()
//                                .filter(clazz -> clazz.getClass().getAnnotation(Component.class)
//                                        .value().equals("/incorrect"))
//                                .findFirst().stream().toList()
//                );
        return null;
    }
}
