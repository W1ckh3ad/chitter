package de.fhdw.chitter.extern;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Handler;
import org.atmosphere.nettosphere.Nettosphere;

import de.fhdw.chitter.services.RoutingService;

public class WebServer implements Handler {

	private Nettosphere server;

	public WebServer(String address, int port) {
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
