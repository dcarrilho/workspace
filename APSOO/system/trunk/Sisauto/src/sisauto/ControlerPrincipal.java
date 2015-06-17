/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisauto;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author fcm3
 */
public class ControlerPrincipal {
	ViewManager viewManager;
	ModelVenda modelVenda;
	ModelTestDrive modelTestDrive;

	
	ConnectionSource connectionSource;
	
	Dao<ModelVenda, Integer> daoVenda;
	Dao<ModelCliente, Integer> daoCliente;
	Dao<ModelTestDrive, Integer> daoTestDrive;
	Dao<ModelVeiculo, Integer> daoVeiculo;
	public void init(){
		viewManager= new ViewManager( this );
		try {
			connectionSource = new JdbcConnectionSource(Config.DATABASE_URL, Config.DATABASE_LOGIN, Config.DATABASE_PASSWORD);
			
			
			try{TableUtils.createTable(connectionSource, ModelVenda.class);} catch (Exception ex) {}
			try{TableUtils.createTable(connectionSource, ModelCliente.class);} catch (Exception ex) {}
			try{TableUtils.createTable(connectionSource, ModelTestDrive.class);} catch (Exception ex) {}
			try{TableUtils.createTable(connectionSource, ModelVeiculo.class);} catch (Exception ex) {}
			
			daoVenda = DaoManager.createDao(connectionSource, ModelVenda.class);
			daoCliente= DaoManager.createDao(connectionSource, ModelCliente.class);
			daoTestDrive= DaoManager.createDao(connectionSource, ModelTestDrive.class);
			daoVeiculo= DaoManager.createDao(connectionSource, ModelVeiculo.class);
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog( null, "Falha BD", "Falha BD", JOptionPane.INFORMATION_MESSAGE );
			Logger.getLogger(ControlerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}

		
		
	}
	
	public void efetuarVenda()
	{
		modelVenda= new ModelVenda();
		modelVenda.setControler(this);
		viewManager.startViewEfetuarVenda();
		
	}
	
	public void efetuarTestDrive()
	{
		modelTestDrive= new ModelTestDrive();
		modelTestDrive.setControler(this);
		viewManager.startViewTestDrive();
		
	}
	
	
	public String[][] buscarCliente( String nome, String cpf )
	{
		try {
			List<ModelCliente> clienteList;
			
			
			//daoCliente.
			clienteList = daoCliente.query(
			daoCliente.queryBuilder().where()
				.like("nome", "%"+nome+"%")
				.or()
				.eq("cpf", cpf)
				.prepare());
			
			if( clienteList!=null && clienteList.size()>0 )
			{
				ModelCliente mc;
				String [][]dados= new String[clienteList.size()][3];
				for( int i=0;i<clienteList.size();i++)
				{
					mc= clienteList.get(i);
					dados[i][0]= mc.nome;
					dados[i][1]= mc.cpf;
					dados[i][2]= String.valueOf(mc.id);
				}
				
				return dados;
				//JOptionPane.showMessageDialog( null, mc.cpf.toString(), mc.nome.toString(), JOptionPane.INFORMATION_MESSAGE );
			}
	
			
		} catch (SQLException ex) {
			Logger.getLogger(ControlerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}
	
	public void concluirVenda(String dados[][])
	{
		
		modelVenda.efetuarVenda(dados);
		int id;
		try {
			daoVenda.create(modelVenda);
			id= modelVenda.id;
			JOptionPane.showMessageDialog( null, "Venda concluida com o cliente: "+modelVenda.cliente.nome, "Concluir venda:", JOptionPane.INFORMATION_MESSAGE );
	
			
		} catch (SQLException ex) {
			Logger.getLogger(ControlerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		viewManager.startViewInicial();
		
		//JOptionPane.showMessageDialog( null, "Concluir venda", "Concluir venda:"+account2.idCliente, JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void concluirTestDrive(String dados[][])
	{
		
		modelTestDrive.efetuarTestDrive(dados);
		int id;
		try {
			daoTestDrive.create(modelTestDrive);
			id= modelTestDrive.id;
			JOptionPane.showMessageDialog( null, "Test Drive concluido com o cliente: "+modelTestDrive.cliente.nome, "Concluir venda:", JOptionPane.INFORMATION_MESSAGE );
	
			
		} catch (SQLException ex) {
			Logger.getLogger(ControlerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		viewManager.startViewInicial();
		
		//JOptionPane.showMessageDialog( null, "Concluir venda", "Concluir venda:"+account2.idCliente, JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void cancelar()
	{
		viewManager.startViewInicial();
	}
	
	public void selecionarCliente(int j)
	{
		viewManager.selecionarCliente(j);
	}
	
	public void selecionarClienteResponse(int j, int idCliente)
	{
		switch( j )
		{
			case 1:
				modelVenda.setCliente(idCliente);
				viewManager.viewEfetuarVenda.setCliente( modelVenda.cliente.nome );
				break;
			case 2:
				modelTestDrive.setCliente(idCliente);
				viewManager.viewTestDrive.setCliente( modelTestDrive.cliente.nome );
				break;
		}
		
		System.out.println("ID: "+idCliente);
		//viewManager.selecionarCliente(j);
	}
	
	
	
	
	// connectionSource.close();
}
