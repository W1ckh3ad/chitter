package de.fhdw.chitter.utils;

public class MyMarkDownParser {
    public static String parse(String text) {
        text = text.replaceAll("^#(\s)(.*)", "<h1>$1</h1>");
        text = text.replaceAll("^##(\s)(.*)", "<h2>$1</h2>");
        text = text.replaceAll("^###(\s)(.*)", "<h3>$1</h3>");
        text = text.replaceAll("\\n", "<br />");
        text = text.replaceAll("\\*(.*)\\*", "<em>$1</em>");
        text = text.replaceAll("\\*\\*(.*)\\*\\*", "<b>$1</b>");
        text = text.replaceAll("^\\* (.*)$", "<li> $1 </li>");
        return text;
    }
}
