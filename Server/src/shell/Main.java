package shell;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		ANSI.clear();
         System.out.print(ANSI.PURPLE + "Remote Shell by Fluxmc");
         String port2 = JOptionPane.showInputDialog(null, "Wich port?");
         System.out.print("\r\n" + ANSI.YELLOW + "Starting on " + port2 + " port");
         int port = Integer.parseInt(port2);
         Server s = new Server(port);
         s.run();


	}
	

}
