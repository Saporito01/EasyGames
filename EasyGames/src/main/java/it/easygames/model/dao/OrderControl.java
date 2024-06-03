package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import it.easygames.model.bean.Ordine;

public class OrderControl {
	
	private OrderControl() {
		
	}
	
	static public synchronized Collection<Ordine> load() throws SQLException {

		Connection connection = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		
		Collection<Ordine> ordini = new LinkedList<Ordine>();

		String select1 = "SELECT * FROM ordine;";
		String select2 = "SELECT gioco, quantita FROM giochi_acquistati WHERE ordine = ?";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			stmt1 = connection.prepareStatement(select1);
			
			ResultSet rs = stmt1.executeQuery();
			
			while(rs.next()) {
				Ordine ordine = new Ordine();
				
				ordine.setCodice(rs.getInt("codice"));
				ordine.setData(rs.getString("data"));
				ordine.setOra(rs.getString("ora"));
				ordine.setAccount(rs.getString("account"));
				
				stmt2 = connection.prepareStatement(select2);
				stmt2.setInt(1, ordine.getCodice());
				ResultSet rs2 = stmt2.executeQuery();
				while(rs2.next())
					ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
				
				ordini.add(ordine);
			}
		}
		finally
		{
			try
			{
				if (stmt1 != null)
					stmt1.close();
				if (stmt2 != null)
					stmt2.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return ordini;
	}
	
	static public synchronized Collection<Ordine> loadForUser(String user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement stmt2 = null;
		
		Collection<Ordine> ordini = new LinkedList<Ordine>();

		//prende tutti gli ordini dell'ultimo mese di un determinato utente
		String selectSQL = "SELECT * FROM ordine WHERE account = ? AND data >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) ORDER BY data";

		String select2 = "SELECT gioco, quantita FROM giochi_acquistati WHERE ordine = ?";
		
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Ordine ordine = new Ordine();
				
				ordine.setCodice(rs.getInt("codice"));
				ordine.setData(rs.getString("data"));
				ordine.setOra(rs.getString("ora"));
				ordine.setAccount(rs.getString("account"));
				
				stmt2 = connection.prepareStatement(select2);
				stmt2.setInt(1, ordine.getCodice());
				ResultSet rs2 = stmt2.executeQuery();
				while(rs2.next())
					ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
				
				ordini.add(ordine);
			}
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
				if (stmt2 != null)
					stmt2.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return ordini;
	}
	
	static public synchronized ArrayList<String> loadOrderAccount() throws SQLException {
		//valutare la rimozione di questo metodo, posso recuperare la stessa informazione tramite un metodo del dao per gli account

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<String> account = new ArrayList<String>();

		String selectSQL = "SELECT DISTINCT account FROM ordine";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
				account.add(rs.getString("account"));
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
		
		return account;
	}
	
	
	static public synchronized void doSave(Ordine ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		Map<String,Integer> products = ordine.getProducts();
		List<String> gameId = new ArrayList<>(products.keySet());

		try {
			connection =DriverManagerConnectionPool.getConnection();
			
			String insert1 = "INSERT INTO ordine (data, ora, account) VALUES (?, ?, ?)";
			stmt1 = connection.prepareStatement(insert1);
			stmt1.setString(1, ordine.getData());
			stmt1.setString(2, ordine.getOra());
			stmt1.setString(3, ordine.getAccount());
			stmt1.executeUpdate();
			
			String select = "SELECT codice FROM ordine WHERE data = ? AND ora = ? AND account = ?";
			stmt2 = connection.prepareStatement(select);
			stmt2.setString(1, ordine.getData());
			stmt2.setString(2, ordine.getOra());
			stmt2.setString(3, ordine.getAccount());
			ResultSet rs = stmt2.executeQuery();
			
			int codice = 0;
	        if (rs.next()) {
	            codice = rs.getInt("codice");
	        } else {
	            throw new SQLException("Errore nel recupero del codice ordine.");
	        }
			
			String insert2 = "INSERT INTO giochi_acquistati (gioco, ordine, quantita) VALUES (?, ?, ?)";
			stmt3 = connection.prepareStatement(insert2);
			for(String id : gameId) {
				stmt3.setString(1, id);
				stmt3.setInt(2, codice);
				stmt3.setInt(3, products.get(id));
				stmt3.executeUpdate();
			}
			
			connection.commit();
		} finally {
			try {
				if(stmt1 != null)
					stmt1.close();
				if(stmt2 != null)
					stmt2.close();
				if(stmt3 != null)
					stmt3.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	
	static public synchronized Collection<Ordine> doRetrieveByDate(String data1, String data2, String account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement stmt2 = null;
		String select2 = "SELECT gioco, quantita FROM giochi_acquistati WHERE ordine = ?";
		
		Collection<Ordine> ordini = new LinkedList<Ordine>();

		try
		{
			if(!data1.equalsIgnoreCase("") && !data2.equalsIgnoreCase("") && account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data BETWEEN ? AND ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, data1);
				preparedStatement.setString(2, data2);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else if(!data1.equalsIgnoreCase("") && data2.equalsIgnoreCase("") && account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data >= ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, data1);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else if(data1.equalsIgnoreCase("") && !data2.equalsIgnoreCase("") && account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data <= ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, data2);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else if(data1.equalsIgnoreCase("") && data2.equalsIgnoreCase("") && !account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE account = ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, account);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else if(data1.equalsIgnoreCase("") && data2.equalsIgnoreCase("") && account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine ORDER BY codice";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else if(!data1.equalsIgnoreCase("") && data2.equalsIgnoreCase("") && !account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data >= ? AND account = ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, data1);
				preparedStatement.setString(2, account);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else if(data1.equalsIgnoreCase("") && !data2.equalsIgnoreCase("") && !account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data <= ? AND account = ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, data2);
				preparedStatement.setString(2, account);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
			else
			{
				String selectSQL = "SELECT * FROM ordine WHERE account = ? AND data BETWEEN ? AND ?";
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, account);
				preparedStatement.setString(2, data1);
				preparedStatement.setString(3, data2);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					Ordine ordine = new Ordine();
					
					ordine.setCodice(rs.getInt("codice"));
					ordine.setData(rs.getString("data"));
					ordine.setOra(rs.getString("ora"));
					ordine.setAccount(rs.getString("account"));
					
					stmt2 = connection.prepareStatement(select2);
					stmt2.setInt(1, ordine.getCodice());
					ResultSet rs2 = stmt2.executeQuery();
					while(rs2.next())
						ordine.addProduct(rs2.getString("gioco"), rs2.getInt("quantita"));
					
					ordini.add(ordine);
				}
			}
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
				if (stmt2 != null)
					stmt2.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return ordini;
	}
}
