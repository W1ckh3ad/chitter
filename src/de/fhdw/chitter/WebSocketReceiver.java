package de.fhdw.chitter;

import de.fhdw.chitter.extern.WebSocketServer;
import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.utils.MyFileHandler;

import org.atmosphere.websocket.WebSocket;

import java.io.IOException;

public class WebSocketReceiver implements Receiver {
    private static WebSocketReceiver instance;

    private WebSocketReceiver() {
    }

    // TODO: Auslagern in Parser, Alternativ einen Formatter erstellen
    private String markup(NewsMessage msg) {
        return "<h2>" + msg.getTopics() + "</h2><br><h3>" + msg.getHeadline() + "</h3><br>" + "\n" + msg.getText()
                + "<br><br><hr>";
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
        // WebSocket connection = WebSocketServer.getInstance().currentConnection;

        newssystem.registerAllTopics(this);

        // String[] pathes = MyFileHandler.getFileNames("data");
        // for (String path : pathes) {
        // try {
        // connection.write(markup(MyFileHandler.readFromFile(path)));
        // } catch (IOException e){
        // e.printStackTrace();
        // }
        // }
    }

    @Override
    public void receiveMessage(NewsMessage msg) {
        String msgtext = "<h2>" + msg.getTopics() + "</h2><br><h3>" + msg.getHeadline() + "</h3><br>" + "\n"
                + msg.getText() + "<br><br><hr>";

        WebSocket connection = WebSocketServer.getInstance().currentConnection;

        if (connection == null) {
            return;
        }

        try {
            connection.write(msgtext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
