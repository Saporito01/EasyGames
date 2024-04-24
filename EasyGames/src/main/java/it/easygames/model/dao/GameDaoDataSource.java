package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.tomcat.jdbc.pool.DataSource;
import it.easygames.model.bean.Game;

public class GameDaoDataSource implements IGameDao{
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/easygames");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
private static final String TABLE_NAME = "gioco";
	
	@Override
	public synchronized void doSave(Game game) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + GameDaoDataSource.TABLE_NAME
				+ " (id, nome, descrizione, piattaforma, quantita, prezzo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, game.getId());
			preparedStatement.setString(2, game.getName());
			preparedStatement.setString(3, game.getDesc());
			preparedStatement.setString(4, game.getPlatf());
			preparedStatement.setInt(5, game.getQt());
			preparedStatement.setFloat(6, game.getPrice());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public synchronized void doSaveGenere(String id, String[] genere) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			for(int i=0;i<genere.length;i++)
			{
				String insertSQL = "INSERT INTO appartienegenere (gioco,genere) VALUES (?,?)";

				connection = ds.getConnection();
				
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setString(1, id);
				preparedStatement.setString(2, genere[i]);


				preparedStatement.executeUpdate();

				connection.commit();
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public synchronized boolean doDelete(String id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + GameDaoDataSource.TABLE_NAME + " WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, id);

			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Game doRetrieveByKey(String id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Game item = new Game();

		String selectSQL = "SELECT * FROM " + GameDaoDataSource.TABLE_NAME + " WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setId(rs.getString("id"));
				item.setName(rs.getString("nome"));
				item.setDesc(rs.getString("descrizione"));
				item.setPlatf(rs.getString("piattaforma"));
				item.setQt(rs.getInt("quantita"));
				item.setPrice(rs.getFloat("prezzo"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		return item;
	}

	@Override
	public synchronized Collection<Game> searchBarGame(String nome, String piattaforma) throws SQLException{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Game> model = new ArrayList<Game>();
		
		try {
			connection = ds.getConnection();
			
			if(piattaforma.equals("tutto"))
			{
				String sql = "SELECT * FROM " + GameDaoDataSource.TABLE_NAME + " WHERE nome LIKE ?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, "%"+nome+"%");
				
				rs = stmt.executeQuery();
			}
			else
			{
				String sql = "SELECT * FROM " + GameDaoDataSource.TABLE_NAME + " WHERE nome LIKE ? AND piattaforma = ?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, "%"+nome+"%");
				stmt.setString(2, piattaforma);
				
				rs = stmt.executeQuery();
			}
			
			while(rs.next()) {
				Game item = new Game();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("nome"));
				item.setDesc(rs.getString("descrizione"));
				item.setPlatf(rs.getString("piattaforma"));
				item.setQt(rs.getInt("quantita"));
				item.setPrice(rs.getFloat("prezzo"));
				
				model.add(item);
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
				if (connection != null) 
					if(connection != null)
						connection.close();
			}
		}
		return model;
	}
	
	@Override
	public synchronized Collection<Game> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Game> games = new LinkedList<Game>();

		String selectSQL = "SELECT * FROM " + GameDaoDataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Game item = new Game();

				item.setId(rs.getString("id"));
				item.setName(rs.getString("nome"));
				item.setDesc(rs.getString("descrizione"));
				item.setPlatf(rs.getString("piattaforma"));
				item.setQt(rs.getInt("quantita"));
				item.setPrice(rs.getFloat("prezzo"));
				
				games.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		return games;
	}
	
	@Override
	public synchronized void doUpdate(Game game) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "UPDATE " + GameDaoDataSource.TABLE_NAME + " SET nome = ?, descrizione = ?, piattaforma = ?, quantita = ?, prezzo = ? WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, game.getName());
			preparedStatement.setString(2, game.getDesc());
			preparedStatement.setString(3, game.getPlatf());
			preparedStatement.setInt(4, game.getQt());
			preparedStatement.setFloat(5, game.getPrice());
			preparedStatement.setString(6, game.getId());

			preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
	}
	
}
