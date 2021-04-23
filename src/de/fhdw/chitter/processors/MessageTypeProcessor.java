package de.fhdw.chitter.processors;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.processors.abstracts.Processor;
import de.fhdw.chitter.utils.jsonparser.StringListParser;

public final class MessageTypeProcessor extends Processor<String> {
    private static MessageTypeProcessor instance;

    private MessageTypeProcessor() {
        super("data/messageTypes.json");
    }

    @Override
    protected void save(ArrayList<String> list) {
        var array = StringListParser.convertToJsonArray(list);
        var obj = (JSONObject) StringListParser.getDefault(array);
        try {
            fileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ArrayList<String> transform() {
        try {
            JSONObject obj = read();
            JSONArray types = (JSONArray) obj.get("data");
            return StringListParser.convertFromJsonObject(types);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    public static MessageTypeProcessor getInstance() {
        if (instance == null) {
            instance = new MessageTypeProcessor();
        }
        return instance;
    }

    @Override
    public ArrayList<String> get() {
        return transform();
    }

    @Override
    public void post(String type) {
        var list = transform();
        list.add(type);
        save(list);
    }

    public void post(ArrayList<String> types) {
        var list = transform();
        for (String string : types) {
            list.add(string);
        }
        save(list);
    }
}
