package de.jxson.module.tickets;

import de.jxson.command.AbstractCommandAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class TicketCommand extends AbstractCommandAdapter {
    @Override
    public void perform(SlashCommandInteractionEvent event) {
        TextChannel target = event.getOption("channel").getAsChannel().asTextChannel();
        if(event.getMember().getId().equals("287874774204547073"))
        {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Jxson | Support");
            eb.setColor(0xF40C0C);
            eb.setDescription("Create a support ticket and a team member will take care of your questions and concerns shortly!\n" +
                    "Abuse of the ticket system will be sanctioned!");
            target.sendMessageEmbeds(eb.build())
                    .addActionRow(
                            Button.primary("create_ticket", "New Ticket")
                    )
                    .queue();
        }
        else
            event.getUser().openPrivateChannel().queue((privateChannel) -> privateChannel.sendMessage("No permissions!").queue());

    }

    @Override
    public String getName() {
        return "ticket";
    }
}
