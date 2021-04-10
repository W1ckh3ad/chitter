package de.fhdw.chitter.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewsMessage {
    private String date;
    private String author;
    private List<NewsMessageTopic> topics;
    private String headline;
    private String text;

    public static List<NewsMessageTopic> readTopicStrings(String topicStrings) {
        List<NewsMessageTopic> topics = new ArrayList<>();
        for (String topicString : topicStrings.split(",")) {
            try {
                topics.add(NewsMessageTopic.valueOf(topicString.trim()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Kein topic mit dem Namen " + topicString + " verfügbar");
            }
        }
        return topics;
    }

    public NewsMessage(String date, String author, List<NewsMessageTopic> topics, String headline) {
        this.date = date;
        this.author = author;
        this.topics = topics;
        this.headline = headline;
    }

    public NewsMessage(String date, String author, List<NewsMessageTopic> topics, String headline, String text) {
        this(date, author, topics, headline);
        this.text = text;
    }

    // public NewsMessage(String author, String topic, String headline, String text)
    // {
    // this();
    // this.author = author;
    // this.topic = topic;
    // this.headline = headline;
    // this.text = text;
    // }

    public NewsMessage() {
        SimpleDateFormat sdf_message = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        date = sdf_message.format(System.currentTimeMillis());
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public List<NewsMessageTopic> getTopics() {
        return topics;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return headline + "[" + topics + "]\n" + text + "\n(" + author + "," + date + ")\n";

    }

}
