package nl.atlasdev.v1wac.standalone.les1.p1;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] arg) throws Exception {
		Socket s = new Socket("127.0.0.1", 4711);
		OutputStream os = s.getOutputStream();
		os.write("Hello world!".getBytes());
		s.close();
	}
}
