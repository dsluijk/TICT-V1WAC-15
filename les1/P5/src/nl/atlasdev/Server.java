package nl.atlasdev;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(4711);
		while(true) new Thread(new MyServlet(server.accept())).start();
	}
}
