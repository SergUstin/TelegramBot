package com.example.telegrambot.service.command;

<<<<<<< HEAD
public abstract class Command {
    public abstract boolean setCommand(String msg);
=======
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    SendMessage commandSelection();
>>>>>>> origin/master
}
