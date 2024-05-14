package xyz.mangal.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.mangal.example.command.Command1;
import xyz.mangal.example.config.Config;
import xyz.mangal.example.listener.Listener;
import com.google.gson.*;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.EventBus;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main implements ModInitializer {

    private static final File weaveDir = new File(System.getProperty("user.home"), ".weave");
    public static final Logger LOG = LogManager.getLogger("OWO");
    private static final File modDirectory = new File(weaveDir, "example mod");
    private static JsonObject config;

    @Override
    public void preInit() {
        CommandBus.register(new Command1());
        EventBus.subscribe(new Listener());
        if (!modDirectory.exists() && !modDirectory.mkdir()) {
            LOG.error("Failed to create config directory.");
        }

        String fileName = "config.json";
        File configFile = new File(modDirectory, fileName);
        if (!configFile.exists()) {
            try {
                if (!configFile.createNewFile()) {
                    LOG.error("Failed to create config.");
                }
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JsonParser jsonParser = new JsonParser();
            try (FileReader reader = new FileReader(configFile)) {
                Object obj = jsonParser.parse(reader);
                config = (JsonObject)obj;
            } catch (JsonSyntaxException | ClassCastException | IOException e) {
                e.printStackTrace();
            }
        }
        loadConfig(config);
    }
    public static void saveConfig() {
        try {
            Path path = weaveDir.toPath().resolve("example mod").resolve("config.json");
            JsonObject jo = new JsonObject();
            jo.addProperty("Pos X", Config.posX);
            jo.addProperty("Pos Y", Config.posY);
            jo.addProperty("Text", Config.text);
            Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
            String jsonData = gson.toJson(jo);
            Files.write(path, jsonData.getBytes(StandardCharsets.UTF_8));
        } catch (Throwable t) {
            LOG.error("Failed to save config.", t);
        }
    }
    private static void loadConfig(JsonObject d) {
        try {
            Config.posX = d.get("Pos X").getAsInt();
            Config.posY = d.get("Pos Y").getAsInt();
            Config.text = d.get("Text").getAsString();
        } catch (Throwable t) {
            LOG.error("Failed to load config.", t);
        }
    }
}