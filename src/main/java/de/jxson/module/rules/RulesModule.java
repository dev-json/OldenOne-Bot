package de.jxson.module.rules;

import de.jxson.Bot;
import de.jxson.module.Module;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class RulesModule extends Module
{
    @Override
    public void enable() {
        JDA jda = getDiscordBot().getDiscordJDAInstance();

        Bot.getCommandMap().put("rules", new RulesCommand());
        jda.addEventListener(new RulesButton());
        jda.addEventListener(new RulesPM());
    }

    @Override
    public void disable() {

    }

}
