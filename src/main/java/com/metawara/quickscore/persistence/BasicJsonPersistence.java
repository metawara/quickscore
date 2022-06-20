package com.metawara.quickscore.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

public class BasicJsonPersistence implements LocalJsonPersistence {

    private final String filepath;

    public BasicJsonPersistence(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void persist(JsonObject object) {
        try (FileWriter file = new FileWriter(filepath)) {
            file.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonObject retrieve() {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            return new Gson().fromJson(br, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }
}
