package de.fhdw.chitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.fhdw.chitter.extern.WebServer;
import de.fhdw.chitter.extern.WebSocketServer;
import de.fhdw.chitter.gui.AdminGUI;

public class Main {
	public static void main(String[] args) {
		Newssystem.start();

		WebServer webServer = new WebServer("127.0.0.1", 8080);
		webServer.start();

		WebSocketServer webSocketServer = new WebSocketServer("127.0.0.1", 8081);
		webSocketServer.start();

		new AdminGUI();

		BufferedReader readUConsole = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Q zum beenden");

			try {
				String userinput = readUConsole.readLine();

				if (userinput.startsWith("q")) {
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println("Unknown execption " + e);
			}
		}
	}
}