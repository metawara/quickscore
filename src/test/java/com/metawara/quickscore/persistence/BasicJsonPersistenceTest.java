package com.metawara.quickscore.persistence;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest(classes = BasicJsonPersistenceTest.class)
@RunWith(MockitoJUnitRunner.class)
public class BasicJsonPersistenceTest {

    BasicJsonPersistence persistence = new BasicJsonPersistence("testfile.json");

    @Test
    public void testSaving(){
        JsonObject object = new JsonObject();
        object.add("Object", new JsonArray());

        persistence.persist(object);
        assertNotEquals("", "a");

    }

    @Test
    public void testReading(){
        System.out.println(persistence.retrieve().toString());
        assertNotEquals("", persistence.retrieve().toString());
    }
}
