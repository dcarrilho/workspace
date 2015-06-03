package br.facom.apsoo.sisauto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.facom.apsoo.sisauto.factory.ConnectionFactory;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.Veiculo;

public class VeiculoDao {
	
public boolean adicionaVeiculo(Veiculo veiculo) throws SQLException {
		
		Connection con = new ConnectionFactory().getConnection();
		Statement stmt = null;
		
		String SQL = "INSERT INTO veiculo VALUES (?,?,?,?,?,?)";
		
        PreparedStatement prepare = con.prepareStatement(SQL);
        
        prepare.setString(1, veiculo.getMarca());
        prepare.setString(2, veiculo.getModelo());
        prepare.setString(3, veiculo.getCor());
        prepare.setInt(4, veiculo.getAnoFabricacao());
        prepare.setInt(5, veiculo.getAnoModelo());
		prepare.setDouble(6, veiculo.getPreco());
        
        prepare.execute();

        System.out.println("Veiculo Add success");
		return false;
	}
	
	public List<Veiculo> getAll() throws SQLException{
		
		Connection con = new ConnectionFactory().getConnection();
		Statement stm = con.createStatement();
		List<Veiculo> lista = new LinkedList<Veiculo>();
		
		ResultSet rs = stm.executeQuery("SELECT * FROM veiculo");
		
		while(rs.next()){
			Veiculo veiculo = new Veiculo();
			veiculo.setMarca(rs.getString("marca"));
			veiculo.setModelo(rs.getString("modelo"));
			lista.add(veiculo);
		}
		
		return lista;
	}

}
