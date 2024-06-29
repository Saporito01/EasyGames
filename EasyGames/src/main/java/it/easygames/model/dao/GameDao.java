package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.easygames.model.bean.Game;

public class GameDao implements IGameDao{
	
private static final String TABLE_NAME = "gioco";
	
	@Override
	public synchronized void doSave(Game game) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + GameDao.TABLE_NAME
				+ " (id, nome, descrizione, piattaforma, quantita, prezzo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	@Override
	public synchronized void doSaveGenere(String id, String[] genere) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			
			String insertSQL = "INSERT INTO appartienegenere (gioco,genere) VALUES (?,?)";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			for(int i=0;i<genere.length;i++)
			{	
				preparedStatement.setString(1, id); //questo può essere portato fuori dal for?
				preparedStatement.setString(2, genere[i]);

				preparedStatement.executeUpdate();

				connection.commit();
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	@Override
	public synchronized boolean doDelete(String id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + GameDao.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, id);

			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Game doRetrieveByKey(String id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Game item = new Game();

		String selectSQL = "SELECT * FROM " + GameDao.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
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
				DriverManagerConnectionPool.releaseConnection(connection);
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
			connection = DriverManagerConnectionPool.getConnection();
			
			if(piattaforma.equals("tutto"))
			{
				String sql = "SELECT * FROM " + GameDao.TABLE_NAME + " WHERE nome LIKE ?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, "%"+nome+"%");
				
				rs = stmt.executeQuery();
			}
			else
			{
				String sql = "SELECT * FROM " + GameDao.TABLE_NAME + " WHERE nome LIKE ? AND piattaforma = ?";
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return model;
	}
	
	@Override
	public synchronized Collection<Game> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Game> games = new LinkedList<Game>();

		String selectSQL = "SELECT * FROM " + GameDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return games;
	}
	
	@Override
	public synchronized void doUpdate(Game game) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "UPDATE " + GameDao.TABLE_NAME + " SET nome = ?, descrizione = ?, piattaforma = ?, quantita = ?, prezzo = ? WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized Collection<Game> getToHomePage() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Game> games = new LinkedList<Game>();

		String selectSQL = "SELECT gioco.* FROM gioco JOIN (SELECT gioco FROM giochi_acquistati GROUP BY gioco ORDER BY COUNT(*) DESC LIMIT 9) giochi_acquistati ON gioco.id = giochi_acquistati.gioco;";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return games;
	}
	
	public synchronized Collection<Game> getForGenere(String genere) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Game> games = new LinkedList<Game>();

		String selectSQL = "SELECT gioco.* FROM gioco, appartienegenere WHERE gioco.id = appartienegenere.gioco AND genere = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, genere);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
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
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return games;
	}
	
	public synchronized void removeSoldGames(Map<String,Integer> games) throws SQLException{
		Connection connection = DriverManagerConnectionPool.getConnection();
		PreparedStatement preparedStatement = null;
		List<String> gameId = new ArrayList<>(games.keySet());

		String updateSQL = "UPDATE gioco SET quantita = quantita - ? WHERE id = ?";
		
		try {
			
			for(String id : gameId) {
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setInt(1, games.get(id));
				preparedStatement.setString(2, id);
				preparedStatement.executeUpdate();
				connection.commit();
			}
			
			} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized int quantityCheck(String id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int qt = 0;

		String selectSQL = "SELECT quantita FROM " + GameDao.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next())
				qt = rs.getInt("quantita");

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return qt;
	}
}