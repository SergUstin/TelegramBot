package com.example.telegrambot.service.text_command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectTextCommandTest {

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private SendMessageCommand sendMessageCommand;

    @InjectMocks
    private SelectTextCommand selectTextCommand;

    @BeforeEach
    void setUp() {
        selectTextCommand.setApplicationContext(applicationContext);
    }

    @Test
    void testGetCommandByName_ExistingCommand_ReturnsSendMessageCommand() {
        // Arrange
        String commandName = "validCommand";
        when(applicationContext.containsBean(commandName)).thenReturn(true);
        when(applicationContext.getBean(commandName, sendMessageCommand)).thenReturn(sendMessageCommand);

        // Act
        SendMessageCommand result = selectTextCommand.getCommandByName(commandName);

        // Assert
        assertEquals(sendMessageCommand, result);
    }

    @Test
    void testGetCommandByName_NonExistingCommand_ReturnsIncorrectCommand() {
        // Arrange
        String invalidCommandName = "invalidCommand";
        when(applicationContext.containsBean(invalidCommandName)).thenReturn(false);
        IncorrectCommand incorrectCommand = new IncorrectCommand();
        when(applicationContext.getBean(IncorrectCommand.class)).thenReturn(incorrectCommand);

        // Act
        SendMessageCommand result = selectTextCommand.getCommandByName(invalidCommandName);

        // Assert
        assertEquals(incorrectCommand, result);
    }

    @Test
    void testGetCommandByName_FactoryCreatesAllStrategyInstances() {
        // Arrange
        List<SendMessageCommand> sendObjects = Arrays.asList(new StartCommand(), new HelpCommand());
        when(applicationContext.containsBean("StartCommand")).thenReturn(true);
        when(applicationContext.containsBean("HelpCommand")).thenReturn(true);
        when(applicationContext.getBean("StartCommand", sendObjects.get(0))).thenReturn(sendObjects.get(0));
        when(applicationContext.getBean("HelpCommand", sendObjects.get(1))).thenReturn(sendObjects.get(1));

        selectTextCommand.setSendObjects(sendObjects);

        // Act
        SendMessageCommand resultStrategy1 = selectTextCommand.getCommandByName("StartCommand");
        SendMessageCommand resultStrategy2 = selectTextCommand.getCommandByName("HelpCommand");

        // Assert
        assertEquals(StartCommand.class, resultStrategy1.getClass());
        assertEquals(HelpCommand.class, resultStrategy2.getClass());
    }
}