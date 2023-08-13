package de.jxson.module.rules;

import de.jxson.Globals;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class RulesPM extends ListenerAdapter  {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getChannel().getId().equals(Globals.Channels.RULES_CHANNEL) || event.getChannel().getId().equals(Globals.Channels.BOT_SHIT_CHANNEL))
        {
            Role memberRole = event.getGuild().getRoleById(Globals.Roles.VERIFY_ROLE);
            User user = event.getAuthor();
            if(RulesButton.VERIFY_OPEN.containsKey(user))
            {
                String confirmation = event.getMessage().getContentDisplay();
                if(RulesButton.VERIFY_OPEN.get(user).equals(confirmation))
                {
                    event.getMessage().reply("You are now verified!").queue(message -> {
                        message.delete().queueAfter(5, TimeUnit.SECONDS);
                    });
                    event.getGuild().addRoleToMember(user, memberRole).queue();
                    RulesButton.VERIFY_OPEN.remove(user);
                }
                else
                {
                    event.getMessage().reply("The password is wrong!").queue(message -> {
                        message.delete().queueAfter(5, TimeUnit.SECONDS);
                    });
                }
                event.getMessage().delete().queue();
            }
            if(event.getChannel().getId().equals(Globals.Channels.RULES_CHANNEL))
            {
                event.getMessage().delete().queue();
            }
        }
    }
}
