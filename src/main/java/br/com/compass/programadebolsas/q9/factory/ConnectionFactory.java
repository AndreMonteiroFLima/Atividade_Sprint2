package br.com.compass.programadebolsas.q9.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import br.com.compass.programadebolsas.q9.exceptions.NoDataBaseException;

public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() throws NoDataBaseException {
		try {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setJdbcUrl("jdbc:mysql://localhost/avaliacao_sprint_2?useTimezone=true&serverTimezone=UTC");
			cpds.setUser("root");
			cpds.setPassword("123Andre**EE");
			
			cpds.setMaxPoolSize(15);
			
			this.dataSource = cpds;
		} catch (Exception e) {
			throw new NoDataBaseException();
		}
		
	}

	public Connection recuperaConexao() throws NoDataBaseException {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new NoDataBaseException();
		}
	}

}
