package de.fhdw.chitter.utils;

// import de.fhdw.chitter.models.NewsMessage;

public class MarkDownParser {

    // private String uselessShit = "232";

    public static String parse(String text) {
        String returnText = text;
        returnText = text.replaceAll("(?m)^#(?!#)(.*)", "<h1>$1</h1>");
        returnText = text.replaceAll("(?m)^#{2}(?!#)(.*)", "<h2>$1</h2>");
        // h3 sind in md ### nicht ##
        returnText = text.replaceAll("(?m)^#{3}(?!#)(.*)", "<h3>$1</h3>");

        returnText = text.replaceAll("\\*(.*)\\*", "<em>$1</em>");
        returnText = text.replaceAll("\\*\\*(.*)\\*\\*", "<b>$1</b>");

        returnText = text.replaceAll("(?m)^\\* (.*)$", "<li> $1 </li>");

        return returnText;
    }

    // public NewsMessage parseNewsMessage(NewsMessage msg) {
    // msg.text = msg.text.replaceAll("(?m)^#(?!#)(.*)", "<h1>$1</h1>");
    // msg.text = msg.text.replaceAll("(?m)^#{2}(?!#)(.*)", "<h2>$1</h2>");
    // msg.text = msg.text.replaceAll("(?m)^#{2}(?!#)(.*)", "<h3>$1</h3>");

    // msg.text = msg.text.replaceAll("\\*(.*)\\*", "<em>$1</em>");
    // msg.text = msg.text.replaceAll("\\*\\*(.*)\\*\\*", "<b>$1</b>");

    // msg.text = msg.text.replaceAll("(?m)^\\* (.*)$", "<li> $1 </li>");

    // return msg;
    // }
}

// # ErsteÜberschrift
// <h1>ErsteÜberschrift</h1>
