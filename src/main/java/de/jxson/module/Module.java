package de.jxson.module;

import de.jxson.Bot;

public abstract class Module {

    private static Bot discordBot;

    public Module()
    {
        discordBot = Bot.getBot();
    }

    public abstract void enable();
    public abstract void disable();

    public static Bot getDiscordBot() {
        return discordBot;
    }
}
