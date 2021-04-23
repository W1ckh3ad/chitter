package de.fhdw.chitter.processors;

import java.util.ArrayList;

import de.fhdw.chitter.jsonparser.StringListParser;
import de.fhdw.chitter.processors.abstracts.Processor;

public final class MessageTypeProcessor extends Processor<String> {
    private static MessageTypeProcessor instance;

    private MessageTypeProcessor() {
        super("data/messageTypes.json");
        parser = new StringListParser();
    }

    public static MessageTypeProcessor getInstance() {
        if (instance == null) {
            instance = new MessageTypeProcessor();
        }
        return instance;
    }

    public void post(ArrayList<String> types) {
        var list = transform();
        for (String string : types) {
            list.add(string);
        }
        save(list);
    }
}
