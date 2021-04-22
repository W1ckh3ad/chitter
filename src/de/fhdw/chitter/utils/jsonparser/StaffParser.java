package de.fhdw.chitter.utils.jsonparser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.models.Staff;
import de.fhdw.chitter.utils.jsonparser.abstracts.Parser;

@SuppressWarnings("unchecked")
public class StaffParser extends Parser {
    public static JSONArray converListToJsonArray(ArrayList<Staff> list) {
        var ret = new JSONArray();
        for (Staff staff : list) {
            ret.add(convertToJsonObject(staff));
        }
        return ret;
    }

    public static ArrayList<Staff> convertJsonObjectToList(JSONArray array) {
        var ret = new ArrayList<Staff>();
        for (Object staff : array) {
            ret.add(convertFromJsonObject((JSONObject) staff));
        }
        return ret;
    }

    public static JSONObject convertToJsonObject(Staff model) {
        var obj = new JSONObject();
        obj.put("username", model.getUsername());
        obj.put("password", model.getPassword());
        return obj;
    }

    public static Staff convertFromJsonObject(JSONObject obj) {
        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        return new Staff(username, password);
    }

}
