package de.fhdw.chitter.jsonparser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.jsonparser.abstracts.ComplexModelParser;
import de.fhdw.chitter.models.Staff;

@SuppressWarnings("unchecked")
public class StaffParser extends ComplexModelParser<Staff> {

    @Override
    public JSONArray convertListToJsonArray(ArrayList<Staff> list) {
        var ret = new JSONArray();
        for (Staff staff : list) {
            ret.add(convertToJsonObject(staff));
        }
        return ret;
    }

    @Override
    public ArrayList<Staff> convertJsonArrayToList(JSONArray array) {
        var ret = new ArrayList<Staff>();
        for (Object staff : array) {
            ret.add(convertFromJsonObject((JSONObject) staff));
        }
        return ret;
    }

    @Override
    public JSONObject convertToJsonObject(Staff model) {
        var obj = new JSONObject();
        obj.put("username", model.getUsername());
        obj.put("password", model.getPassword());
        return obj;
    }

    @Override
    public Staff convertFromJsonObject(JSONObject obj) {
        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        return new Staff(username, password);
    }

}
