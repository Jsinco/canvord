package dev.jsinco.canvord;

import dev.jsinco.canvord.console.PrivilegedUserConsoleCommand;
import dev.jsinco.canvord.utility.StringUtil;
import dev.jsinco.discord.framework.FrameWork;
import dev.jsinco.discord.framework.console.ConsoleCommandManager;

import java.io.File;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path dataFolderPath = setupDataFolder();
        System.out.println("Using " + dataFolderPath + " as data folder.");

        FrameWork.start(Main.class, dataFolderPath);

        ConsoleCommandManager.getInstance().registerCommand(new PrivilegedUserConsoleCommand());
    }


    private static Path setupDataFolder() {
        String newDataFolderPath = StringUtil.getFromEnvironment("data_folder");
        File folder = getDefaultDataFolder();

        if (newDataFolderPath != null) {
            folder = new File(newDataFolderPath);

            if (!folder.exists()) {
                folder.mkdirs();
                System.out.println("Created new data folder at " + folder.getPath() + ".");
            }

            if (!folder.isDirectory()) {
                System.out.println("Provided data folder is not a directory. Using default data folder instead.");
            }
        }
        return folder.toPath();
    }

    private static File getDefaultDataFolder() {
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarFile = new File(path);
        String jarDir = jarFile.getParentFile().getAbsolutePath();

        File dataFolder = new File(jarDir + File.separator + "canvord_data");
        dataFolder.mkdirs();

        return dataFolder;
    }

}