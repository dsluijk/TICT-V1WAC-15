package nl.atlasdev.v1wac.standalone.les1.p5;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ServerTest {
	private static int nothreads = 120;
	private static int correctthreads = 0;
	private static Date startTime;
	private static ArrayList<TestThread> threads = new ArrayList<TestThread>();

	public static void main(String[] args) {
		for(int i = 0; i < ServerTest.nothreads; i++) ServerTest.threads.add(new TestThread());
		ServerTest.startTime = new Date();
		Iterator<TestThread> iter = threads.iterator();
		while(iter.hasNext()) iter.next().start();
	}

	public static synchronized void increaseCorrectResponses() {
		ServerTest.correctthreads++;
		System.out.println("Finished " + ServerTest.correctthreads + "/" + ServerTest.nothreads + " requests.");
		if(ServerTest.correctthreads == ServerTest.nothreads) {
			System.out.println("All responses were correct!");
			System.out.println("Finished in " + (new Date().getTime() - ServerTest.startTime.getTime()) + "ms.");
		}
	}
}
