package it.easygames.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoverControl {
	
	public synchronized static byte[] load(String id) throws SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			String sql = "SELECT copertina FROM gioco WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("copertina");
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bt;
	}
	
	public synchronized static void updateCover(String id, InputStream cover) 
			throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			stmt = connection.prepareStatement("UPDATE gioco SET copertina = ? WHERE id = ?");
			try {
				stmt.setBinaryStream(1, cover, cover.available());
				stmt.setString(2, id);	
				stmt.executeUpdate();
				connection.commit();
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
