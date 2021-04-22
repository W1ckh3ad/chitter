package de.fhdw.chitter.controller.api;

// import org.atmosphere.cpr.AtmosphereResource;

import de.fhdw.chitter.processors.MessageTypeProcessor;
import de.fhdw.chitter.processors.MessagesProcessor;
import de.fhdw.chitter.utils.jsonparser.NewsMessageParser;
import de.fhdw.chitter.utils.jsonparser.StringListParser;

public class ChitterController {
    // private AtmosphereResource resource;
    private MessagesProcessor messagesProcessor = MessagesProcessor.getInstance();
    private MessageTypeProcessor messageTypeProcessor = MessageTypeProcessor.getInstance();

    // public ChitterController(AtmosphereResource r) {
    // resource = r;
    // }

    public String index() {
        var messages = messagesProcessor.get(5);
        var obj = NewsMessageParser.getDefault(NewsMessageParser.convertListToJsonArray(messages));
        return obj.toJSONString();
    }

    public String index(String topic) {
        var messages = messagesProcessor.get(topic, 5, true);
        var obj = NewsMessageParser.getDefault(NewsMessageParser.convertListToJsonArray(messages));
        return obj.toJSONString();
    }

    public String topics() {
        var topics = messageTypeProcessor.get();
        var obj = StringListParser.getDefault(StringListParser.convertToJsonArray(topics));
        return obj.toJSONString();
    }
}
