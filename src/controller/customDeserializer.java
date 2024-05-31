package controller;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class customDeserializer implements JsonDeserializer<ConfigLoader> {
    @Override
    public ConfigLoader deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ConfigLoader configLoader = ConfigLoader.getInstance();
        JsonObject jsonObject = json.getAsJsonObject();

        int widthTiles = jsonObject.get("widthTiles").getAsInt();
        int heightTiles = jsonObject.get("heightTiles").getAsInt();

        configLoader.setWidthTiles(widthTiles);
        configLoader.setHeightTiles(heightTiles);


        if (jsonObject.has("images")) {
            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            ArrayList<String> list = context.deserialize(jsonObject.get("images"), listType);
            configLoader.setImages(list);
        }

        if (jsonObject.has("initial_ordering")) {
            Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
            ArrayList<Integer> list = context.deserialize(jsonObject.get("initial_ordering"), listType);
            configLoader.setInitial_ordering(list);
        }

        return configLoader;
    }
}
