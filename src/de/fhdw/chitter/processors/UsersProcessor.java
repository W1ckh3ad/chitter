package de.fhdw.chitter.processors;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.fhdw.chitter.models.Staff;
import de.fhdw.chitter.processors.abstracts.Processor;
import de.fhdw.chitter.utils.MyFileHandler;
import de.fhdw.chitter.utils.MyJsonParser;

public class UsersProcessor extends Processor {
    private ArrayList<Staff> list = new ArrayList<>();

    private static UsersProcessor instance;

    private UsersProcessor() {
        path = "data/users.json";
        if (!MyFileHandler.fileExists(path)) {
            try {
                create();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        JSONObject obj;
        JSONArray staff;
        try {
            obj = read();
            staff = (JSONArray) obj.get("data");
            for (Object object : staff) {
                var jsonObj = (JSONObject) object;
                list.add(MyJsonParser.convertJsonObjectToStaff(jsonObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static UsersProcessor getInstance() {
        if (instance == null) {
            instance = new UsersProcessor();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void save() {
        var jsonList = new JSONArray();
        for (Staff newsMessage : list) {
            try {
                jsonList.add(MyJsonParser.convertStaffToJsonObject(newsMessage));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        var obj = (JSONObject) MyJsonParser.getDefault(jsonList);
        try {
            MyFileHandler.writeToFile(path, obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Staff> get() {
        return list;
    }

    public Staff get(String username) {
        for (Staff staff : list) {
            if (staff.getUsername().equals(username)) {
                return staff;
            }
        }
        return null;
    }

    public void post(Staff staff) {
        list.add(staff);
        save();
    }
}
