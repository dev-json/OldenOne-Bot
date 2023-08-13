package de.jxson.module.rules;

import de.jxson.command.AbstractCommandAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class RulesCommand extends AbstractCommandAdapter {


    @Override
    public void perform(SlashCommandInteractionEvent event) {
            TextChannel target = event.getOption("channel").getAsChannel().asTextChannel();
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("OldenOnes | Rules");
            eb.setColor(0xF40C0C);
            eb.setDescription("*1. Humanity* \n I don't need to explain more. You all know, how to be friendly. ");
            target.sendMessageEmbeds(eb.build())
                    .addActionRow(
                            Button.primary("accept_rules", "Accept rules")
                    )
                    .queue(response ->
                            event.deferReply(true).queue(InteractionHook::deleteOriginal));
    }

    @Override
    public String getName() {
        return "rules";
    }
}
