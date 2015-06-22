package br.facom.apsoo.sisauto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.facom.apsoo.sisauto.factory.ConnectionFactory;
import br.facom.apsoo.sisauto.model.Venda;

public class VendaDao {

	public boolean adicionaVenda(Venda venda) throws SQLException {

		Connection con = new ConnectionFactory().getConnection();

		String SQL = "INSERT INTO venda VALUES (?,?,?,?,?)";

		PreparedStatement prepare = con.prepareStatement(SQL);

		prepare.setString(2, venda.getCliente());
		prepare.setLong(3, venda.getVeiculo());
		prepare.setBoolean(4, venda.isFinanciado());
		prepare.setDate(5, null);

		prepare.execute();

		con.close();

		System.out.println("Add success");
		return false;
	}

	public List<Venda> getAll() throws SQLException {

		Connection con = new ConnectionFactory().getConnection();
		Statement stm = con.createStatement();
		List<Venda> lista = new LinkedList<Venda>();

		ResultSet rs = stm.executeQuery("SELECT * FROM venda");

		while (rs.next()) {
			Venda venda = new Venda();
			venda.setCliente(rs.getString("cliente"));
			venda.setVeiculo(rs.getLong("veiculo"));
			lista.add(venda);
		}

		return lista;
	}

}
