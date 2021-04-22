package de.fhdw.chitter.services;

import java.util.ArrayList;

import org.atmosphere.websocket.WebSocket;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.processors.MessagesProcessor;
import de.fhdw.chitter.utils.MyMessageFormatter;

public class WebSocketConnectionService {

    private ArrayList<WebSocket> connections;

    public WebSocketConnectionService() {
        connections = new ArrayList<>();
    }

    public void add(WebSocket webSocket) {
        if (!connections.contains(webSocket)) {
            for (NewsMessage message : MessagesProcessor.getInstance().get()) {
                try {
                    webSocket.write(MyMessageFormatter.toHtml(message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            connections.add(webSocket);
        }
    }

    public void remove(WebSocket webSocket) {
        if (connections.contains(webSocket)) {
            connections.remove(webSocket);
        }
    }

    public ArrayList<WebSocket> get() {
        return new ArrayList<>(connections);
    }
}
