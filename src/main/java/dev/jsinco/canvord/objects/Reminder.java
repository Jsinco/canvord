package dev.jsinco.canvord.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Reminder {

    private final String identifier;
    private final MessageChannel channel;
    private final String message;
    private final LocalDateTime when;
    private final ReminderFrequency frequency;

    private LocalDateTime lastSent;


    public boolean shouldSendNow() {
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
