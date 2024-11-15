package dev.jsinco.canvord.console;

import dev.jsinco.canvord.files.CanvordConfig;
import dev.jsinco.canvord.utility.Util;
import dev.jsinco.discord.framework.console.ConsoleCommand;

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
            System.out.println("Usage: privilege <add|remove> <user_id>");
            return;
        }

        Action action = Util.getEnum(Action.class, strings[0], Action.ADD);
        String userId = strings[1];

        CanvordConfig config = CanvordConfig.getInstance();

        switch (action) {
            case ADD -> {
                config.getPrivilegedUsers().add(userId);
                System.out.println("Added " + userId + " to privileged users list.");
            }
            case REMOVE -> {
                config.getPrivilegedUsers().remove(userId);
                System.out.println("Removed " + userId + " from privileged users list.");
            }
        }

        config.save();
    }
}
