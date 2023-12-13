package com.example.telegrambot.service.text.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectTextCommand {

    private ApplicationContext applicationContext;

    private List<SendMessageCommand> sendObjects;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setSendObjects(List<SendMessageCommand> sendObjects) {
        this.sendObjects = sendObjects;
    }

    public SendMessageCommand getCommandByName(String commandName) {
        if (applicationContext.containsBean(commandName)) {
            return (SendMessageCommand) applicationContext.getBean(commandName,
                    sendObjects.stream().findAny().isPresent());
        } else {
            return applicationContext.getBean(IncorrectCommand.class);
        }

//        return applicationContext.getBean(commandName,
//                sendObjects.stream()
//                        .findAny()
//                        .orElseGet(() -> applicationContext.getBean(IncorrectCommand.class)) //todo попытался, но теперь начал выбрасывать BeanNotOfRequiredTypeException:
//                        .getClass()); //todo старайся никогда не использовать get().
//        //todo там есть команды orElseGet и orElseThrow

    }
}
