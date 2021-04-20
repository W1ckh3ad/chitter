package de.fhdw.chitter.extern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Handler;
import org.atmosphere.nettosphere.Nettosphere;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.processors.MessagesProcessor;
import de.fhdw.chitter.services.RoutingService;
import de.fhdw.chitter.utils.MyFileHandler;
//https://github.com/Atmosphere/nettosphere
import de.fhdw.chitter.utils.MyMessageFormatter;

public class RestAPIServer implements Handler {

	private Nettosphere server;
	private MessagesProcessor messagesProcessor = MessagesProcessor.getInstance();

	public RestAPIServer(String address, int port) {
		server = new Nettosphere.Builder().config(new Config.Builder().host(address).port(port).resource(this).build())
				.build();

	}

	public void start() {
		server.start();
	}

	// Response Inhalt auslagern
	// Aufgabe ist Request und Response zu behandeln
	@Override
	public void handle(AtmosphereResource r) {
		//
		try {
			var handler = new RoutingService(r);

			StringBuffer response = new StringBuffer();
			response.append(handler.route());

			// write text message to response
			r.getResponse().write(response.toString()).flushBuffer();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}
