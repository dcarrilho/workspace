package br.facom.apsoo.sisauto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.facom.apsoo.sisauto.factory.ConnectionFactory;
import br.facom.apsoo.sisauto.model.Cliente;

public class ClienteDao {

	public boolean adicionaCliente(Cliente cliente) throws SQLException {
		
		Connection con = new ConnectionFactory().getConnection();
		
		String SQL = "INSERT INTO cliente VALUES (?,?,?,?,?,?)";
		
        PreparedStatement prepare = con.prepareStatement(SQL);
        
        prepare.setString(1, cliente.getCadastro());
        prepare.setString(2, cliente.getNome());
        prepare.setString(3, cliente.getEndereco());
        prepare.setString(4, cliente.getCidade());
        prepare.setString(5, cliente.getEstado());
        java.util.Date date = new Date(Calendar.getInstance().getTimeInMillis());
		prepare.setDate(6, new java.sql.Date(date.getTime()));
        
        prepare.execute();

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
			cliente.setNome(rs.getString("nome"));
			lista.add(cliente);
		}
		
		return lista;
	}

	public String[][] getWithName(String text) throws SQLException {
		
		Connection con = new ConnectionFactory().getConnection();
		Statement stm = con.createStatement();
		List<Cliente> lista = new LinkedList<Cliente>();
		
		ResultSet rs = stm.executeQuery("SELECT * FROM cliente WHERE nome LIKE '%"+text+"%'");
		
		while(rs.next()){
			Cliente cliente = new Cliente();
			cliente.setCadastro(rs.getString("cadastro"));
			cliente.setNome(rs.getString("nome"));
			lista.add(cliente);
		}
		
		if( lista!=null && lista.size()>0 )
		{
			Cliente mc;
			String [][]dados= new String[lista.size()][3];
			for( int i=0;i<lista.size();i++)
			{
				mc= lista.get(i);
				dados[i][0]= mc.getNome();
				dados[i][1]= mc.getCadastro();
			}
			
			return dados;
		}
		
		return null;
	}
	
public String[][] getWithCpf(String text) throws SQLException {
		
		Connection con = new ConnectionFactory().getConnection();
		Statement stm = con.createStatement();
		List<Cliente> lista = new LinkedList<Cliente>();
		
		ResultSet rs = stm.executeQuery("SELECT * FROM cliente WHERE cadastro = '"+text+"'");
		
		while(rs.next()){
			Cliente cliente = new Cliente();
			cliente.setCadastro(rs.getString("cadastro"));
			cliente.setNome(rs.getString("nome"));
			lista.add(cliente);
		}
		
		if( lista!=null && lista.size()>0 )
		{
			Cliente mc;
			String [][]dados= new String[lista.size()][3];
			for( int i=0;i<lista.size();i++)
			{
				mc= lista.get(i);
				dados[i][0]= mc.getNome();
				dados[i][1]= mc.getCadastro();
			}
			
			return dados;
		}
		
		return null;
	}

}
