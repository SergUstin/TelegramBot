package com.example.telegrambot.service.command.text;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectFileCommandTest {

    @Test
    void testStrategyPattern() {
        List<SendTextCommand> sendObjects = List.of(
                new StartCommand(),
                new HelpCommand()
                //and others
                );

        SelectTextCommand factoryExample = new SelectTextCommand(sendObjects);
        List<SendTextCommand> process = factoryExample.getCommandByName("/start");
        String actualResult = process.get(0).getClass().getSimpleName();

        assertEquals("StartCommand", actualResult);
    }

    @Test
    void testStrategyPatternWrong() {
        List<SendTextCommand> sendObjects = List.of(
                new StartCommand(),
                new HelpCommand()
                //and others
        );

        SelectTextCommand factoryExample = new SelectTextCommand(sendObjects);
        List<SendTextCommand> process = factoryExample.getCommandByName("/new_command");

        assertTrue(process.isEmpty());
    }
}