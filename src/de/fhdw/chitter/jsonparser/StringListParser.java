package de.fhdw.chitter.jsonparser;

import java.util.ArrayList;

import org.json.simple.JSONArray;

import de.fhdw.chitter.jsonparser.abstracts.Parser;

@SuppressWarnings("unchecked")
public class StringListParser extends Parser<String> {

    @Override
    public JSONArray convertListToJsonArray(ArrayList<String> list) {
        var ret = new JSONArray();
        for (String string : list) {
            ret.add(string);
        }
        return ret;
    }

    @Override
    public ArrayList<String> convertJsonArrayToList(JSONArray array) {
        var ret = new ArrayList<String>();
        for (Object string : array) {
            ret.add(string.toString());
        }
        return ret;
    }
}
