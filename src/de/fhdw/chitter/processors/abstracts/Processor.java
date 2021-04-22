package de.fhdw.chitter.processors.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.jsonparser.abstracts.Parser;

public abstract class Processor {
    protected String path;

    public Processor(String path) {
        this.path = path;
        if (!MyFileHandler.fileExists(path)) {
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
        return (JSONObject) parser.parse(MyFileHandler.readFromFile(path));
    }

    protected void create() throws ParseException, FileNotFoundException, IOException {
        MyFileHandler.createFile(path);
        var obj = Parser.getDefault().toJSONString();
        MyFileHandler.writeToFile(path, obj);
    }

}
