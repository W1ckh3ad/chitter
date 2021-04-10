package de.fhdw.chitter.models;

import java.text.SimpleDateFormat;

public class NewsMessage {
    private String date;
    private String author;
    private String topic;
    private String headline;
    private String text;

    public NewsMessage(String date, String author, String topic, String headline) {
        this.date = date;
        this.author = author;
        this.topic = topic;
        this.headline = headline;
    }

    public NewsMessage(String date, String author, String topic, String headline, String text) {
        this(date, author, topic, headline);
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

    public String getTopic() {
        return topic;
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
        return headline + "[" + topic + "]\n" + text + "\n(" + author + "," + date + ")\n";

    }

}
