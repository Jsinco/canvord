package dev.jsinco.canvord.objects;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class Meeting extends Reminder {


    private final Set<User> attendees; // List of attendee ids

    public Meeting(String identifier, MessageChannel channel, String message, LocalDateTime when, ReminderFrequency frequency, Set<User> attendees) {
        super(identifier, channel, message, when, frequency);
        this.attendees = attendees;
    }


    public boolean shouldSendNow() {
        return LocalDateTime.now().isAfter(when);
    }

    public void send() {
        // Send embed message to the channel
    }

}
