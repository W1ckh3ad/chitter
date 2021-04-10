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

// import de.fhdw.chitter.models.NewsMessage;

//https://github.com/Atmosphere/nettosphere

public class WebSocketServer implements WebSocketHandler {

	private Nettosphere server;

	public WebSocket currentConnection;

	public static WebSocketServer instance;

	public static WebSocketServer getInstance() {
		if (instance == null) {
			instance = new WebSocketServer("127.0.0.1", 8081);
		}

		return instance;
	}

	private WebSocketServer(String address, int port) {
		server = new Nettosphere.Builder()
				.config(new Config.Builder().host(address).port(port).resource("/*", this).build()).build();

	}

	public void start() {
		server.start();
	}

	@Override
	public void onByteMessage(WebSocket webSocket, byte[] data, int offset, int length) throws IOException {
		System.out.println("onByteMessage" + data);
	}

	@Override
	public void onClose(WebSocket webSocket) {
		System.out.println("onClose" + webSocket);

		currentConnection = null;
	}

	@Override
	public void onError(WebSocket webSocket, WebSocketException t) {
		System.out.println("onError" + webSocket);
	}

	@Override
	public void onOpen(WebSocket webSocket) throws IOException {
		System.out.println("onOpen" + webSocket);

		currentConnection = webSocket;
	}

	@Override
	public void onTextMessage(WebSocket webSocket, String data) throws IOException {
		System.out.println("onTextMessage" + data);
	}

}
