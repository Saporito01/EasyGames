package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import it.easygames.model.bean.Cart;

public class CartControl {
	
	public synchronized static void updateCart(Cart cart) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		String userId = cart.getUserId();
		Map<String,Integer> products = cart.getProducts();
		List<String> gameId = new ArrayList<>(products.keySet());
		
		String delete = "DELETE FROM contiene WHERE carrello = ?";
		String insertSQL = "INSERT INTO contiene (carrello, gioco, quantita) VALUES (?, ?, ?)";

		try{
				connection = DriverManagerConnectionPool.getConnection();
				
				preparedStatement2 = connection.prepareStatement(delete);
				preparedStatement2.setString(1, userId);
				preparedStatement2.executeUpdate();
				connection.commit();
				
				if(products.isEmpty())
					return;
				
				for(String id : gameId) {
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, userId);
					preparedStatement.setString(2, id);
					preparedStatement.setInt(3, products.get(id));
					preparedStatement.executeUpdate();
					connection.commit();
			}
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
				if(preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public synchronized static Collection<String> getCartGames(String account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<String> listGameId = new ArrayList<String>();

		String selectSQL = "SELECT gioco.id FROM gioco, contiene WHERE contiene.gioco = gioco.id AND contiene.carrello = ?";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, account);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String item = rs.getString("id");
				listGameId.add(item);
			}
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return listGameId;
	}
}
