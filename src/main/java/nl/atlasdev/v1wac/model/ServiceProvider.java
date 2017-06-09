package nl.atlasdev.v1wac.model;

public class ServiceProvider {
	private static WorldService worldService = new WorldService();

	public static WorldService getWorldService() {
		return worldService;
	}
}