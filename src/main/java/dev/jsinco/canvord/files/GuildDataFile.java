package dev.jsinco.canvord.files;

import dev.jsinco.canvord.objects.Meeting;
import dev.jsinco.canvord.modules.reminders.WrappedReminder;
import dev.jsinco.discord.framework.settings.AbstractOkaeriConfig;

import java.util.ArrayList;
import java.util.List;

public class GuildDataFile extends AbstractOkaeriConfig {

    public String guildId = "";

    public List<Meeting> meetings = new ArrayList<>();
    public List<WrappedReminder> reminders = new ArrayList<>();
}
