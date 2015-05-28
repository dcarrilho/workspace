package br.facom.apsoo.sisauto;

import java.sql.SQLException;
import java.util.List;

import br.facom.apsoo.sisauto.dao.ClienteDao;
import br.facom.apsoo.sisauto.dao.Database;
import br.facom.apsoo.sisauto.dao.VeiculoDao;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.Veiculo;
import br.facom.apsoo.sisauto.tela.TelaInicial;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        TelaInicial inicial = new TelaInicial();
        inicial.setVisible(true);
        inicial.setSize(300, 300);

//		ClienteDao clienteDao = new ClienteDao();
//		VeiculoDao veiculoDao = new VeiculoDao();
//
//        try {
//			Database base = new Database();
//			Cliente cliente = new Cliente();
////			cliente.setCadastro("12345678911");
////			clienteDao.adicionaCliente(cliente);
////			cliente.setCadastro("12345678912");
////			clienteDao.adicionaCliente(cliente);
////			cliente.setCadastro("12345678913");
////			clienteDao.adicionaCliente(cliente);
////			cliente.setCadastro("12345678914");
////			clienteDao.adicionaCliente(cliente);
////			cliente.setCadastro("12345678915");
////			clienteDao.adicionaCliente(cliente);
//			
//			Veiculo veiculo = new Veiculo();
//			veiculo.setModelo("Fiesta");
//			veiculoDao.adicionaVeiculo(veiculo);
//			veiculo.setModelo("Fiesta");
//			veiculoDao.adicionaVeiculo(veiculo);
//			veiculo.setModelo("Fiesta");
//			veiculoDao.adicionaVeiculo(veiculo);
//			veiculo.setModelo("Fiesta");
//			veiculoDao.adicionaVeiculo(veiculo);
//			veiculo.setModelo("Fiesta");
//			veiculoDao.adicionaVeiculo(veiculo);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//        try {
//			List<Cliente> lista = clienteDao.getAll();
//			 for (Cliente cliente : lista) {
//				 System.out.println("Cliente: "+cliente.getCadastro()+"\n");
//					
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//       
    }
//    
    
}
