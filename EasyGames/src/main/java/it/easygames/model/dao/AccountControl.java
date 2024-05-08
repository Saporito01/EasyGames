package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import it.easygames.model.bean.Account;

public class AccountControl {
	public synchronized static Collection<Account> load() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Account> accounts = new LinkedList<Account>();

		String selectSQL = "SELECT * FROM account ORDER BY nickname";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				
				account.setNickname(rs.getString("nickname"));
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setDataNascita(rs.getString("data_nascita"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				
				accounts.add(account);
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
		
		return accounts;
	}
	
	
	public synchronized static void doSave(Account account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;

		String insertSQL1 = "INSERT INTO account (nickname, nome, cognome, data_nascita, email, password) VALUES (?, ?, ?, ?, ?, ?)";
		String insertSQL2 = "INSERT INTO cliente (account) VALUES (?)";
		String insertSQL3 = "INSERT INTO carrello (account) VALUES (?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			preparedStatement1 = connection.prepareStatement(insertSQL1);
			preparedStatement1.setString(1, account.getNickname());
			preparedStatement1.setString(2, account.getNome());
			preparedStatement1.setString(3, account.getCognome());
			preparedStatement1.setString(4, account.getDataNascita());
			preparedStatement1.setString(5, account.getEmail());
			preparedStatement1.setString(6, account.getPassword());
			preparedStatement1.executeUpdate();
			
			preparedStatement2 = connection.prepareStatement(insertSQL2);
			preparedStatement2.setString(1, account.getNickname());
			preparedStatement2.executeUpdate();
			
			preparedStatement3 = connection.prepareStatement(insertSQL3);
			preparedStatement3.setString(1, account.getNickname());
			preparedStatement3.executeUpdate();

			connection.commit();
		} finally {
			try {
				if(preparedStatement1 != null && preparedStatement2 != null && preparedStatement3 != null)
				{
					preparedStatement1.close();
					preparedStatement2.close();
					preparedStatement3.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized static Collection<Account> getAdmin() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Account> accounts = new LinkedList<Account>();

		String selectSQL = "SELECT A.nickname, A.nome, A.cognome, A.data_nascita, A.email, A.password FROM account AS A, amministratore WHERE A.nickname = amministratore.account";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				
				account.setNickname(rs.getString("nickname"));
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setDataNascita(rs.getString("data_nascita"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				
				accounts.add(account);
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
		
		return accounts;
	}
	
	public synchronized static Collection<Account> getUsers() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Account> accounts = new LinkedList<Account>();

		String selectSQL = "SELECT A.nickname, A.nome, A.cognome, A.data_nascita, A.email, A.password FROM account AS A, cliente WHERE A.nickname = cliente.account";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				
				account.setNickname(rs.getString("nickname"));
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setDataNascita(rs.getString("data_nascita"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				
				accounts.add(account);
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
		
		return accounts;
	}
}
