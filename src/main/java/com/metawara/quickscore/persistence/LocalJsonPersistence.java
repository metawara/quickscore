package com.metawara.quickscore.persistence;

import com.google.gson.JsonObject;

public interface LocalJsonPersistence {

    void persist(JsonObject object);

    JsonObject retrieve();
}
