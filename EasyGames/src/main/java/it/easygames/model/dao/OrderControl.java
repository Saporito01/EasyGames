package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;

import it.easygames.model.bean.Ordine;

public class OrderControl {
	public synchronized static Collection<Ordine> load() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Ordine> ordini = new LinkedList<Ordine>();

		String selectSQL = "SELECT * FROM ordine ORDER BY codice";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Ordine ordine = new Ordine();
				
				ordine.setCodice(rs.getInt("codice"));
				ordine.setData(rs.getString("data"));
				ordine.setOra(rs.getString("ora"));
				ordine.setAccount(rs.getString("account"));
				
				ordini.add(ordine);
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
		
		return ordini;
	}
	
	
	public synchronized static ArrayList<String> loadOrderAccount() throws SQLException {

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
	
	
	public synchronized static void doSave(Ordine ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO ordine (data, ora, account) VALUES (?, ?, ?)";

		try {
			connection =DriverManagerConnectionPool.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ordine.getData());
			preparedStatement.setString(2, ordine.getOra());
			preparedStatement.setString(3, ordine.getAccount());

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
	
	
	public synchronized static Collection<Ordine> doRetrieveByDate(String data1, String data2, String account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
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
					
					ordini.add(ordine);
				}
			}
			else if(!data1.equalsIgnoreCase("") && data2.equalsIgnoreCase("") && account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data > ?";
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
					
					ordini.add(ordine);
				}
			}
			else if(data1.equalsIgnoreCase("") && !data2.equalsIgnoreCase("") && account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data < ?";
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
					
					ordini.add(ordine);
				}
			}
			else if(!data1.equalsIgnoreCase("") && data2.equalsIgnoreCase("") && !account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data > ? AND account = ?";
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
					
					ordini.add(ordine);
				}
			}
			else if(data1.equalsIgnoreCase("") && !data2.equalsIgnoreCase("") && !account.equalsIgnoreCase("tutto"))
			{
				String selectSQL = "SELECT * FROM ordine WHERE data < ? AND account = ?";
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
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return ordini;
	}
}
