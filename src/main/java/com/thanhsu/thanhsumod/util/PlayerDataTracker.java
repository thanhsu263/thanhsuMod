package com.thanhsu.thanhsumod.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thanhsu.thanhsumod.ThanhSuMod;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerDataTracker {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_DIR = "config/thanhsumod";
    private static final String DATA_FILE = "visited_players.json";
    private static final String VIDEO_FILE = "memorial.mp4";
    private static Set<String> visitedPlayers = new HashSet<>();
    private static File dataFile;
    private static File videoFile;

    public static void init() {
        try {
            // Create config directory
            File configDir = new File(CONFIG_DIR);
            if (!configDir.exists()) {
                configDir.mkdirs();
            }

            // Load player data
            dataFile = new File(configDir, DATA_FILE);
            if (dataFile.exists()) {
                loadData();
            } else {
                saveData();
            }

            // Copy video to AnimatedFrames media folder
            copyVideoToAnimatedFrames();
        } catch (Exception e) {
            ThanhSuMod.LOGGER.error("Failed to initialize PlayerDataTracker", e);
        }
    }

    private static void copyVideoToAnimatedFrames() {
        try {
            // AnimatedFrames media folder: .minecraft/config/animatedframes/media/
            File configDir = new File(CONFIG_DIR);
            File gameDir = configDir.getParentFile(); // Go up one level from config/thanhsumod to config/
            File animatedFramesMedia = new File(gameDir, "animatedframes/media");

            if (!animatedFramesMedia.exists()) {
                animatedFramesMedia.mkdirs();
            }

            videoFile = new File(animatedFramesMedia, VIDEO_FILE);
            if (!videoFile.exists()) {
                InputStream videoStream = PlayerDataTracker.class.getResourceAsStream(
                        "/assets/thanhsumod/videos/memorial.mp4");

                if (videoStream != null) {
                    Files.copy(videoStream, videoFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    videoStream.close();
                    ThanhSuMod.LOGGER.info("Copied memorial video to " + videoFile.getAbsolutePath());
                } else {
                    ThanhSuMod.LOGGER.warn("Memorial video not found in mod resources");
                }
            } else {
                ThanhSuMod.LOGGER.info("Memorial video already exists at " + videoFile.getAbsolutePath());
            }
        } catch (IOException e) {
            ThanhSuMod.LOGGER.error("Failed to copy video", e);
        }
    }

    public static String getVideoPath() {
        return videoFile != null ? videoFile.getAbsolutePath() : null;
    }

    private static void loadData() {
        try (FileReader reader = new FileReader(dataFile)) {
            Type setType = new TypeToken<HashSet<String>>(){}.getType();
            Set<String> loaded = GSON.fromJson(reader, setType);
            if (loaded != null) {
                visitedPlayers = loaded;
            }
            ThanhSuMod.LOGGER.info("Loaded {} visited players from config", visitedPlayers.size());
        } catch (IOException e) {
            ThanhSuMod.LOGGER.error("Failed to load player data", e);
        }
    }

    private static void saveData() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            GSON.toJson(visitedPlayers, writer);
        } catch (IOException e) {
            ThanhSuMod.LOGGER.error("Failed to save player data", e);
        }
    }

    public static boolean hasVisited(UUID playerUUID) {
        return visitedPlayers.contains(playerUUID.toString());
    }

    public static void markAsVisited(UUID playerUUID) {
        visitedPlayers.add(playerUUID.toString());
        saveData();
    }

    public static void clearVisited(UUID playerUUID) {
        visitedPlayers.remove(playerUUID.toString());
        saveData();
    }
}
