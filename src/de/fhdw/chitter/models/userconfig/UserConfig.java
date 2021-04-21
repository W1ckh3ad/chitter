package de.fhdw.chitter.models.userconfig;

import java.util.ArrayList;

public class UserConfig {
    private Integer maxUsers;
    private ArrayList<CharacterVal> rules;

    public int getMaxUsers() {
        return maxUsers;
    }

    public ArrayList<CharacterVal> getRules() {
        return rules;
    }

    public UserConfig(Integer maxUsers, ArrayList<CharacterVal> rules) {
        this.maxUsers = maxUsers;
        this.rules = rules;
    }
}
