package dev.jsinco.canvord.files;

import dev.jsinco.discord.framework.settings.AbstractOkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Exclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class CanvordConfig extends AbstractOkaeriConfig {

    @Getter
    @Exclude
    private static final CanvordConfig instance = createConfig(CanvordConfig.class, "canvordConfig.yml");


    @Comment({"Privileged user ids that can execute privileged Canvord commands.",
            "Such as, '/presence' and '/show-errors'."})
    private List<String> privilegedUsers = new ArrayList<>();
}
