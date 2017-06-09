package nl.atlasdev.v1wac.standalone.les1.p3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket("127.0.0.1", 4711);
		PrintWriter writer = new PrintWriter(sock.getOutputStream());
		Scanner key = new Scanner(System.in);

		while(key.hasNextLine()) {
			String input = key.nextLine();
			if(input.equals("stop")) break;
			writer.write(input + '\n');
		}

		key.close();
		writer.close();
		sock.close();
	}
}
