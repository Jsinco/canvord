package dev.jsinco.canvord.objects;

import dev.jsinco.canvord.enums.FrequencyUnit;
import dev.jsinco.discord.framework.serdes.Serdes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReminderFrequency {

    private static final Serdes serdes = Serdes.getInstance();
    private final int frequency;
    private final FrequencyUnit unit;


    @Override
    public String toString() {
        return serdes.serialize(this);
    }

    public static ReminderFrequency fromString(String string) {
        return serdes.deserialize(string, ReminderFrequency.class);
    }

}
