package nl.atlasdev.v1wac.standalone.les1.p5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestThread extends Thread {
	private static final String host = "127.0.0.1";
	private static final int port = 4711;

	@Override
	public void run() {
		Socket sock = null;
		PrintWriter writer = null;
		Scanner reader = null;
		try {
			sock = new Socket(TestThread.host, TestThread.port);
			writer = new PrintWriter(sock.getOutputStream());
			reader = new Scanner(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.write("Hi server this is Thread " + this.getName() + "\n\n");
		writer.flush();
		while(reader.hasNext()) {
			if(reader.nextLine().equals("SUCCESS")) ServerTest.increaseCorrectResponses();
		}
		writer.close();
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
