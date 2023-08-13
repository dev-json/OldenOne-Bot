package de.jxson.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommandAdapter {

    public static List<AbstractCommandAdapter> commandAdapters = new ArrayList<>();

    public abstract void perform(SlashCommandInteractionEvent event);

    public abstract String getName();

}
