package nl.atlasdev.v1wac.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// This class exists to get around the CORS headers, acts like a proxy.
// It does not work sometimes, probably because of the upstream api.

@Path("/ip")
public class IpResource {
	@GET
	@Produces("application/json")
	public String getCountries() throws IOException {
		URL api = new URL("http://ip-api.com/json");
		BufferedReader in = new BufferedReader(new InputStreamReader(api.openStream()));
		return in.readLine();
	}
}
