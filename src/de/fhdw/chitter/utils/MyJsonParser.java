package de.fhdw.chitter.utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import de.fhdw.chitter.models.*;

@SuppressWarnings("unchecked")
public class MyJsonParser {

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

    public static JSONObject convertNewsMessageToJsonObject(NewsMessage model) {
        var obj = new JSONObject();
        var topics = new JSONArray();

        for (String elem : model.getTopics()) {
            topics.add(elem);
        }
        obj.put("text", model.getText());
        obj.put("author", model.getAuthor());
        obj.put("topics", topics);
        obj.put("headline", model.getHeadline());
        obj.put("date", model.getDate());

        return obj;
    }

    public static NewsMessage convertJsonObjectToNewsMessage(JSONObject obj) {
        JSONArray jsonTopics = (JSONArray) obj.get("topics");
        ArrayList<String> topics = new ArrayList<>();
        for (Object object : jsonTopics) {
            topics.add(object.toString());
        }
        String author = (String) obj.get("author");
        String headline = (String) obj.get("headline");
        String text = (String) obj.get("text");
        String date = (String) obj.get("date");

        return new NewsMessage(date, author, topics, headline, text);
    }

    public static JSONArray convertNewsMessageListToJsonArray(ArrayList<NewsMessage> list) {
        var ret = new JSONArray();
        for (NewsMessage message : list) {
            ret.add(convertNewsMessageToJsonObject(message));
        }
        return ret;
    }

    public static ArrayList<NewsMessage> convertJsonObjectToNewsMessageList(JSONArray array) {
        var ret = new ArrayList<NewsMessage>();
        for (Object message : array) {
            ret.add(MyJsonParser.convertJsonObjectToNewsMessage((JSONObject) message));
        }
        return ret;
    }

    public static JSONObject convertStaffToJsonObject(Staff model) {
        var obj = new JSONObject();
        obj.put("username", model.getUsername());
        obj.put("password", model.getPassword());
        return obj;
    }

    public static Staff convertJsonObjectToStaff(JSONObject obj) {
        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        return new Staff(username, password);
    }

    public static JSONArray convertStringListToJsonArray(ArrayList<String> list) {
        var ret = new JSONArray();
        for (String string : list) {
            ret.add(string);
        }
        return ret;
    }

    public static ArrayList<String> convertJsonObjectToStringList(JSONArray array) {
        var ret = new ArrayList<String>();
        for (Object string : array) {
            ret.add(string.toString());
        }
        return ret;
    }

}
