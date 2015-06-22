package br.facom.apsoo.sisauto;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.facom.apsoo.sisauto.dao.Database;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.Veiculo;
import br.facom.apsoo.sisauto.tela.TelaAdicionaCliente;
import br.facom.apsoo.sisauto.tela.TelaAdicionaVeiculo;
import br.facom.apsoo.sisauto.tela.TelaBuscaCliente;
import br.facom.apsoo.sisauto.tela.TelaInicial;
import br.facom.apsoo.sisauto.tela.TelaTestDrive;
import br.facom.apsoo.sisauto.tela.TelaVenda;

public class Controller {

	Database base;
	private TelaInicial telaInicial;
	private TelaVenda telaVenda;
	private TelaTestDrive telaTestDrive;

	public void iniciar() {
		try {
			iniciarDataBase();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO",
					"Problemas ao iniciar o BD",
					JOptionPane.INFORMATION_MESSAGE);
		}
		telaInicial = new TelaInicial(this);
	}

	private void iniciarDataBase() throws SQLException {
		base = new Database();
	}

	public void cadastrarCliente() {
		new TelaAdicionaCliente();
	}

	public void cadastrarVeiculo() {
		new TelaAdicionaVeiculo();
	}

	public void venderVeiculo() {
		telaVenda = new TelaVenda(this);
	}
	
	public void TestarVeiculo() {
		telaTestDrive = new TelaTestDrive(this);
		
	}

	public void retornaClienteParaVenda(Cliente cliente) {
		telaVenda.setCliente(cliente);
	}
	
	public void retornaClienteParaTest(Cliente cliente) {
		telaTestDrive.setCliente(cliente);
	}

	public void fecharTela(JFrame frame) {
		frame.dispose();
	}

	public void retornaVeiculo(Veiculo veiculo) {
		telaVenda.setVeiculo(veiculo);

	}

	public void buscarCliente(Class cl){
		new TelaBuscaCliente(cl, this);
	}

	public void retornaCliente(Class pai, Cliente cliente) {
		if(pai.getName().equalsIgnoreCase("br.facom.apsoo.sisauto.tela.TelaVenda$1"))
			retornaClienteParaVenda(cliente);
		else
			retornaClienteParaTest(cliente);
		
	}

}
