package de.fhdw.chitter.controller.api;

import org.atmosphere.cpr.AtmosphereResource;

import de.fhdw.chitter.jsonparser.NewsMessageParser;
import de.fhdw.chitter.jsonparser.StringListParser;

// import org.atmosphere.cpr.AtmosphereResource;

import de.fhdw.chitter.processors.MessageTypeProcessor;
import de.fhdw.chitter.processors.MessagesProcessor;

public class ChitterController {
    private AtmosphereResource resource;
    private MessagesProcessor messagesProcessor = MessagesProcessor.getInstance();
    private MessageTypeProcessor messageTypeProcessor = MessageTypeProcessor.getInstance();
    private StringListParser stringListParser = new StringListParser();
    private NewsMessageParser newsMessageParser = new NewsMessageParser();
    public ChitterController(AtmosphereResource r) {
    resource = r;
    }

    public String index() {
        var messages = messagesProcessor.get(5);
        var obj = newsMessageParser.getDefault(messages);
        return obj.toJSONString();
    }

    public String index(String topic) {
        var messages = messagesProcessor.get(topic, 5, true);
        var obj = newsMessageParser.getDefault(messages);
        return obj.toJSONString();
    }

    public String topics() {
        var topics = messageTypeProcessor.get();
        var obj = stringListParser.getDefault(topics);
        return obj.toJSONString();
    }

    public String error() {
        var response = resource.getResponse();
        response.setStatus(404);
        return "{'data': 'Die Ressource die Sie abgefragt haben ist nicht zu finden'}";
    }
}
