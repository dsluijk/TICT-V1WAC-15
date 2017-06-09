package nl.atlasdev.les4.model;

public class Country {
	private String iso2Code;
	private String iso3Code;
	private String name;
	private String capital;
	private String continent;
	private String region;
	private double surface;
	private int population;
	private String government;
	private double latitude;
	private double longitude;

	public Country(String iso2cd, String iso3cd, String nm, String cap, String ct, String reg, double sur, int pop, String gov, double lat, double lng) {
		iso2Code = iso2cd;
		iso3Code = iso3cd;
		name = nm;
		capital = cap;
		continent = ct;
		region = reg;
		surface = sur;
		population = pop;
		government = gov;
		latitude = lat;
		longitude = lng;
	}

	public String getCode() {
		return iso2Code;
	}

	public String getIso3Code() {
		return iso3Code;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public String getContinent() {
		return continent;
	}

	public String getRegion() {
		return region;
	}

	public double getSurface() {
		return surface;
	}

	public int getPopulation() {
		return population;
	}

	public String getGovernment() {
		return government;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
}
