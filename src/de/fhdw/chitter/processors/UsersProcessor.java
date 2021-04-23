package de.fhdw.chitter.processors;


import de.fhdw.chitter.jsonparser.StaffParser;
import de.fhdw.chitter.models.Staff;
import de.fhdw.chitter.processors.abstracts.Processor;

public final class UsersProcessor extends Processor<Staff> {
    private static UsersProcessor instance;

    private UsersProcessor() {
        super("data/users.json");
        parser = new StaffParser();
    }

    public static UsersProcessor getInstance() {
        if (instance == null) {
            instance = new UsersProcessor();
        }
        return instance;
    }


    public Staff get(String username) {
        var list = transform();
        for (Staff staff : list) {
            if (staff.getUsername().equals(username)) {
                return staff;
            }
        }
        return null;
    }
}
