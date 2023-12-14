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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SelectTextCommandTest {

    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private SelectTextCommand selectTextCommand;

    @Test
    void testGetCommandByName_ExistingCommand_ReturnsSendMessageCommand() {
        List<SendMessageCommand> sendObjects = List.of(new StartCommand());

        when(applicationContext.containsBean("validCommand")).thenReturn(true);
        StartCommand startCommand = new StartCommand();
        when(applicationContext.getBean("validCommand", SendMessageCommand.class)).thenReturn(startCommand);

        selectTextCommand.setSendObjects(sendObjects);

        SendMessageCommand result = selectTextCommand.getCommandByName("validCommand");

        assertEquals(startCommand, result);
    }

    @Test
    void testGetCommandByName_NonExistingCommand_ReturnsIncorrectCommand() {
        String invalidCommandName = "invalidCommand";
        when(applicationContext.containsBean(invalidCommandName)).thenReturn(false);
        IncorrectCommand incorrectCommand = new IncorrectCommand();
        when(applicationContext.getBean(IncorrectCommand.class)).thenReturn(incorrectCommand);

        SendMessageCommand result = selectTextCommand.getCommandByName(invalidCommandName);

        assertEquals(incorrectCommand, result);
    }

    @Test
    void testGetCommandByName_FactoryCreatesAllStrategyInstances() {
        List<SendMessageCommand> sendObjects = Arrays.asList(new StartCommand(), new HelpCommand());
        when(applicationContext.containsBean("startCommand")).thenReturn(true);

        when(applicationContext.getBean("startCommand", SendMessageCommand.class)).thenReturn((StartCommand)sendObjects.get(0));

        selectTextCommand.setSendObjects(sendObjects);

        SendMessageCommand resultStrategy1 = selectTextCommand.getCommandByName("startCommand");
        assertEquals(StartCommand.class, resultStrategy1.getClass());
    }
}