package de.jxson.module.reminder;

import de.jxson.Bot;
import de.jxson.Globals;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderThread implements Runnable {
    boolean running = false;
    @Override
    public void run() {
        try {
            CronExpression expression = new CronExpression("0 0/1 * ? * *");
            CronExpression europeReminderCron = new CronExpression("0 0 10 ? * * *");
            CronExpression usaReminderCron = new CronExpression("0 0 1 ? * * *");
            CronExpression frontlineReminder = new CronExpression("0 45 19 ? * * *"); //yes 15 min before fl starts
            while(true)
            {
                if(expression.isSatisfiedBy(new Date()))
                {
                    if(running) return;
                    running = true;
                    for(Guild guild : Bot.getBot().getDiscordJDAInstance().getGuilds())
                    {
                        if(guild.getId().equals("1136741050434728007")) {
                            List<Member> remindMemberes = guild.findMembers(member -> member.getRoles().stream().anyMatch(role -> role.getId().equals(Globals.Roles.REMINDER_ROLE))).get();

                            if(remindMemberes.size() == 0)
                                return;

                            List<Member> europeReminder = new ArrayList<>();
                            List<Member> usaReminder = new ArrayList<>();
                            for(Member member : remindMemberes)
                                for(Role role : member.getRoles())
                                    if(role.getId().equals(Globals.Roles.EUROPE_ROLE))
                                    {
                                        europeReminder.add(member);
                                    } else if (role.getId().equals(Globals.Roles.USA_ROLE)){
                                        usaReminder.add(member);
                                    }

                            Date currentDate = new Date();
                            if(europeReminderCron.isSatisfiedBy(currentDate))
                            {
                                europeReminder.forEach(member ->
                                {
                                    member.getUser().openPrivateChannel().queue(privateChannel ->
                                    {
                                        privateChannel.sendMessage("Hey! A new day for your MLA experience :D!").queue();
                                    });
                                });
                            }

                            if(usaReminderCron.isSatisfiedBy(currentDate))
                            {
                                usaReminder.forEach(member ->
                                {
                                    member.getUser().openPrivateChannel().queue(privateChannel ->
                                    {
                                        privateChannel.sendMessage("Hey! A new day for your MLA experience :D!").queue();
                                    });
                                });
                            }

                            if(frontlineReminder.isSatisfiedBy(new Date()) && ReminderModule.frontlineReminder)
                            {
                                remindMemberes.forEach(member ->
                                {
                                    member.getUser().openPrivateChannel().queue(privateChannel ->
                                            privateChannel.sendMessage("Get on, its frontline time, we wanna win bitch! " + member.getUser().getAsMention()).queue());
                                });
                            }
                        }
                    }
                    Thread.sleep(1000);
                    running = false;
                }
            }
        } catch (ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
