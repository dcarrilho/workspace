package br.facom.apsoo.sisauto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.facom.apsoo.sisauto.factory.ConnectionFactory;
import br.facom.apsoo.sisauto.model.TestDrive;
import br.facom.apsoo.sisauto.model.Venda;

public class TesteDao {
	
	public boolean adicionaTeste(TestDrive testDrive) throws SQLException {

		Connection con = new ConnectionFactory().getConnection();

		String SQL = "INSERT INTO test VALUES (?,?,?,?,?,?,?,? )";

		PreparedStatement prepare = con.prepareStatement(SQL);

		prepare.setString(2, testDrive.getCliente());
		prepare.setString(3, testDrive.getVeiculo());
		prepare.setString(4, testDrive.getHsaida());
		prepare.setString(5, testDrive.getHchegada());;
		prepare.setString(6, testDrive.getKmsaida());
		prepare.setString(7, testDrive.getKmchegada());

		prepare.execute();

		con.close();

		System.out.println("Add success");
		return false;
	}

}
