package dev.jsinco.canvord.commands;

import dev.jsinco.canvord.files.CanvordConfig;
import dev.jsinco.canvord.utility.Util;
import dev.jsinco.discord.framework.FrameWork;
import dev.jsinco.discord.framework.commands.CommandModule;
import dev.jsinco.discord.framework.commands.DiscordCommand;
import dev.jsinco.discord.framework.reflect.InjectStatic;
import dev.jsinco.discord.framework.settings.Settings;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.Presence;

import java.util.List;

/**
 * A command module for setting the presence of JDA (discord bot).
 * @author Jonah
 */
@DiscordCommand(name = "presence", description = "Set the presence of this bot.", guildOnly = false)
public class PresenceCommand implements CommandModule {

    @InjectStatic(FrameWork.class)
    private static JDA jda;
    @InjectStatic(CanvordConfig.class)
    private static CanvordConfig config;


    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (!config.getPrivilegedUsers().contains(event.getUser().getId())) {
            event.reply("You must be a privileged user to use this command.").setEphemeral(true).queue();
            return;
        }


        OnlineStatus status = Util.getEnum(OnlineStatus.class, Util.getOption(event.getOption("status"), OptionType.STRING, "ONLINE"));
        Activity.ActivityType activityType = Util.getEnum(Activity.ActivityType.class, Util.getOption(event.getOption("activity-type"), OptionType.STRING));
        String activity = Util.getOption(event.getOption("activity"), OptionType.STRING, "dev.jsinco.discord");

        Presence presence = jda.getPresence();
        presence.setStatus(status);
        presence.setActivity(activityType == null ? null : Activity.of(activityType, activity));

        Settings settings = Settings.getInstance();
        settings.setDefaultStatus(status);
        settings.setDefaultActivityType(activityType);
        settings.setDefaultActivity(activity);
        settings.save();
        event.reply("Presence updated.").setEphemeral(true).queue();
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING, "status", "The online status of this bot.", true).addChoices(Util.buildChoicesFromEnum(OnlineStatus.class, "UNKNOWN")),
                new OptionData(OptionType.STRING, "activity-type", "The type of activity this bot is doing.", false).addChoices(Util.buildChoicesFromEnum(Activity.ActivityType.class)),
                new OptionData(OptionType.STRING, "activity", "The activity this bot is doing.", false)
        );
    }
}
