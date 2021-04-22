package de.fhdw.chitter.utils;

import java.util.ArrayList;

import de.fhdw.chitter.models.NewsMessage;

public class TopicExtractor {

    public static ArrayList<String> getTopics(NewsMessage message) {
        var ret = message.getTopics();
        String text = message.getText();
        if (text.contains("#")) {
            int start = 0, end = 0;
            for (;;) {
                if (start > text.length()) {
                    break;
                }
                start = text.indexOf("#", start) + 1;
                end = text.indexOf(" ", start - 1);
                if (start == end) {
                    continue;
                }

                if (end == -1) {
                    end = text.length();
                }
                var substring = text.substring(start, end);
                if (!ret.contains(substring) && !substring.contains(("#"))) {
                    ret.add(substring);
                }
                start = end + 1;
            }
        }
        return ret;
    }

}
