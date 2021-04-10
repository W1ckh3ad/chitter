package de.fhdw.chitter;

import de.fhdw.chitter.models.Staff;

import java.util.ArrayList;
import java.util.List;

public class Staffsystem {

    private static Staffsystem instance;

    private Staffsystem() { }

    public static Staffsystem getInstance() {
        if (instance == null) {
            instance = new Staffsystem();
        }
        return instance;
    }

    public boolean checkLogin(String username, String password) {
        for (Staff s : this.getStaffList()) {
            if (s != null && s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    protected List<Staff> getStaffList() {
        List<Staff> staffList = new ArrayList<>();
        // Todo: Instead of adding a bunch of users, we should read these from a configuration file
        staffList.add(new Staff("Max", "passwort"));
        staffList.add(new Staff("Hans", "12345"));
        staffList.add(new Staff("John", "wer?"));
        return staffList;
    }
}
