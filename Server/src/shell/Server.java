package shell;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JLabel;



public class Server {

	private static List<PrintStream> clients;
	private static ServerSocket server;

	private int port;
	private static SecretKeySpec secretKey;
	private static byte[] key;
    private static final String aeskey = "YG{&gD#L&=.{Vsu7J?M$RWm@vpn.4/nw";

	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<PrintStream>();

	}
	  public static void setKey(final String key2) {
	    MessageDigest sha = null;
	    try {
	      key = key2.getBytes("UTF-8");
	      sha = MessageDigest.getInstance("SHA-1");
	      key = sha.digest(key);
	      key = Arrays.copyOf(key, 16);
	      secretKey = new SecretKeySpec(key, "AES");
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }
	  }

	public static String encrypt(final String text, final String pass) {
	    try {
	      setKey(pass);
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	      return Base64.getEncoder()
	        .encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
	    } catch (Exception e) {
	      System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	  }
	public void run() throws IOException {
		server = new ServerSocket(port) {
			protected void finalize() throws IOException {

				this.close();
			}
		};
		System.out.print(ANSI.GREEN + "Running on port [" + ANSI.BLUE + port + ANSI.GREEN + "]");	
		while (true) {
			// accepts a new client
			Socket client = server.accept();
			System.out.println(ANSI.GREEN + "Socket got pinged : "+ ANSI.RED + client.getInetAddress().getHostAddress());
			 PrintStream output = new PrintStream(client.getOutputStream());

             
       
			
			// add client message to list
			this.clients.add(new PrintStream(client.getOutputStream()));
			
			// create a new thread for client handling
			new Thread(new ClientHandler(this, client.getInputStream())).start();
		}
	}

	public static void sendmsg (String command) {
		for (PrintStream client : clients) {
		client.println(encrypt(command, aeskey));
	}
	}
	
	void broadcastMessages(String msg) {
		for (PrintStream client : this.clients) {
			client.println(encrypt(msg, aeskey));
		}
	}
	
}