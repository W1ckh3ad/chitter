package de.fhdw.chitter.receivers;

import de.fhdw.chitter.Newssystem;
import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.receivers.interfaces.Receiver;
import de.fhdw.chitter.services.WebSocketConnectionService;
import de.fhdw.chitter.utils.MyMessageFormatter;

import org.atmosphere.websocket.WebSocket;

import java.io.IOException;

public class WebSocketReceiver implements Receiver {
    private WebSocketConnectionService service;
    private Newssystem newssystem = Newssystem.getInstance();

    public WebSocketReceiver(WebSocketConnectionService service) {
        this.service = service;
    }

    public void register() {
        newssystem.registerAllTopics(this);
    }

    @Override
    public void receiveMessage(NewsMessage newsMessage) {
        var list = service.get();
        for (WebSocket webSocket : list) {
            try {
                webSocket.write(MyMessageFormatter.toHtml(newsMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
