package shell;

import java.io.InputStream;
import java.util.Scanner;

public class ClientHandler implements Runnable {

		private Server server;
		private InputStream client;

		public ClientHandler(Server server, InputStream client) {
			this.server = server;
			this.client = client;
		}

		public void run() {
			String message;
			
			// when there is a new message, broadcast to all
			Scanner sc = new Scanner(this.client);
			while (sc.hasNextLine()) {
				message = sc.nextLine();
				server.broadcastMessages(message);
			}
			sc.close();
		}
	}



