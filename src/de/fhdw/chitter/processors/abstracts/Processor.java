package de.fhdw.chitter.processors.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.jsonparser.abstracts.Parser;

public abstract class Processor<T> {
    protected String path;
    protected static MyFileHandler fileHandler = new MyFileHandler();

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
        var obj = Parser.getDefault().toJSONString();
        fileHandler.writeToFile(path, obj);
    }

    protected abstract ArrayList<T> transform();

    protected abstract void save(ArrayList<T> list);

    public abstract ArrayList<T> get();

    public abstract void post(T elem);

}