package de.fhdw.chitter.models.userconfig;

public class CharacterVal {
    private String name;
    private Integer min;
    private Integer max;
    private String characters;

    public String getName() {
        return name;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public String getCharacters() {
        return characters;
    }

    public CharacterVal(String name, Integer min, Integer max, String chars) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.characters = chars;
    }
}
