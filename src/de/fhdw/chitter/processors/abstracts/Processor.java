package de.fhdw.chitter.processors.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.fhdw.chitter.utils.MyFileHandler;

public abstract class Processor {
    protected String path;

    protected JSONObject read() throws ParseException, FileNotFoundException, IOException {
        var parser = new JSONParser();
        return (JSONObject) parser.parse(MyFileHandler.readFromFile(path));
    }   

}
