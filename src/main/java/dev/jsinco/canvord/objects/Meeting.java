package dev.jsinco.canvord.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class Meeting {

    private final String identifier;
    private final MessageChannel channel;
    private final String message;
    private final LocalDateTime when;
    private final Set<User> attendees; // List of attendee ids


    public boolean shouldSendNow() {
        return LocalDateTime.now().isAfter(when);
    }

    public void send() {
        // Send embed message to the channel
    }

}
