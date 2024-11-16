package dev.jsinco.canvord.objects;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Meeting extends Reminder {

    private final Set<User> attendees; // List of attendee ids

    public Meeting(String identifier, MessageChannel channel, String message, LocalDateTime when, ReminderFrequency frequency, LocalDateTime lastSent) {
        super(identifier, channel, message, when, frequency, lastSent);
        this.attendees = new HashSet<>();
    }


    public boolean shouldRemindOfMeeting() {
        return super.shouldSendNow();
    }

    public boolean shouldSendNow() {
        return LocalDateTime.now().isAfter(when);
    }

    public void send() {
        // Send embed message to the channel
        EmbedBuilder embed = new EmbedBuilder();

    }

}
