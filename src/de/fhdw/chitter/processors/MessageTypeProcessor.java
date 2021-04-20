package de.fhdw.chitter.processors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import de.fhdw.chitter.processors.abstracts.Processor;
import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.MyJsonParser;

public class MessageTypeProcessor extends Processor {
    // public static NewsMessageTopics fromString(String topicsString) {
    // NewsMessageTopics topics = new NewsMessageTopics();
    // for (String topicString : topicsString.split(",")) {
    // try {
    // topics.add(NewsMessageTopic.getElemWithStringCaseSensitive(topicString));
    // } catch (IllegalArgumentException e) {
    // throw new IllegalArgumentException("Kein topic mit dem Namen " + topicString
    // + " verfügbar");
    // }
    // }
    // return topics;
    // }

    // @Override
    // public String toString() {
    // List<String> topicsStrings = new ArrayList<>();
    // for (NewsMessageTopic t : this) {
    // topicsStrings.add(t.toString());
    // }
    // return String.join(",", topicsStrings);
    // }

    private ArrayList<String> list = new ArrayList<>();
    private static MessageTypeProcessor instance;

    private MessageTypeProcessor() {
        path = "data/messageTypes.json";
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

    private void save() {
        var array = MyJsonParser.convertStringListToJsonArray(list);
        var obj = (JSONObject) MyJsonParser.getDefault(array);
        try {
            MyFileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> transform() throws ParseException, FileNotFoundException, IOException {
        JSONObject obj = read();
        JSONArray types = (JSONArray) obj.get("data");
        return MyJsonParser.convertJsonObjectToStringList(types);
    }

    public static MessageTypeProcessor getInstance() {
        if (instance == null) {
            instance = new MessageTypeProcessor();
        }
        return instance;
    }

    public ArrayList<String> get() {
        return list;
    }

    public void post(String... type) {
        for (String string : type) {
            if (!list.contains(string)) {
                list.add(string);
            }
        }
        save();
    }
}
