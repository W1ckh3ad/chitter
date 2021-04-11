package de.fhdw.chitter.models;

public enum NewsMessageTopic {
    SPORT("Sport"), ECONOMY("Wirtschaft"), POLITICS("Politik"), SCIENCE("Wissenschaft"), TECHNOLOGY("Technik"),
    COMPUTERS("Computer");

    private String value;

    public String getValue() {
        return value;
    }

    private NewsMessageTopic(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static NewsMessageTopic getElemWithStringCaseSensitive(String value) throws IllegalArgumentException {
        value = value.trim();
        for (NewsMessageTopic item : NewsMessageTopic.values()) {
            if (value.equals(item.getValue())) {
                return item;
            }
        }
        throw new IllegalArgumentException();
    }

    public static NewsMessageTopic getElemWithStringCaseInsensitive(String value) throws IllegalArgumentException {
        value = value.trim().toLowerCase();
        for (NewsMessageTopic item : NewsMessageTopic.values()) {
            if (value.equals(item.getValue().toLowerCase())) {
                return item;
            }
        }
        throw new IllegalArgumentException();
    }
}
