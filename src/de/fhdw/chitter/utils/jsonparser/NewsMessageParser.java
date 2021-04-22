package de.fhdw.chitter.utils.jsonparser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.utils.jsonparser.abstracts.Parser;

@SuppressWarnings("unchecked")
public class NewsMessageParser extends Parser {

    public static JSONObject convertToJsonObject(NewsMessage model) {
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

    public static NewsMessage convertFromJsonObject(JSONObject obj) {
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

    public static JSONArray convertListToJsonArray(ArrayList<NewsMessage> list) {
        var ret = new JSONArray();
        for (NewsMessage message : list) {
            ret.add(convertToJsonObject(message));
        }
        return ret;
    }

    public static ArrayList<NewsMessage> convertJsonObjectToList(JSONArray array) {
        var ret = new ArrayList<NewsMessage>();
        for (Object message : array) {
            ret.add(NewsMessageParser.convertFromJsonObject((JSONObject) message));
        }
        return ret;
    }
}
