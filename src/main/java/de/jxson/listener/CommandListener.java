package de.jxson.listener;

import de.jxson.Bot;
import de.jxson.command.AbstractCommandAdapter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {

        for(AbstractCommandAdapter abstractCommandAdapter : Bot.getCommandMap().values())
        {
            if(event.getName().equals(abstractCommandAdapter.getName()))
            {
                abstractCommandAdapter.perform(event);
            }
        }

    }

}
