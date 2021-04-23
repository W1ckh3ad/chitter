package de.fhdw.chitter.controller;

import de.fhdw.chitter.utils.MyFileHandler;

public class HomeController {

    private MyFileHandler fileHandler = new MyFileHandler();

    public String index() {
        var ret = "";
        try {
            ret = fileHandler.readFromFile("src/de/fhdw/chitter/views/newsticker.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public String error() {
        var ret = "";
        try {
            ret = fileHandler.readFromFile("src/de/fhdw/chitter/views/404.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
