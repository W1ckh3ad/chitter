package de.fhdw.chitter.processors.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.fhdw.chitter.jsonparser.abstracts.Parser;
import de.fhdw.chitter.utils.MyFileHandler;

public abstract class Processor<T> {
    protected String path;
    protected static MyFileHandler fileHandler = new MyFileHandler();
    protected Parser<T> parser;

    public Processor(String path) {
        this.path = path;
        if (!fileHandler.fileExists(path)) {
            try {
                create();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

    }

    protected JSONObject read() throws ParseException, FileNotFoundException, IOException {
        var parser = new JSONParser();
        return (JSONObject) parser.parse(fileHandler.readFromFile(path));
    }

    protected void create() throws ParseException, FileNotFoundException, IOException {
        fileHandler.createFile(path);
        var obj = parser.getDefault().toJSONString();
        fileHandler.writeToFile(path, obj);
    }

    protected void save(ArrayList<T> list) {
        var array = parser.convertListToJsonArray(list);
        var obj = (JSONObject) parser.getDefault(array);
        try {
            fileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ArrayList<T> transform() {
        try {
            JSONObject obj = read();
            JSONArray list = (JSONArray) obj.get("data");
            return parser.convertJsonArrayToList(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<T>();
        }
    }

    public ArrayList<T> get() {
        return transform();
    }

    public void post(T elem) {
        var list = transform();
        list.add(elem);
        save(list);
    }

}