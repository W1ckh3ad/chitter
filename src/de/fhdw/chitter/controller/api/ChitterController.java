package de.fhdw.chitter.controller.api;

import org.atmosphere.cpr.AtmosphereResource;
import org.json.simple.JSONArray;

import de.fhdw.chitter.processors.MessageTypeProcessor;
import de.fhdw.chitter.processors.MessagesProcessor;
import de.fhdw.chitter.utils.MyJsonParser;

public class ChitterController {
    private AtmosphereResource resource;
    private MessagesProcessor messagesProcessor = MessagesProcessor.getInstance();
    private MessageTypeProcessor messageTypeProcessor = MessageTypeProcessor.getInstance();

    public ChitterController(AtmosphereResource r) {
        resource = r;
    }

    public String index() {
        var obj = MyJsonParser.getDefault();
        var messages = messagesProcessor.get(5);
        JSONArray array = MyJsonParser.convertNewsMessageListToJsonArray(messages);
        obj.replace("data", array);
        return obj.toJSONString();
    }

    public String index(String topic) {
        var obj = MyJsonParser.getDefault();
        var messages = messagesProcessor.get(topic, 5, true);
        obj.replace("data", MyJsonParser.convertNewsMessageListToJsonArray(messages));
        return obj.toJSONString();
    }

    public String topics() {
        var obj = MyJsonParser.getDefault();
        var topics = messageTypeProcessor.get();
        obj.replace("data", MyJsonParser.convertStringListToJsonArray(topics));
        return obj.toJSONString();
    }
}
