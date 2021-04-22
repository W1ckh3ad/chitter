package de.fhdw.chitter.utils.jsonparser.abstracts;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public abstract class Parser {
    public static JSONObject getDefault() {
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONArray());
        return (JSONObject) obj;
    }

    public static JSONObject getDefault(JSONArray array) {
        JSONObject obj = new JSONObject();
        obj.put("data", array);
        return (JSONObject) obj;
    }
}
