package de.fhdw.chitter.receivers;

import de.fhdw.chitter.Newssystem;
import de.fhdw.chitter.extern.WebSocketServer;
import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.receivers.interfaces.Receiver;
import de.fhdw.chitter.utils.MyMessageFormatter;

import org.atmosphere.websocket.WebSocket;

import java.io.IOException;

public class WebSocketReceiver implements Receiver {
    private static WebSocketReceiver instance;

    private WebSocketReceiver() {
    }

    public static WebSocketReceiver getInstance() {
        if (instance == null) {
            instance = new WebSocketReceiver();
        }
        return instance;
    }

    // TODO: Beim WebSocket Connect vorhandene Nachrichten anzeigen
    public void register() {
        Newssystem newssystem = Newssystem.getInstance();

        newssystem.registerAllTopics(this);
        try {
            var connection = WebSocketServer.getInstance().currentConnection;
            if (connection != null)
                connection.write("Test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMessage(NewsMessage msg) {

        WebSocket connection = WebSocketServer.getInstance().currentConnection;

        if (connection == null) {
            return;
        }
        try {
            connection.write(MyMessageFormatter.toHtml(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
