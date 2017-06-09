package nl.atlasdev.v1wac.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

// This class exists to get around the CORS headers, acts like a proxy.

@Path("/weather")
public class WeatherResource {
	private final String apiKey = "d10dfbec852f67713ccde8e527503ea1";
	
	@GET
	@Produces("application/json")
	public String getCountries(
		@QueryParam("lat") double lat,
		@QueryParam("lon") double lon
	) throws IOException {
		URL api = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&APPID="+this.apiKey);
		BufferedReader in = new BufferedReader(new InputStreamReader(api.openStream()));
		return in.readLine();
	}
}
