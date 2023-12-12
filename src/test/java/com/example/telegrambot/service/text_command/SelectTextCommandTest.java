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


import static org.junit.jupiter.api.Assertions.assertEquals;
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
        //не понимаю зачем тебе вот это
        //selectTextCommand.setApplicationContext(applicationContext);
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
        when(applicationContext.containsBean("startCommand")).thenReturn(true);

        //todo тесты лучше делать атомарными. чтобы они пятьсот всего не проверяли. не поняла зачем тут второй класс, если достаточно одного
//        when(applicationContext.containsBean("helpCommand")).thenReturn(true);
        when(applicationContext.getBean("startCommand", StartCommand.class)).thenReturn((StartCommand)sendObjects.get(0));
//        when(applicationContext.getBean("helpCommand", sendObjects.get(1))).thenReturn(sendObjects.get(1));

        selectTextCommand.setSendObjects(sendObjects);

        // Act
        SendMessageCommand resultStrategy1 = selectTextCommand.getCommandByName("startCommand");
//        SendMessageCommand resultStrategy2 = selectTextCommand.getCommandByName("helpCommand");

        // Assert
        assertEquals(StartCommand.class, resultStrategy1.getClass());
//        assertEquals(HelpCommand.class, resultStrategy2.getClass());
    }
}