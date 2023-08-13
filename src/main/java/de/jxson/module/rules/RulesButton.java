package de.jxson.module.rules;

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

public class RulesButton extends ListenerAdapter {

    public static HashMap<User, String> VERIFY_OPEN = new HashMap<>();

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("accept_rules"))
        {
            Member member = event.getMember();
            User user = event.getUser();
            Guild guild = event.getGuild();
            Role memberRole = guild.getRoleById(Globals.Roles.VERIFY_ROLE);
            if(!member.getRoles().contains(memberRole))
            {
                String pass = RandomUtils.generateRandomString(8);
                VERIFY_OPEN.put(user, pass);
                event.getHook().sendMessage("Re-type the password in this channel to verify you are a human: \n ```"+ pass +"```").setEphemeral(true).queue();
                event.deferEdit().queue();
            }
            else
            {
                event.getHook().sendMessage("You already accepted the rules").setEphemeral(true).queue(
                        callback -> callback.delete().queueAfter(5, TimeUnit.SECONDS)
                );
                event.deferEdit().queue();
            }
        }
    }
}
