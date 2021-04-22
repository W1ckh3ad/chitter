package de.fhdw.chitter.services;

import org.apache.commons.codec.digest.DigestUtils;

import de.fhdw.chitter.models.Staff;
import de.fhdw.chitter.models.userconfig.CharacterVal;
import de.fhdw.chitter.processors.UsersProcessor;
import de.fhdw.chitter.utils.UserConfigParser;

public class UserService {
    private static UsersProcessor usersProcessor = UsersProcessor.getInstance();

    private static String encryptPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public static boolean logIn(String username, String password) {
        var dbStaff = usersProcessor.get(username);
        if (dbStaff == null) {
            return false;
        }
        if (dbStaff.getPassword().equals(encryptPassword(password))) {
            return true;
        }
        return false;
    }

    public static boolean signUp(String username, String password) throws Exception {
        var dbStaff = usersProcessor.get(username);
        if (dbStaff != null) {
            throw new Exception("User already Exists");
        }
        if (usersProcessor.get().size() >= UserConfigParser.getMaxUserCount()) {
            throw new Exception("Max User Count reached");
        }
        validatePassword(password);

        usersProcessor.post(new Staff(username, encryptPassword(password)));
        return true;
    }

    private static void validatePassword(String password) throws Exception {
        var rules = UserConfigParser.getPasswordRules();
        for (CharacterVal characterVal : rules) {
            var min = characterVal.getMin();
            var max = characterVal.getMax();
            var characters = characterVal.getCharacters();
            var name = characterVal.getName();
            if (characters != null) {
                if (validateCharsWithMinMax(password, characters, min, max)) {
                    continue;
                } else {
                    throw new Exception(
                            "Error Rule (" + name + "), needs (" + characters + ") min(" + min + ") max(" + max + ")");
                }
            } else if (min != null && max != null) {
                var length = password.length();
                if (length >= min && length <= max) {
                    continue;
                } else {
                    throw new Exception("Error Rule (" + name + "), min(" + min + ") max(" + max + ")");
                }
            }

        }

    }

    private static int getCharCount(String string, String chars) {
        var ret = 0;
        for (int i = 0; i < string.length(); i++) {
            var c = string.charAt(i);
            if (chars.indexOf(c) != -1) {
                ret++;
            }
        }
        return ret;
    }

    private static boolean validateCharsWithMinMax(String string, String chars, Integer min, Integer max) {
        var count = getCharCount(string, chars);
        if (min != null && max != null) {
            return !(count < min && count > max);
        } else if (min == null && max != null) {
            return !(count > max);
        } else if (min != null && max == null) {
            return !(count < min);
        } else {
            return count > 0;
        }
    }
}
