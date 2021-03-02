package xyz.d1snin.emily.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;

public class readTokenFromJson {
    public static String readJson() {
        try {

            JSONParser parser = new JSONParser();
            File tokenFile = new File("token.json");
            BufferedReader reader = new BufferedReader(new FileReader(tokenFile.getAbsoluteFile()));
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            return (String) jsonObject.get("token");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

