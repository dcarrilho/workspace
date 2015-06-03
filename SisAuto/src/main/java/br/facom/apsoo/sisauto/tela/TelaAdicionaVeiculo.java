package br.facom.apsoo.sisauto.tela;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.facom.apsoo.sisauto.dao.ClienteDao;
import br.facom.apsoo.sisauto.dao.VeiculoDao;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.Veiculo;

public class TelaAdicionaVeiculo extends JFrame {

	private final JTextField marca;
	private final JTextField modelo;
	private final JTextField cor;
	private final JTextField anoFabricacao;
	private final JTextField anoModelo;
	private final JTextField preco;
	private final Container container;
	private final JButton salvar;
	private final JLabel jTextPane;

	public TelaAdicionaVeiculo() {
		super.setTitle("SisAuto - Adiciona Veículo");
		super.setSize(300, 300);
		super.setVisible(true);

		container = getContentPane();
		container.setLayout(new FlowLayout());

		Panel pMarca = new Panel();
		marca = new JTextField(20);
		pMarca.add(new JLabel("Marca"));
		pMarca.add(marca);
		container.add(pMarca);

		Panel pModelo = new Panel();
		modelo = new JTextField(20);
		pModelo.add(new JLabel("Modelo"));
		pModelo.add(modelo);
		container.add(pModelo);

		Panel pCor = new Panel();
		cor = new JTextField(20);
		pCor.add(new JLabel("Cor"));
		pCor.add(cor);
		container.add(pCor);

		Panel pFabricacao = new Panel();
		anoFabricacao = new JTextField(20);
		pFabricacao.add(new JLabel("Ano Fabricação"));
		pFabricacao.add(anoFabricacao);
		container.add(pFabricacao);

		Panel pAnoModelo = new Panel();
		anoModelo = new JTextField(20);
		pAnoModelo.add(new JLabel("Ano Modelo"));
		pAnoModelo.add(anoModelo);
		container.add(pAnoModelo);

		Panel pPreco = new Panel();
		preco = new JTextField(20);
		pPreco.add(new JLabel("Preço"));
		pPreco.add(preco);
		container.add(pPreco);

		salvar = new JButton("Salvar");

		salvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				salvarInfos();

			}
		});

		container.add(salvar);

		jTextPane = new JLabel();
		container.add(jTextPane);
		listar();

	}

	public void salvarInfos() {

		Veiculo veiculo = new Veiculo();

		veiculo.setMarca(marca.getText());
		veiculo.setModelo(modelo.getText());
		veiculo.setCor(cor.getText());
		veiculo.setAnoFabricacao(Integer.parseInt(anoFabricacao.getText()));
		veiculo.setAnoModelo(Integer.parseInt(anoModelo.getText()));
		veiculo.setPreco(Double.parseDouble(preco.getText()));

		VeiculoDao dao = new VeiculoDao();
		try {
			dao.adicionaVeiculo(veiculo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listar();

	}

	private void listar() {
		VeiculoDao dao = new VeiculoDao();

		try {
			java.util.List<Veiculo> lista = dao.getAll();
			String listagem = "Nenhum Veiculo Cadastrado";
			if (!lista.isEmpty()) {
				listagem = "";
				for (Veiculo veiculo : lista) {

					listagem = listagem + veiculo.getModelo() + " \n";

				}
			}
			jTextPane.setText(listagem);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}