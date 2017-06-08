package nl.atlasdev;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
	    Socket sock = new Socket("127.0.0.1", 4711);
	    PrintWriter writer = new PrintWriter(sock.getOutputStream());
	    writer.write("hallo");
	    writer.close();
	    sock.close();
	}
}
