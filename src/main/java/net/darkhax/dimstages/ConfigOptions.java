package net.darkhax.dimstages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ConfigOptions {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    public boolean ignoreCreativeMode = false;

    @Expose
    public String notice = "This version of Dimension Stages uses CraftTweaker scripts to restrict dimensions. You do not use this file to do that!";

    public static ConfigOptions load(File configFile) {

        ConfigOptions config = new ConfigOptions();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, ConfigOptions.class);
                DimensionStages.LOG.info("Loaded config file.");
            }

            catch (Exception e) {

                DimensionStages.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath());
                DimensionStages.LOG.catching(e);
            }
        }

        else {

            DimensionStages.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
            DimensionStages.LOG.info("Saved config file.");
        }

        catch (Exception e) {

            DimensionStages.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath());
            DimensionStages.LOG.catching(e);
        }

        return config;
    }
}