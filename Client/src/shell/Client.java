package shell;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static String host;
	public static int port;
	private String nickname;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws UnknownHostException, IOException {
		Socket client = new Socket(host, port);
		System.out.println("Zombie connected");

		new Thread(new Handler(client.getInputStream())).start();

		Scanner sc = new Scanner(System.in);
		nickname = "Zombie";

		PrintStream output = new PrintStream(client.getOutputStream());
		while (sc.hasNextLine()) {
			output.println(nickname + ": " + sc.nextLine());
		}
		
		output.close();
		sc.close();
		client.close();
	}
}


