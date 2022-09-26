package IDao;

import java.sql.SQLException;

import entities.Response;

public interface Dao {
	public Response deleteAll() throws SQLException;
	public Response createTable() throws SQLException;
}
