package de.fhdw.chitter;

import de.fhdw.chitter.extern.WebSocketServer;
import de.fhdw.chitter.models.NewsMessage;
import org.atmosphere.websocket.WebSocket;

import java.io.IOException;

public class WebSocketReceiver implements Receiver {
    private static WebSocketReceiver instance;

    private WebSocketReceiver() { }

    public static WebSocketReceiver getInstance() {
        if (instance == null) {
            instance = new WebSocketReceiver();
        }
        return instance;
    }

    public void register() {
        Newssystem newssystem = Newssystem.getInstance();
        newssystem.registerAllTopics(this);
    }

    @Override
    public void receiveMessage(NewsMessage msg) {
        String msgtext = "<h2>" + msg.getTopic() + "</h2><br><h3>" + msg.getHeadline() + "</h3><br>" + "\n"
                + msg.getText() + "<br><br><hr>";


        WebSocket connection = WebSocketServer.getInstance().currentConnection;

        if(connection == null)
        {
            return;
        }

        try {
            connection.write(msgtext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
