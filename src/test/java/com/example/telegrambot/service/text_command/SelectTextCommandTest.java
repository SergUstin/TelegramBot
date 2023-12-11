package com.example.telegrambot.service.text_command;

import com.example.telegrambot.service.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectTextCommandTest {

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private List<SendMessageCommand> sendObjects;

    @InjectMocks
    private SelectTextCommand selectTextCommand;

    @Test
    void testGetCommandByName_ExistingCommand_ReturnsSendMessageCommand() {
        // Arrange
        String commandName = "/help";
        HelpCommand expectedCommand = mock(HelpCommand.class);
        when(applicationContext.containsBean(commandName)).thenReturn(true);
        when(applicationContext.getBean(HelpCommand.class)).thenReturn(expectedCommand);

        // Act
        SendMessageCommand result = selectTextCommand.getCommandByName(commandName);

        // Assert
        assertEquals(expectedCommand, result);
    }

    @Test
    void testGetCommandByName_NonExistingCommand_ReturnsIncorrectCommand() {
        // Arrange
        String commandName = "invalidCommand";
        IncorrectCommand expectedCommand = mock(IncorrectCommand.class);
        when(applicationContext.containsBean(commandName)).thenReturn(false);
        when(applicationContext.getBean(IncorrectCommand.class)).thenReturn(expectedCommand);

        // Act
        SendMessageCommand result = selectTextCommand.getCommandByName(commandName);

        // Assert
        assertEquals(expectedCommand, result);
    }

    @Test
    void testGetCommandByName_FactoryCreatesAllStrategyInstances() {
        // Arrange
        String commandName = "existingCommand";
        SendMessageCommand expectedCommand = mock(SendMessageCommand.class);
        when(applicationContext.containsBean(commandName)).thenReturn(true);
        when(applicationContext.getBean(commandName, SendMessageCommand.class)).thenReturn(expectedCommand);
        when(sendObjects.stream().findFirst().orElse(null)).thenReturn(expectedCommand);

        // Act
        SendMessageCommand result = selectTextCommand.getCommandByName(commandName);

        // Assert
        assertEquals(expectedCommand, result);
    }
}