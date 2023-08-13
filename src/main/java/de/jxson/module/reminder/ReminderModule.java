package de.jxson.module.reminder;

import de.jxson.Bot;
import de.jxson.module.Module;

public class ReminderModule extends Module {

    public static boolean frontlineReminder;

    @Override
    public void enable() {
        frontlineReminder = false;
        //Multithreading cuz, we dont want to lock our mainthread.. or?
        Thread remindThread = new Thread(new ReminderThread());
        remindThread.start();
        Bot.getCommandMap().put("reminder", new ReminderCommand());
        getDiscordBot().getDiscordJDAInstance().addEventListener(new ReminderButton());
    }

    @Override
    public void disable() {

    }
}
