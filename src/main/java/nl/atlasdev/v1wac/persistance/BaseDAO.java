package nl.atlasdev.v1wac.persistance;

import java.net.URI;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class BaseDAO {
	private DataSource connectionPool;

	public BaseDAO() {
		try {
			final String DATABASE_URL_PROP = System.getenv("DATABASE_URL");
			if (DATABASE_URL_PROP != null) {
				URI dbUri = new URI(DATABASE_URL_PROP);
				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
				BasicDataSource pool = new BasicDataSource();
				if (dbUri.getUserInfo() != null) {
					pool.setUsername(dbUri.getUserInfo().split(":")[0]);
					pool.setPassword(dbUri.getUserInfo().split(":")[1]);
				}
				pool.setDriverClassName("org.postgresql.Driver");
				pool.setUrl(dbUrl);
				pool.setInitialSize(1);
				connectionPool = pool;
			} else {
				InitialContext ic = new InitialContext();
				connectionPool = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected final Connection getConnection() {
		try {
			return connectionPool.getConnection();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}