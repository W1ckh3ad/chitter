package de.fhdw.chitter.extern;

// import java.io.File;
import java.io.IOException;

// import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.nettosphere.Config;
// import org.atmosphere.nettosphere.Handler;
import org.atmosphere.nettosphere.Nettosphere;
import org.atmosphere.websocket.WebSocket;
import org.atmosphere.websocket.WebSocketHandler;
import org.atmosphere.websocket.WebSocketProcessor.WebSocketException;

import de.fhdw.chitter.receivers.WebSocketReceiver;
import de.fhdw.chitter.services.WebSocketConnectionService;

// import de.fhdw.chitter.models.NewsMessage;

//https://github.com/Atmosphere/nettosphere

public class WebSocketServer implements WebSocketHandler {

	private Nettosphere server;

	private WebSocketConnectionService connections;
	private WebSocketReceiver receiver;

	public WebSocketServer(String address, int port) {
		server = new Nettosphere.Builder()
				.config(new Config.Builder().host(address).port(port).resource("/*", this).build()).build();
		connections = new WebSocketConnectionService();
		receiver = new WebSocketReceiver(connections);
		receiver.register();
	}

	public void start() {
		server.start();
	}

	@Override
	public void onByteMessage(WebSocket webSocket, byte[] data, int offset, int length) throws IOException {
		System.out.println("\n\nonByteMessage" + data);
	}

	@Override
	public void onClose(WebSocket webSocket) {
		System.out.println("\n\nonClose" + webSocket);
		connections.remove(webSocket);
	}

	@Override
	public void onError(WebSocket webSocket, WebSocketException t) {
		t.printStackTrace();
		System.out.println("\n\nonError" + webSocket);
	}

	@Override
	public void onOpen(WebSocket webSocket) throws IOException {
		System.out.println("\n\nonOpen" + webSocket);
		connections.add(webSocket);
	}

	@Override
	public void onTextMessage(WebSocket webSocket, String data) throws IOException {
		System.out.println("\n\nonTextMessage" + data);
	}

}
