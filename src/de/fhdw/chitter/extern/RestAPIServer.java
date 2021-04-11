package de.fhdw.chitter.extern;

import java.io.File;
import java.io.IOException;

import de.fhdw.chitter.models.NewsMessageTopic;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Handler;
import org.atmosphere.nettosphere.Nettosphere;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.utils.MyFileHandler;
//https://github.com/Atmosphere/nettosphere

public class RestAPIServer implements Handler {

	private Nettosphere server;

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
			String path = r.getRequest().getPathInfo();

			// extract topic from path
			NewsMessageTopic topic = NewsMessageTopic.valueOf(path.split("/")[1]);

			// build response text
			String[] files = MyFileHandler.getFileNames("data");

			StringBuffer response = new StringBuffer();
			response.append("<html><body>");
			for (String f : files) {

				// Todo: This should be handled indirectly though Newssystem
				NewsMessage msg = MyFileHandler.readFromFile("data/" + f);

				if (msg.getTopics().contains(topic)) {

					response.append(msg.toString());
					response.append("<br><br>");
				}

			}

			response.append("</body></html>");

			// write text message to response
			r.getResponse().write(response.toString()).flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
