package de.jxson.module.tickets;

import de.jxson.Bot;
import de.jxson.module.Module;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

@Deprecated(forRemoval = true)
public class TicketModule extends Module {
    /**
     * Ignore the ticket module, its from my own bot.
     * the updates commands are overwriting other commands so its a technical TODO :D
     */
    @Override
    public void enable() {
        JDA jda = getDiscordBot().getDiscordJDAInstance();
        jda.updateCommands().addCommands(
                Commands.slash("ticket", "Print the ticket thing")
                        .addOption(OptionType.CHANNEL, "channel", "Which channel", true)
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
        ).queue();
        Bot.getCommandMap().put("ticket", new TicketCommand());
        jda.addEventListener(new TicketButton());
    }

    @Override
    public void disable() {

    }
}
