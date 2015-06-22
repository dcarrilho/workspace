package br.facom.apsoo.sisauto.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.facom.apsoo.sisauto.factory.ConnectionFactory;

public class Database {
	
	public Database() throws SQLException{
		Connection con = new ConnectionFactory().getConnection();
		Statement stm;
		con.setCatalog("bd");
		stm = con.createStatement();
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS cliente ( "
				+ " cadastro varchar(14) PRIMARY KEY NOT NULL,"
				+ " nome varchar(70),"
				+ " endereco varchar(256),"
				+ " cidade varchar(70),"
				+ " estado varchar(2),"
				+ " data date);");
		
	
		
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS veiculo ( id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " marca varchar(70),"
				+ " modelo varchar(70),"
				+ " cor varchar(14),"
				+ " ano_fabricacao integer,"
				+ " ano_modelo integer,"
				+ " preco real,"
				+ " vendido boolean);");
		
		
		
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS venda ( id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " cliente_id varchar(14),"
				+ " veiculo_id integer,"
				+ " financiado boolean,"
				+ " data date,"
				+ " FOREIGN KEY(cliente_id) REFERENCES cliente(cadastro),"
				+ " FOREIGN KEY(veiculo_id) REFERENCES veiculo(id));");
		
		
		stm.executeUpdate("CREATE TABLE IF NOT EXISTS test ( id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " cliente_id varchar(14),"
				+ " veiculo_id integer,"
				+ " hsaida varchar(10),"
				+ " hchegada varchar(10),"
				+ " kmsaida varchar(10),"
				+ " kmchegada varchar(10),"
				+ " data date,"
				+ " FOREIGN KEY(cliente_id) REFERENCES cliente(cadastro),"
				+ " FOREIGN KEY(veiculo_id) REFERENCES veiculo(id));");
		
		
		stm.close();
		con.close();
	}

}
