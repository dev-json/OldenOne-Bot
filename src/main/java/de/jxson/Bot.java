package de.jxson;

import de.jxson.command.AbstractCommandAdapter;
import de.jxson.database.DataSource;
import de.jxson.database.Database;
import de.jxson.listener.CommandListener;
import de.jxson.module.Module;
import de.jxson.module.reminder.ReminderModule;
import de.jxson.module.rules.RulesModule;
import de.jxson.module.selfroles.SelfrolesModule;
import de.jxson.module.tickets.TicketModule;
import de.jxson.settings.Settings;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bot extends ListenerAdapter {

    private static Bot botInstance;
    private Database database;
    private JDA discordJDAInstance;

    private final List<Module> modules;
    private static final HashMap<String, AbstractCommandAdapter> COMMAND_MAP = new HashMap<>();

    public Bot()
    {
        Bot.botInstance = this;

        this.modules = new ArrayList<>();

        Settings.load();

        Settings.load();

        JDABuilder botBuilder = JDABuilder.createDefault(Settings.BOT_TOKEN.getValue());
        botBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        botBuilder.setBulkDeleteSplittingEnabled(false);
        botBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);
        botBuilder.setActivity(Activity.playing("playing with tsu"));
        try {
            // create the instance of JDA
            discordJDAInstance = botBuilder.build();

            //Module logic here
            modules.add(new RulesModule());
            modules.add(new SelfrolesModule());
            modules.add(new ReminderModule());

            for(Module module : modules)
                module.enable();

            discordJDAInstance.updateCommands().addCommands(
                    Commands.slash("rules", "Print the Rules in a channel")
                            .addOption(OptionType.CHANNEL, "channel", "Which channel", true)
                            .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                    Commands.slash("selfrole", "Print the selfrroles in a channel")
                            .addOption(OptionType.CHANNEL, "channel", "Which channel", true)
                            .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                    Commands.slash("reminder", "status info about the reminder module")
                            .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
            ).queue();

            discordJDAInstance.addEventListener(this, new CommandListener());

            // optionally block until JDA is ready
            discordJDAInstance.awaitReady();
        } catch (InterruptedException e) {
            System.err.println("Couldn't login.");
            e.printStackTrace();
        }

    }

    public static Bot getBot() {
        return botInstance;
    }

    public Database getDatabase() {
        return database;
    }

    public JDA getDiscordJDAInstance() {
        return discordJDAInstance;
    }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot is ready! Beep, Boop!");
    }

    public static HashMap<String, AbstractCommandAdapter> getCommandMap() {
        return COMMAND_MAP;
    }

    public static void main(String[] args) {
        new Bot();
    }
}
