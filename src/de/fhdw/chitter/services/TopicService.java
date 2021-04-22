package de.fhdw.chitter.services;

import java.util.ArrayList;

import de.fhdw.chitter.processors.MessageTypeProcessor;

public class TopicService {

    public static void post(ArrayList<String> param) {
        var list = new ArrayList<String>(param);
        var savedTopics = MessageTypeProcessor.getInstance().get();
        for (String string : savedTopics) {
            if (list.contains(string)) {
                list.remove(string);
            }
        }
        if (list.size() > 0) {
            MessageTypeProcessor.getInstance().post(list);
        }
    }
}
