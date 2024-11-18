package dev.jsinco.canvord.console;

import dev.jsinco.canvord.files.CanvordConfig;
import dev.jsinco.canvord.utility.Util;
import dev.jsinco.discord.framework.console.ConsoleCommand;
import dev.jsinco.discord.framework.logging.FrameWorkLogger;

public class PrivilegedUserConsoleCommand implements ConsoleCommand {

    private enum Action {
        ADD,
        REMOVE
    }

    @Override
    public String name() {
        return "privilege";
    }

    @Override
    public void execute(String[] strings) {
        if (strings.length < 2) {
            FrameWorkLogger.info("Usage: privilege <add|remove> <user_id>");
            return;
        }

        Action action = Util.getEnum(Action.class, strings[1], Action.ADD);
        String userId = strings[2];

        CanvordConfig config = CanvordConfig.getInstance();

        switch (action) {
            case ADD -> {
                config.getPrivilegedUsers().add(userId);
                FrameWorkLogger.info("Added " + userId + " to privileged users list.");
            }
            case REMOVE -> {
                config.getPrivilegedUsers().remove(userId);
                FrameWorkLogger.info("Removed " + userId + " from privileged users list.");
            }
        }

        config.save();
    }
}
