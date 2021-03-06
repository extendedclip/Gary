package me.piggypiglet.gary.core.objects;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import me.piggypiglet.gary.GaryBot;
import me.piggypiglet.gary.core.utils.file.ConfigUtils;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class Config {
    @Inject private ConfigUtils cutil;
    private File config;

    public Config() {
        config = new File("./config.json");
        try {
            if (!config.exists()) {
                if (config.createNewFile()) {
                    if (cutil.exportResource(GaryBot.class.getResourceAsStream("/config.json"), "./config.json")) {
                        System.out.println("Config successfully created!");
                    } else {
                        System.out.println("Config creation failed!");
                    }
                }
            } else {
                System.out.println("Config successfully loaded!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getItem(String item) {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(config));
            Map<String, String> data = gson.fromJson(reader, LinkedTreeMap.class);
            if (data.containsKey(item)) {
                return data.get(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item + " not found in the config.";
    }
}
