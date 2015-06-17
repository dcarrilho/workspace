package br.facom.apsoo.sisauto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.facom.apsoo.sisauto.factory.ConnectionFactory;
import br.facom.apsoo.sisauto.model.Veiculo;

public class VeiculoDao {

	public boolean adicionaVeiculo(Veiculo veiculo) throws SQLException {

		Connection con = new ConnectionFactory().getConnection();

		String SQL = "INSERT INTO veiculo VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement prepare = con.prepareStatement(SQL);

		prepare.setString(2, veiculo.getMarca());
		prepare.setString(3, veiculo.getModelo());
		prepare.setString(4, veiculo.getCor());
		prepare.setInt(5, veiculo.getAnoFabricacao());
		prepare.setInt(6, veiculo.getAnoModelo());
		prepare.setDouble(7, veiculo.getPreco());
		prepare.setBoolean(8, false);

		prepare.execute();

		System.out.println("Veiculo Add success");
		return false;
	}

	public List<Veiculo> getAll() throws SQLException {

		Connection con = new ConnectionFactory().getConnection();
		Statement stm = con.createStatement();
		List<Veiculo> lista = new LinkedList<Veiculo>();

		ResultSet rs = stm.executeQuery("SELECT * FROM veiculo");

		while (rs.next()) {
			Veiculo veiculo = new Veiculo();
			veiculo.setMarca(rs.getString("marca"));
			veiculo.setModelo(rs.getString("modelo"));
			lista.add(veiculo);
		}

		con.close();
		return lista;
	}

	public List<String> getAllMarca() {

		Connection con = new ConnectionFactory().getConnection();
		List<String> lista = new LinkedList<String>();
		ResultSet rs;
		try {
			Statement stm = con.createStatement();
			rs = stm.executeQuery("SELECT DISTINCT marca FROM veiculo");
			while (rs.next()) {
				lista.add(rs.getString("marca"));
			}
			con.close();
		} catch (SQLException e) {
		}

		
		return lista;
	}

	public List<String> getModelo(String smarca) throws SQLException {
		Connection con = new ConnectionFactory().getConnection();
		List<String> lista = new LinkedList<String>();
		ResultSet rs;
		Statement stm = con.createStatement();
		rs = stm.executeQuery("SELECT DISTINCT modelo FROM veiculo where marca = '"
				+ smarca + "'");
		while (rs.next()) {
			lista.add(rs.getString("modelo"));
		}
		con.close();
		return lista;
	}

	public List<String> getCor(String smodelo) throws SQLException {
		Connection con = new ConnectionFactory().getConnection();
		List<String> lista = new LinkedList<String>();
		ResultSet rs;
		Statement stm = con.createStatement();
		rs = stm.executeQuery("SELECT DISTINCT cor FROM veiculo where modelo = '"
				+ smodelo + "'");
		while (rs.next()) {
			lista.add(rs.getString("cor"));
		}
		con.close();
		return lista;

	}

	public List<String> getAno(String scor) throws SQLException {
		Connection con = new ConnectionFactory().getConnection();
		List<String> lista = new LinkedList<String>();
		ResultSet rs;
		Statement stm = con.createStatement();
		rs = stm.executeQuery("SELECT DISTINCT ano_fabricacao FROM veiculo where cor = '"
				+ scor + "'");
		while (rs.next()) {
			lista.add(rs.getString("ano_fabricacao"));
		}
		con.close();
		return lista;
	}

}
