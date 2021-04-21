package de.fhdw.chitter.utils;

public class MyMarkDownParser {
    public static String parse(String text) {
        text = text.replaceAll("^#(?!#)(.*)", "<h1>$1</h1>");
        text = text.replaceAll("^#{2}(?!#)(.*)", "<h2>$1</h2>");
        // h3 sind in md ### nicht ##
        text = text.replaceAll("^#{3}(?!#)(.*)", "<h3>$1</h3>");
        text = text.replaceAll("\\n", "<br />");
        text = text.replaceAll("\\*(.*)\\*", "<em>$1</em>");
        text = text.replaceAll("\\*\\*(.*)\\*\\*", "<b>$1</b>");

        text = text.replaceAll("^\\* (.*)$", "<li> $1 </li>");

        return text;
    }
}
