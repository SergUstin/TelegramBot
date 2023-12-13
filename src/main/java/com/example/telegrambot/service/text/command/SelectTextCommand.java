package com.example.telegrambot.service.text.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Component
public class SelectTextCommand {

    private final ApplicationContext applicationContext;

    private final List<SendMessageCommand> sendObjects;

    @Autowired
    public SelectTextCommand(ApplicationContext applicationContext, List<SendMessageCommand> sendObjects) {
        this.applicationContext = applicationContext;
        this.sendObjects = sendObjects;
    }

    public SendMessageCommand getCommandByName(String commandName) {

        return (SendMessageCommand) applicationContext
                .getBean(commandName,
                        sendObjects.stream()
                                .filter(sendMessageCommand -> applicationContext.containsBean(commandName))
                                .findFirst()
                                .orElseGet(() -> applicationContext.getBean(IncorrectCommand.class)));

//        return applicationContext.getBean(commandName,
//                sendObjects.stream()
//                        .findAny()
//                        .orElseGet(() -> applicationContext.getBean(IncorrectCommand.class)) //todo попытался, но теперь начал выбрасывать BeanNotOfRequiredTypeException:
//                        .getClass()); //todo старайся никогда не использовать get().
        //todo там есть команды orElseGet и orElseThrow

    }
}
