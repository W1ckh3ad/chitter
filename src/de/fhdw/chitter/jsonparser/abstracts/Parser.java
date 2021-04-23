package de.fhdw.chitter.jsonparser.abstracts;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public abstract class Parser<T> {
    public JSONObject getDefault() {
        return getDefault(new JSONArray());
    }

    public JSONObject getDefault(JSONArray array) {
        JSONObject obj = new JSONObject();
        obj.put("data", array);
        return (JSONObject) obj;
    }

    public JSONObject getDefault(ArrayList<T> list) {
        return getDefault(convertListToJsonArray(list));
    }

    public abstract ArrayList<T> convertJsonArrayToList(JSONArray array);

    public abstract JSONArray convertListToJsonArray(ArrayList<T> list);

}
