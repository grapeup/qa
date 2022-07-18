package dataProviders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class JsonDataReader {
    String data;

    public String readJSONData(String keyword) throws IOException, ParseException{

        Object obj = new JSONParser().parse(new FileReader("src/test/resources/testData/creds.json"));
        JSONObject jo = (JSONObject) obj;
        data = (String)jo.get(keyword);

        return data;

    }

}