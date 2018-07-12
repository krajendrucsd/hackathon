package data.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import data.model.DataSets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonParser {

    public static DataSets parseJson(String fileName) {
        String filePath = new File("").getAbsolutePath();
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader(filePath + "\\src\\main\\resources\\" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        if (jsonReader != null) {
            return gson.fromJson(jsonReader, DataSets.class);
        }
        return null;
    }
}