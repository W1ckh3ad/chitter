package de.fhdw.chitter.processors;

import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.MyJsonParser;
import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.processors.abstracts.Processor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class MessagesProcessor extends Processor {

    private ArrayList<NewsMessage> list = new ArrayList<>();
    private static MessagesProcessor instance;

    private MessagesProcessor() {
        path = "data/messages.json";
        if (!MyFileHandler.fileExists(path)) {
            try {
                MyFileHandler.createFile(path);
                var obj = MyJsonParser.getDefault().toJSONString();
                MyFileHandler.writeToFile(path, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            list = transform();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static MessagesProcessor getInstance() {
        if (instance == null) {
            instance = new MessagesProcessor();
        }
        return instance;
    }

    private ArrayList<NewsMessage> transform() throws ParseException, FileNotFoundException, IOException {
        JSONArray messages = (JSONArray) read().get("data");
        return MyJsonParser.convertJsonObjectToNewsMessageList(messages);
    }

    private void save() {
        var jsonList = MyJsonParser.convertNewsMessageListToJsonArray(list);
        var obj = (JSONObject) MyJsonParser.getDefault(jsonList);
        try {
            MyFileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NewsMessage> get() {
        return list;
    }

    public ArrayList<NewsMessage> get(String topic, int count, boolean caseSensitive) {
        var ret = new ArrayList<NewsMessage>();
        if (caseSensitive) {
            topic = topic.toLowerCase();
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            var newsMessage = list.get(i);
            if (count < 1) {
                break;
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

    // Ähnliche implementierung damit die Laufzeit verbessert wird
    public ArrayList<NewsMessage> get(String topic, int count) {
        var ret = new ArrayList<NewsMessage>();
        for (int i = list.size() - 1; i >= 0; i--) {
            var newsMessage = list.get(i);
            if (count < 1) {
                break;
            }
            if (newsMessage.getTopics().contains(topic)) {
                count--;
                ret.add(newsMessage);
            }
        }
        return ret;
    }

    public ArrayList<NewsMessage> get(int count) {
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
        list.add(message);
        save();
    }
}
