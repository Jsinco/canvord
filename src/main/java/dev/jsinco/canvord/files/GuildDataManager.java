package dev.jsinco.canvord.files;

import dev.jsinco.discord.framework.settings.AbstractOkaeriConfig;
import eu.okaeri.configs.json.gson.JsonGsonConfigurer;
import lombok.Getter;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Getter
public class GuildDataManager {

    @Getter
    private static final GuildDataManager instance = new GuildDataManager();

    private final Map<String, GuildDataFile> loadedGuildDataFiles;
    private final Path guildDataFolderPath;

    private GuildDataManager() {
        this.loadedGuildDataFiles = new HashMap<>();
        this.guildDataFolderPath = AbstractOkaeriConfig.dataFolderPath.resolve("guilds");
        this.loadGuildDataFiles();
    }

    private void saveLoadedGuildDataFiles(boolean async) {
        if (async) {
            new Thread(() -> {
                for (GuildDataFile guildDataFile : loadedGuildDataFiles.values()) {
                    guildDataFile.save();
                }
            }).start();
        } else {
            for (GuildDataFile guildDataFile : loadedGuildDataFiles.values()) {
                guildDataFile.save();
            }
        }
    }

    private void loadGuildDataFiles() {
        File guildDataFolder = guildDataFolderPath.toFile();

        if (!guildDataFolder.exists()) {
            guildDataFolder.mkdirs();
        }

        File[] files = guildDataFolder.listFiles();

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                GuildDataFile guildDataFile = AbstractOkaeriConfig.createConfig(GuildDataFile.class, guildDataFolderPath, fileName, new JsonGsonConfigurer());
                loadedGuildDataFiles.put(guildDataFile.guildId, guildDataFile);
            }
        }
    }

    public GuildDataFile getGuildDataFile(String guildId) {
        if (loadedGuildDataFiles.containsKey(guildId)) {
            return loadedGuildDataFiles.get(guildId);
        }

        GuildDataFile guildDataFile = AbstractOkaeriConfig.createConfig(GuildDataFile.class, guildDataFolderPath, guildId + ".json", new JsonGsonConfigurer());
        loadedGuildDataFiles.put(guildId, guildDataFile);
        return guildDataFile;
    }
}
