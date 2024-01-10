//package com.example.telegrambot.service.command.photo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class SelectFileCommand {
//    private final List<SendFileCommand> fileCommandList;
//
//    @Autowired
//    public SelectFileCommand(List<SendFileCommand> fileCommandList) {
//        this.fileCommandList = fileCommandList;
//    }
//
//    public List<SendFileCommand> getCommandByName(String command) {
//        return fileCommandList.stream()
//                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class))
//                .filter(clazz -> clazz.getClass().getAnnotation(Component.class).value().equals(command)) // добавил filter
//                .findFirst()
//                .map(Collections::singletonList)
//                .orElseGet(() ->  // поменял
//                        fileCommandList.stream()
//                                .filter(clazz -> clazz.getClass().getAnnotation(Component.class)
//                                        .value().equals("/incorrect"))
//                                .findFirst().stream().collect(Collectors.toList())
//                );
//    }
//}
//
