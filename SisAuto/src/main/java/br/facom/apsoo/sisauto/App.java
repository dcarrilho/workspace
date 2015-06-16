package br.facom.apsoo.sisauto;

import java.sql.SQLException;
import java.util.List;

import br.facom.apsoo.sisauto.dao.ClienteDao;
import br.facom.apsoo.sisauto.dao.Database;
import br.facom.apsoo.sisauto.dao.VeiculoDao;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.Veiculo;
import br.facom.apsoo.sisauto.tela.TelaAdicionaCliente;
import br.facom.apsoo.sisauto.tela.TelaAdicionaVeiculo;
import br.facom.apsoo.sisauto.tela.TelaInicial;
import br.facom.apsoo.sisauto.tela.TelaVenda;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

//		TelaInicial inicial = new TelaInicial();
//		inicial.setVisible(true);
//		inicial.setSize(300, 300);
//		
		try {
			new TelaVenda();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Database base = new Database();
		} catch (SQLException e) {
			e.printStackTrace();
			//
			//
		}
	}
	//

}
