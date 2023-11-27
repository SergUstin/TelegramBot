package com.example.telegrambot.service.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartCommand extends Command {

    @Override
    public boolean setCommand(String msg) {
        return msg.equals("/start");
    }

}
