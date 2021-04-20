package de.fhdw.chitter.services;

import org.apache.commons.codec.digest.DigestUtils;

import de.fhdw.chitter.models.Staff;
import de.fhdw.chitter.processors.UsersProcessor;

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

    public static boolean signUp(String username, String password) {
        var dbStaff = usersProcessor.get(username);
        if (dbStaff != null) {
            return false;
        }
        usersProcessor.post(new Staff(username, encryptPassword(password)));
        return true;
    }
}
