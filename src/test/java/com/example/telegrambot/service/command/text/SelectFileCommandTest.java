//package com.example.telegrambot.service.command.text;
//
//import com.example.telegrambot.service.command.SelectCommand;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class SelectFileCommandTest {
//
//    @Test
//    @Disabled
//    void testStrategyPattern() {
//        List<SendTextCommand> sendObjects = List.of(
//                new StartCommand(),
//                new HelpCommand()
//                //and others
//                );
//
//        SelectCommand factoryExample = new SelectCommand(sendObjects);
//        List<SendTextCommand> process = factoryExample.getCommandByName("/start");
//        String actualResult = process.get(0).getClass().getSimpleName();
//
//        assertEquals("StartCommand", actualResult);
//    }
//
//    @Test
//    @Disabled
//    void testStrategyPatternWrong() {
//        List<SendTextCommand> sendObjects = List.of(
//                new StartCommand(),
//                new HelpCommand()
//                //and others
//        );
//
//        SelectCommand factoryExample = new SelectCommand(sendObjects);
//        List<SendTextCommand> process = factoryExample.getCommandByName("/new_command");
//
//        assertTrue(process.isEmpty());
//    }
//}