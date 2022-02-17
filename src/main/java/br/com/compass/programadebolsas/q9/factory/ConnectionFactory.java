package br.com.compass.programadebolsas.q9.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;
	
	public ConnectionFactory() {
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/avalicao_sprint_2?useTimezone=true&serverTimezone=UTC");
		cpds.setUser("root");
		cpds.setPassword("123Andre**EE");
		
		cpds.setMaxPoolSize(15);
		
		this.dataSource = cpds;
	}	
	
	public Connection recuperaConexao() {
		try {
			return this.dataSource.getConnection();
		}catch(SQLException e) {
			throw new  RuntimeException(e);
		}
	}
	
}
