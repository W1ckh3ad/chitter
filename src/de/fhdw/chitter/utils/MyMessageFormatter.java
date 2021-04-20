package de.fhdw.chitter.utils;

import de.fhdw.chitter.models.NewsMessage;

public class MyMessageFormatter {
    public static String toHtml(NewsMessage message) {
        return "<h2>" + message.getTopics() + "</h2><br><h3>" + message.getHeadline() + "</h3><br>" + "\n"
                + message.getText() + "<br><br><hr>";
    }

    public static String toString(NewsMessage message) {
        return message.getHeadline() + "[" + message.getTopics() + "]\n" + message.getText() + "\n("
                + message.getAuthor() + ", " + message.getDate() + ")\n";
    }
}
