package de.fhdw.chitter.models;

import java.util.ArrayList;

import de.fhdw.chitter.utils.TopicExtractor;

public class NewsMessage {
    private String date;
    private String author;
    private ArrayList<String> topics;
    private String headline;
    private String text;

    public NewsMessage(String date, String author, ArrayList<String> topics, String headline) {
        this.date = date;
        this.author = author;
        this.topics = topics;
        this.headline = headline;
    }

    private NewsMessage(String date, String author, String headline) {
        this.date = date;
        this.author = author;
        this.headline = headline;
    }

    public NewsMessage(String date, String author, ArrayList<String> topics, String headline, String text) {
        this(date, author, topics, headline);
        this.text = text;
    }

    public NewsMessage(String date, String author, String[] topics, String headline) {
        this(date, author, headline);
        var topicsArrayList = new ArrayList<String>();
        for (String string : topics) {
            topicsArrayList.add(string);
        }
        this.topics = topicsArrayList;
    }

    public NewsMessage(String date, String author, String[] topics, String headline, String text) {
        this(date, author, topics, headline);
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public ArrayList<String> getTopics() {
        return new ArrayList<String>(topics);
    }

    public ArrayList<String> getAllTopics() {
        return TopicExtractor.getTopics(this);
    }

    public String getHeadline() {
        return headline;
    }

    public String getText() {
        return text;
    }
}
