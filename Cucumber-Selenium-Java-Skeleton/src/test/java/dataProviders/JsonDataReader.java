package dataProviders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class JsonDataReader {
    private final JSONParser parser = new JSONParser();
    public String readJSONData(String keyword) throws IOException, ParseException{
        Object obj = parser.parse(new FileReader("src/test/resources/testData/creds.json"));
        JSONObject jo = (JSONObject) obj;
        return (String)jo.get(keyword);
    }
}