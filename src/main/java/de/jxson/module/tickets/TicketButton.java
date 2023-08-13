package de.jxson.module.tickets;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EnumSet;

public class TicketButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("create_ticket"))
        {
            for(Channel c :  event.getGuild().getChannels())
               if(c.getName().equalsIgnoreCase("ticket_"+event.getMember().getId()))
               {
                   event.getUser().openPrivateChannel().queue((privateChannel) -> privateChannel.sendMessage("There is already a ticket with your id!").queue());
                   return;
               }
            Guild guild = event.getGuild();
            guild.createTextChannel("ticket_"+event.getMember().getId(), event.getGuild().getCategoryById(907733985424199700L))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .queue((textChannel) ->
                    {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Jxson | Support");
                        eb.setColor(0xF40C0C);
                        eb.setDescription("Hi " + event.getUser().getEffectiveName() + ", a team member will be with you shortly to address your concerns.");
                        textChannel.sendMessageEmbeds(eb.build()).queue();
                    });
        }
    }
}
