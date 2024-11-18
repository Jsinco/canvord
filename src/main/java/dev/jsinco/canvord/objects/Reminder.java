package dev.jsinco.canvord.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reminder {

    protected final String identifier;
    protected final MessageChannel channel;
    protected final String message;
    protected final LocalDateTime when;
    protected final ReminderFrequency frequency;

    protected LocalDateTime lastSent;

    public Reminder(String identifier, MessageChannel channel, String message, LocalDateTime when, ReminderFrequency frequency) {
        this.identifier = identifier;
        this.channel = channel;
        this.message = message;
        this.when = when;
        this.frequency = frequency;
    }


    public boolean shouldRemind() {
        boolean frequencyTruth;
        if (lastSent == null) {
            frequencyTruth = true;
        } else {
            frequencyTruth = switch (frequency.getUnit()) {
                case NEVER -> false;
                case MINUTE -> lastSent.plusMinutes(frequency.getFrequency()).isBefore(LocalDateTime.now());
                case HOUR -> lastSent.plusHours(frequency.getFrequency()).isBefore(LocalDateTime.now());
                case DAY -> lastSent.plusDays(frequency.getFrequency()).isBefore(LocalDateTime.now());
                case WEEK -> lastSent.plusWeeks(frequency.getFrequency()).isBefore(LocalDateTime.now());
                case MONTH -> lastSent.plusMonths(frequency.getFrequency()).isBefore(LocalDateTime.now());
                case YEAR -> lastSent.plusYears(frequency.getFrequency()).isBefore(LocalDateTime.now());
            };
        }

        return frequencyTruth && LocalDateTime.now().isAfter(when);
    }
}
