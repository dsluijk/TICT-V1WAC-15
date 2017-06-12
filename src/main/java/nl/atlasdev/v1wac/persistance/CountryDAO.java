package nl.atlasdev.v1wac.persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.atlasdev.v1wac.model.Country;

public class CountryDAO extends BaseDAO {
	public ArrayList<Country> findAll() throws SQLException {
		PreparedStatement statement = super.getConnection().prepareStatement("select * from country");
		return this.formatResult(statement.executeQuery());
	}
	
	public Country findByCode(String code) throws SQLException {
		PreparedStatement statement = super.getConnection().prepareStatement("select * from country where code = ?");
		statement.setString(1, code);
		return this.formatResult(statement.executeQuery()).get(0);
	}
	
	public ArrayList<Country> find10LargestPopulations() throws SQLException {
		PreparedStatement statement = super.getConnection().prepareStatement("select * from country order by population desc limit 10");
		return this.formatResult(statement.executeQuery());
	}
	
	public ArrayList<Country> find10LargestSurfaces() throws SQLException {
		PreparedStatement statement = super.getConnection().prepareStatement("select * from country order by surfacearea desc limit 10");
		return this.formatResult(statement.executeQuery());
	}
	
	public Country update(Country country) throws SQLException {
		String query = "update country set code2 = ?, name = ?, capital = ?, continent = ?, region = ?, surfacearea = ?, population = ?, governmentform = ?, latitude = ?, longitude = ? WHERE code = ?";
		PreparedStatement statement = super.getConnection().prepareStatement(query);
		statement.setString(1, country.getCode());
		statement.setString(2, country.getName());
		statement.setString(3, country.getCapital());
		statement.setString(4, country.getContinent());
		statement.setString(5, country.getRegion());
		statement.setDouble(6, country.getSurface());
		statement.setLong(7, country.getPopulation());
		statement.setString(8, country.getGovernment());
		statement.setDouble(9, country.getLatitude());
		statement.setDouble(10, country.getLongitude());
		statement.setString(11, country.getIso3Code());
		statement.executeUpdate();
		return country;
	}
	
	public boolean delete(Country country) throws SQLException {
		PreparedStatement statement1 = super.getConnection().prepareStatement("delete from city where countrycode = ?");
		statement1.setString(1, country.getIso3Code());
		statement1.executeUpdate();
		PreparedStatement statement2 = super.getConnection().prepareStatement("delete from countrylanguage where countrycode = ?");
		statement2.setString(1, country.getIso3Code());
		statement2.executeUpdate();
		PreparedStatement statement3 = super.getConnection().prepareStatement("delete from country where code = ?");
		statement3.setString(1, country.getIso3Code());
		return statement3.executeUpdate() != 0 ? true : false;
	}
	
	private ArrayList<Country> formatResult(ResultSet resultSet) throws SQLException {
		ArrayList<Country> result = new ArrayList<Country>();
		while(resultSet.next()) {
			result.add(this.createObj(resultSet));
		}
		return result;
	}
	
	private Country createObj(ResultSet resultSet) throws SQLException {
		return new Country(
			resultSet.getString("code2"),
			resultSet.getString("code"),
			resultSet.getString("name"),
			resultSet.getString("capital"),
			resultSet.getString("continent"),
			resultSet.getString("region"),
			resultSet.getDouble("surfacearea"),
			resultSet.getInt("population"),
			resultSet.getString("governmentform"),
			resultSet.getDouble("latitude"),
			resultSet.getDouble("longitude")
		);
	}
}
