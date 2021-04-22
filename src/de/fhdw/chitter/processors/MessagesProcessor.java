package de.fhdw.chitter.processors;

import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.jsonparser.NewsMessageParser;
import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.processors.abstracts.Processor;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MessagesProcessor extends Processor {

    private static MessagesProcessor instance;

    private MessagesProcessor() {
        super("data/messages.json");
    }

    public static MessagesProcessor getInstance() {
        if (instance == null) {
            instance = new MessagesProcessor();
        }
        return instance;
    }

    private ArrayList<NewsMessage> transform() {
        try {
            JSONArray messages = (JSONArray) read().get("data");
            return NewsMessageParser.convertJsonObjectToList(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<NewsMessage>();
        }
    }

    private void save(ArrayList<NewsMessage> list) {
        var jsonList = NewsMessageParser.convertListToJsonArray(list);
        var obj = (JSONObject) NewsMessageParser.getDefault(jsonList);
        try {
            MyFileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NewsMessage> get() {
        return transform();
    }

    public ArrayList<NewsMessage> get(String topic, int count, boolean caseSensitive) {
        var ret = new ArrayList<NewsMessage>();
        var list = transform();
        if (caseSensitive) {
            topic = topic.toLowerCase();
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            var newsMessage = list.get(i);
            if (count < 1) {
                break;
            }
            var hashtag = "#" + topic;
            if (caseSensitive && newsMessage.getText().toLowerCase().contains(hashtag)) {
                count--;
                ret.add(newsMessage);
                continue;
            }
            if (!caseSensitive && newsMessage.getText().contains(hashtag)) {
                count--;
                ret.add(newsMessage);
                continue;
            }
            for (String msg_topic : newsMessage.getTopics()) {
                if (!caseSensitive && msg_topic.equals(topic)) {
                    count--;
                    ret.add(newsMessage);
                    break;
                }
                if (caseSensitive && msg_topic.toLowerCase().equals(topic)) {
                    count--;
                    ret.add(newsMessage);
                    break;
                }
            }

        }
        return ret;
    }

    public ArrayList<NewsMessage> get(String topic, int count) {
        return get(topic, count, false);
    }

    public ArrayList<NewsMessage> get(int count) {
        var list = transform();
        var ret = new ArrayList<NewsMessage>();
        for (int i = list.size() - 1; i >= 0; i--) {
            var newsMessage = list.get(i);
            if (count < 1) {
                break;
            }
            count--;
            ret.add(newsMessage);
        }
        return ret;
    }

    public void post(NewsMessage message) {
        var list = transform();
        list.add(message);
        save(list);
    }
}
