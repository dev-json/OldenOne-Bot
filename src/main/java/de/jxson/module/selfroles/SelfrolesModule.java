package de.jxson.module.selfroles;

import de.jxson.Bot;
import de.jxson.module.Module;
import net.dv8tion.jda.api.JDA;

public class SelfrolesModule extends Module {
    @Override
    public void enable() {
        JDA jda = getDiscordBot().getDiscordJDAInstance();

        Bot.getCommandMap().put("selfrole", new SelfRolesCommand());
        jda.addEventListener(new SelfRolesButton());
    }

    @Override
    public void disable() {

    }
}
