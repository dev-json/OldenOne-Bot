package de.jxson.module.selfroles;

import de.jxson.Globals;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class SelfRolesButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        Member member = event.getMember();
        User user = event.getUser();
        Guild guild = event.getGuild();
        Role reminderRole = guild.getRoleById(Globals.Roles.REMINDER_ROLE);
        Role europeRole = guild.getRoleById(Globals.Roles.EUROPE_ROLE);
        Role usaRole = guild.getRoleById(Globals.Roles.USA_ROLE);

        if(event.getButton().getId().equals("reminder_role_btn"))
        {

            if(!member.getRoles().contains(reminderRole))
            {

                event.getHook().sendMessage("You got the reminder role").setEphemeral(true).queue();
                event.getGuild().addRoleToMember(user, reminderRole).queue();
                event.deferEdit().queue();
            }
            else
            {
                event.getHook().sendMessage("You no longer have the reminder role").setEphemeral(true).queue();
                event.getGuild().removeRoleFromMember(user, reminderRole).queue();
                event.deferEdit().queue();
            }
        }

        if(event.getButton().getId().equals("reminder_europe_btn"))
        {

                if(!member.getRoles().contains(europeRole))
                {
                    event.getHook().sendMessage("You got the europe reminder role").setEphemeral(true).queue();
                    event.getGuild().addRoleToMember(user, europeRole).queue();
                    event.deferEdit().queue();
                    if(!member.getRoles().contains(reminderRole))
                    {
                        event.getGuild().addRoleToMember(user, reminderRole).queue();
                        event.deferEdit().queue();
                    }
                }
                else
                {
                    event.getHook().sendMessage("You no longer have the europe reminder role").setEphemeral(true).queue();
                    event.getGuild().removeRoleFromMember(user, europeRole).queue();
                    event.deferEdit().queue();
                }

        }

        if(event.getButton().getId().equals("reminder_usa_btn"))
        {
            if(!member.getRoles().contains(usaRole))
            {

                event.getHook().sendMessage("You got the usa reminder role").setEphemeral(true).queue();
                event.getGuild().addRoleToMember(user, usaRole).queue();
                event.deferEdit().queue();
                if(!member.getRoles().contains(reminderRole))
                {
                    event.getGuild().addRoleToMember(user, reminderRole).queue();
                    event.deferEdit().queue();
                }
            }
            else
            {
                event.getHook().sendMessage("You no longer have the usa reminder role").setEphemeral(true).queue();
                event.getGuild().removeRoleFromMember(user, usaRole).queue();
                event.deferEdit().queue();
            }
        }
    }
}
