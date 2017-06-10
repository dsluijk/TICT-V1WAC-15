package nl.atlasdev.v1wac.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.atlasdev.v1wac.persistance.CountryDAO;

public class WorldService {
	private CountryDAO dao;

	public WorldService() {
		this.dao = new CountryDAO();
	}

	public ArrayList<Country> getAllCountries() throws SQLException {
		return this.dao.findAll();
	}

	public ArrayList<Country> get10LargestPopulations() throws SQLException {
		return this.dao.find10LargestPopulations();
	}

	public ArrayList<Country> get10LargestSurfaces() throws SQLException {
		return this.dao.find10LargestSurfaces();
	}

	public Country getCountryByCode(String code) throws SQLException {
		return this.dao.findByCode(code);
	}
}
