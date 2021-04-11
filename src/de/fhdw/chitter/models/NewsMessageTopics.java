package de.fhdw.chitter.models;

import java.util.ArrayList;
import java.util.List;

public class NewsMessageTopics extends ArrayList<NewsMessageTopic> {

    public static NewsMessageTopics fromString(String topicsString) {
        NewsMessageTopics topics = new NewsMessageTopics();
        for (String topicString : topicsString.split(",")) {
            try {
                topics.add(NewsMessageTopic.getElemWithStringCaseSensitive(topicString));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Kein topic mit dem Namen " + topicString + " verfügbar");
            }
        }
        return topics;
    }

    @Override
    public String toString() {
        List<String> topicsStrings = new ArrayList<>();
        for (NewsMessageTopic t : this) {
            topicsStrings.add(t.toString());
        }
        return String.join(",", topicsStrings);
    }
}
