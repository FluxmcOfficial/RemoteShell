package shell;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	public static void Main(String args[]) throws UnknownHostException, IOException {
		String host = "127.0.0.1";
		int port = 80;
	    System.out.print(Handler.author);
		Client c = new Client(host,port);
		c.run();
		
		// racisz?
		
		// By Fluxmc
	}
}
