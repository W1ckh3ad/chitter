package de.fhdw.chitter.processors;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.models.Staff;
import de.fhdw.chitter.processors.abstracts.Processor;
import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.jsonparser.StaffParser;

public class UsersProcessor extends Processor {
    private static UsersProcessor instance;

    private UsersProcessor() {
        super("data/users.json");
    }

    public static UsersProcessor getInstance() {
        if (instance == null) {
            instance = new UsersProcessor();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void save(ArrayList<Staff> list) {
        var jsonList = new JSONArray();
        for (Staff newsMessage : list) {
            try {
                jsonList.add(StaffParser.convertToJsonObject(newsMessage));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        var obj = (JSONObject) StaffParser.getDefault(jsonList);
        try {
            MyFileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Staff> transform() {
        try {
            JSONObject obj = read();
            JSONArray staffList = (JSONArray) obj.get("data");
            return StaffParser.convertJsonObjectToList(staffList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Staff>();
        }
    }

    public ArrayList<Staff> get() {
        return transform();
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

    public void post(Staff staff) {
        var list = transform();
        list.add(staff);
        save(list);
    }
}
