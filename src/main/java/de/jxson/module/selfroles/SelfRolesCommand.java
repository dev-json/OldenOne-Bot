package de.jxson.module.selfroles;

import de.jxson.command.AbstractCommandAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class SelfRolesCommand extends AbstractCommandAdapter {
    @Override
    public void perform(SlashCommandInteractionEvent event) {
        TextChannel target = event.getOption("channel").getAsChannel().asTextChannel();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("OldenOnes | Roles");
        eb.setColor(0xF40C0C);
        eb.setDescription("**Click to get a role or remove it**\n" +
                "Reminder -> Reminds you daily for your tasks (FL, GBR, DM and more...) \n" +
                "Europe -> Reminder for 10:00 AM \n" +
                "USA -> Reminder for 01:00 AM");

        target.sendMessageEmbeds(eb.build())
                .addActionRow(
                        Button.success("reminder_role_btn", "Reminder Role"),
                        Button.primary("reminder_europe_btn", "Europe"),
                        Button.primary("reminder_usa_btn", "USA")
                )
                .queue(response ->
                {
                    event.deferReply(true).queue(InteractionHook::deleteOriginal);
                });
    }

    @Override
    public String getName() {
        return "selfrole";
    }
}
