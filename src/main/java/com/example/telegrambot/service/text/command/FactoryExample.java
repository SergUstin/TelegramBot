package com.example.telegrambot.service.text.command;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фабрика по выбору текстовой команды телеграмма.
 */
@Service
public class FactoryExample {
    private final List<SendMessageCommand> list;

    /**
     * Конструктор.
     *
     * @param list - все наследники SendMessageCommand.
     */
    public FactoryExample(List<SendMessageCommand> list) {
        this.list = list;
    }

    /**
     * Выбор команды по тексту.
     * @param command текст от пользователя
     * @return список команд (может быть пустым)
     */
    public List<SendMessageCommand> process(String command) {
        return list.stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(Component.class)
                        && clazz.getClass().getAnnotation(Component.class).value().equals(command))
                .collect(Collectors.toList());
    }
}
