package de.jxson.module.reminder;

import de.jxson.Globals;
import de.jxson.util.RandomUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ReminderButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("enable_fl_reminder"))
        {
            ReminderModule.frontlineReminder = true;
            event.reply("Frontline reminder ON").setEphemeral(true).queue();
        }
        else if(event.getButton().getId().equals("disable_fl_reminder"))
        {
            ReminderModule.frontlineReminder = false;
            event.reply("Frontline reminder OFF").setEphemeral(true).queue();
        }
    }
}
