package nl.atlasdev;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyServlet extends Thread {
	private Socket socket;

	MyServlet(Socket sock) {
		this.socket = sock;
	}

	@Override
	public void run() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			String line = scanner.nextLine();
			System.out.println(line);
			if(line.equals("")) {
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(this.socket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				writer.write("HTTP/1.1 200 OK\n\nSUCCESS");
				scanner.close();
				try {
					this.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
