package de.jxson.module.reminder;

import de.jxson.Globals;
import de.jxson.command.AbstractCommandAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.ArrayList;
import java.util.List;

public class ReminderCommand extends AbstractCommandAdapter {


    @Override
    public void perform(SlashCommandInteractionEvent event) {

        if(!event.getGuild().getId().equals("1136741050434728007"))
            return;
        Role reminderRole = event.getGuild().getRoleById(Globals.Roles.REMINDER_ROLE);
        event.getGuild().findMembersWithRoles(reminderRole).onSuccess(members -> {
            if(members.size() == 0)
                return;

            List<Member> europeReminder = new ArrayList<>();
            List<Member> usaReminder = new ArrayList<>();
            for(Member member : members)
                for(Role role : member.getRoles())
                    if(role.getId().equals(Globals.Roles.EUROPE_ROLE))
                    {
                        europeReminder.add(member);
                    } else if (role.getId().equals(Globals.Roles.USA_ROLE)){
                        usaReminder.add(member);
                    }

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("OldenOnes | Reminders");
            eb.setColor(0xF40C0C);
            eb.appendDescription("Frontline Reminder | " + (ReminderModule.frontlineReminder ? "**ON**" : "**OFF**") + "\n");
            eb.appendDescription("\n");
            eb.appendDescription("User with @" + event.getGuild().getRoleById(Globals.Roles.REMINDER_ROLE).getAsMention() + " = " + members.size());
            eb.appendDescription("\nUser with @" + event.getGuild().getRoleById(Globals.Roles.EUROPE_ROLE).getAsMention() + " = " + europeReminder.size());
            eb.appendDescription("\nUser with @" + event.getGuild().getRoleById(Globals.Roles.USA_ROLE).getAsMention() + " = " + usaReminder.size());
            event.replyEmbeds(eb.build())
                    .addActionRow(
                            Button.success("enable_fl_reminder", "Enable"),
                            Button.success("disable_fl_reminder", "Disable")
                    )
                    .queue();

        });

    }

    @Override
    public String getName() {
        return "reminder";
    }
}
