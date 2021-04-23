package de.fhdw.chitter.jsonparser;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.jsonparser.abstracts.ComplexModelParser;
import de.fhdw.chitter.models.NewsMessage;

@SuppressWarnings("unchecked")
public class NewsMessageParser extends ComplexModelParser<NewsMessage> {

    public JSONObject convertToJsonObject(NewsMessage model) {
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

    @Override
    public NewsMessage convertFromJsonObject(JSONObject obj) {
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

    @Override
    public JSONArray convertListToJsonArray(ArrayList<NewsMessage> list) {
        var ret = new JSONArray();
        for (NewsMessage message : list) {
            ret.add(convertToJsonObject(message));
        }
        return ret;
    }

    @Override
    public ArrayList<NewsMessage> convertJsonArrayToList(JSONArray array) {
        var ret = new ArrayList<NewsMessage>();
        for (Object message : array) {
            ret.add(convertFromJsonObject((JSONObject) message));
        }
        return ret;
    }
}
