package com.example.telegrambot.service.text.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SelectTextCommandTest {

    @Test
    void testStrategyPattern() {
        List<SendMessageCommand> sendObjects = List.of(
                new StartCommand(),
                new HelpCommand()
                //and others
                );

        SelectTextCommand factoryExample = new SelectTextCommand(sendObjects);
        List<SendMessageCommand> process = factoryExample.getCommandByName("/start");
        String actualResult = process.get(0).getClass().getSimpleName();

        assertEquals("StartCommand", actualResult);
    }

    @Test
    void testStrategyPatternWrong() {
        List<SendMessageCommand> sendObjects = List.of(
                new StartCommand(),
                new HelpCommand()
                //and others
        );

        SelectTextCommand factoryExample = new SelectTextCommand(sendObjects);
        List<SendMessageCommand> process = factoryExample.getCommandByName("/new_command");

        assertTrue(process.isEmpty());
    }
}