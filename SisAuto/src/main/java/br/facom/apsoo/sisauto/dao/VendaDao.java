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
import br.facom.apsoo.sisauto.model.Venda;

public class VendaDao {
	
public boolean adicionaCliente(Venda venda) throws SQLException {
		
		Connection con = new ConnectionFactory().getConnection();
		Statement stmt = null;
		
		String SQL = "INSERT INTO venda VALUES (?,?,?,?)";
		
        PreparedStatement prepare = con.prepareStatement(SQL);
        
        prepare.setString(1, venda.getCliente());
        prepare.setLong(2, venda.getVeiculo());
		prepare.setDate(3, null);
        
        prepare.execute();

        System.out.println("Add success");
		return false;
	}
	
	public List<Cliente> getAll() throws SQLException{
		
		Connection con = new ConnectionFactory().getConnection();
		Statement stm = con.createStatement();
		List<Cliente> lista = new LinkedList<Cliente>();
		
		ResultSet rs = stm.executeQuery("SELECT * FROM cliente");
		
		while(rs.next()){
			Cliente cliente = new Cliente();
			cliente.setCadastro(rs.getString("cadastro"));
			lista.add(cliente);
		}
		
		return lista;
	}


}
