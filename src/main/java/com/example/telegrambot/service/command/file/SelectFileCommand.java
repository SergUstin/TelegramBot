package com.example.telegrambot.service.command.file;

import com.example.telegrambot.service.command.file.SendFileCommand;
import com.example.telegrambot.service.command.text.SendTextCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SelectFileCommand {
    private final List<SendFileCommand> fileCommandList;

    @Autowired
    public SelectFileCommand(List<SendFileCommand> fileCommandList, List<SendTextCommand> textCommandList) {
        this.fileCommandList = fileCommandList;
    }

    public List<SendFileCommand> getCommandByName(String command) {
        return fileCommandList.stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command)) // добавил filter
                .findFirst()
                .map(Collections::singletonList)
                .orElseGet(() ->  // поменял
                        fileCommandList.stream()
                                .filter(clazz -> clazz.getClass().getAnnotation(Component.class)
                                        .value().equals("/incorrect"))
                                .findFirst().stream().toList()
                );
    }
}

