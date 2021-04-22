package de.fhdw.chitter.utils.jsonparser;

import java.util.ArrayList;

import org.json.simple.JSONArray;

import de.fhdw.chitter.utils.jsonparser.abstracts.Parser;

@SuppressWarnings("unchecked")
public class StringListParser extends Parser {
    public static JSONArray convertToJsonArray(ArrayList<String> list) {
        var ret = new JSONArray();
        for (String string : list) {
            ret.add(string);
        }
        return ret;
    }

    public static ArrayList<String> convertFromJsonObject(JSONArray array) {
        var ret = new ArrayList<String>();
        for (Object string : array) {
            ret.add(string.toString());
        }
        return ret;
    }
}
