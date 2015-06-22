package br.facom.apsoo.sisauto.tela;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.facom.apsoo.sisauto.Controller;
import br.facom.apsoo.sisauto.dao.TesteDao;
import br.facom.apsoo.sisauto.dao.VeiculoDao;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.TestDrive;
import br.facom.apsoo.sisauto.model.Veiculo;

public class TelaTestDrive extends JFrame {

	Controller controller;
	TelaTestDrive me;

	private Veiculo veiculo;
	private Cliente cliente;
	private JTextField tCliente;
	private JTextField preco;
	private JTextField hSaida, hChegada, kmSaida, kmChegada;
	private JComboBox marca;
	private JComboBox modelo;
	private JComboBox cor;
	private JComboBox ano;
	private String smarca, smodelo, scor, sano, spreco;
	private JRadioButton vista, prazo;
	private JButton buscar, concluir, iniciar, avalizacao;
	private VeiculoDao dao;
	private GridBagLayout layout;
	private GridBagConstraints constraints;

	public TelaTestDrive(Controller control) {
		super("SisAuto - Test Drive");
		this.controller = control;
		me = this;
		dao = new VeiculoDao();
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(layout);
		constraints.anchor = GridBagConstraints.WEST;

		tCliente = new JTextField(50);
		tCliente.setEditable(false);
		preco = new JTextField(20);
		buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.buscarCliente(getClass());

			}
		});
		marca = new JComboBox();
		marca.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				smarca = (String) marca.getSelectedItem();
				if (!smarca.equalsIgnoreCase("Selecione"))
					marca.setEnabled(false);
				try {
					atualizaModelo(smarca);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		modelo = new JComboBox();
		modelo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				smodelo = (String) modelo.getSelectedItem();
				if (!smodelo.equalsIgnoreCase("Selecione"))
					modelo.setEnabled(false);
				try {
					atualizaCor(smodelo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		cor = new JComboBox();
		cor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				scor = (String) cor.getSelectedItem();
				if (!scor.equalsIgnoreCase("Selecione"))
					cor.setEnabled(false);
				try {
					atualizaAno(scor);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		ano = new JComboBox();
		ano.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sano = (String) ano.getSelectedItem();
				buscarVeiculo();
				if (veiculo != null)
					preco.setText(String.valueOf(veiculo.getPreco()));

			}

		});

		hSaida = new JTextField();
		hChegada = new JTextField();
		kmSaida = new JTextField();
		kmChegada = new JTextField();

		avalizacao = new JButton("Avaliação");
		concluir = new JButton("Confirmar");
		iniciar = new JButton("Iniciar Venda");

		iniciar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cliente != null && veiculo != null) {
					controller.venderVeiculo();
					controller.retornaClienteParaVenda(cliente);
					controller.retornaVeiculo(veiculo);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione veiculo e/ou cliente",
							"Ninguém encontrado",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		avalizacao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JOptionPane pane = new JOptionPane();
				int i = pane.showConfirmDialog(null, "Confirma impressão do formulario de Avaliação?",
						"Atenção", 0);
			}
		});

		concluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					salvar();
				} catch (SQLException e1) {
					System.out.println("Erro não salvar TestDrive");
					e1.printStackTrace();
				}
			}

		});

		// Construção da Tela
		addComp(new JLabel("Nome: "), 0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(tCliente, 0, 1, 1, 4, GridBagConstraints.HORIZONTAL);
		addComp(buscar, 0, 5, 1, 1, GridBagConstraints.HORIZONTAL);

		addComp(marca, 1, 1, 1, 4, GridBagConstraints.HORIZONTAL);
		addComp(new JLabel("Marca: "), 1, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);

		addComp(new JLabel("Modelo: "), 2, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(modelo, 2, 1, 1, 4, GridBagConstraints.HORIZONTAL);
		//
		addComp(new JLabel("Cor"), 3, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(cor, 3, 1, 1, 4, GridBagConstraints.HORIZONTAL);
		//
		addComp(new JLabel("Ano"), 4, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(ano, 4, 1, 1, 4, GridBagConstraints.HORIZONTAL);
		//
		//

		//
		addComp(new JLabel("Horário   saída:"), 5, 0, 1, 2,
				GridBagConstraints.HORIZONTAL);
		addComp(hSaida, 5, 2, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(new JLabel("Chegada:"), 5, 3, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(hChegada, 5, 4, 1, 1, GridBagConstraints.HORIZONTAL);

		addComp(new JLabel("KM  saída:"), 6, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(kmSaida, 6, 1, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(new JLabel("Chegada:"), 6, 3, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(kmChegada, 6, 4, 1, 1, GridBagConstraints.HORIZONTAL);
		//
		addComp(new JLabel("--------"), 7, 3, 1, 2,
				GridBagConstraints.HORIZONTAL);
		//
		//
		addComp(avalizacao, 8, 0, 1, 2, GridBagConstraints.HORIZONTAL);

		addComp(iniciar, 9, 0, 1, 2, GridBagConstraints.HORIZONTAL);
		addComp(concluir, 9, 2, 1, 1, GridBagConstraints.HORIZONTAL);

		atualizaMarca();

		// setSize(500, 450);
		setVisible(true);
		pack();

	}

	private void addComp(Component component, int row, int column, int gh,
			int gw, int fill) {
		constraints.gridx = column; // coluna em que o componente será colocado
		constraints.gridy = row; // linha em que o componente será colocado
		constraints.gridheight = gh; // numero de linhas que o componente ocupa
		constraints.gridwidth = gw; // numero de colunas que o componente ocupa
		constraints.fill = fill; // redireciona o componente na direção
									// especificada
		layout.setConstraints(component, constraints);
		add(component);
	}

	@SuppressWarnings("unchecked")
	private void atualizaMarca() {
		List<String> lista = dao.getAllMarca();
		marca.addItem("Selecione");
		for (String string : lista)
			marca.addItem(string);
	}

	@SuppressWarnings("unchecked")
	private void atualizaModelo(String smarca) throws SQLException {

		if (!smarca.equalsIgnoreCase("Selecione")
				&& !smarca.equalsIgnoreCase("")) {
			modelo.removeAllItems();
			List<String> lista = dao.getModelo(smarca);
			modelo.addItem("Selecione");
			for (String string : lista)
				modelo.addItem(string);

		} else {
			modelo.removeAllItems();
		}
	}

	@SuppressWarnings("unchecked")
	private void atualizaCor(String smodelo) throws SQLException {
		if (smodelo != null && !smodelo.equalsIgnoreCase("")
				&& !smodelo.equalsIgnoreCase("Selecione")) {
			cor.removeAllItems();
			List<String> lista = dao.getCor(smodelo);
			cor.addItem("Selecione");
			for (String string : lista)
				cor.addItem(string);

		} else {
			cor.removeAllItems();
		}
	}

	@SuppressWarnings("unchecked")
	private void atualizaAno(String scor) throws SQLException {

		if (scor != null && !scor.equalsIgnoreCase("")
				&& !scor.equalsIgnoreCase("Selecione")) {
			ano.removeAllItems();
			List<String> lista = dao.getAno(scor);
			ano.addItem("Selecione");
			for (String string : lista)
				ano.addItem(string);

		} else {
			ano.removeAllItems();
		}
	}

	public void setCliente(String nome) {
		tCliente.setText(nome);
	}

	private void buscarVeiculo() {

		try {
			veiculo = dao.getVeiculo(smarca, smodelo, scor, sano);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		tCliente.setText(cliente.getNome());
	}

	protected void salvar() throws SQLException {
		if(veiculo!=null && cliente != null){
		TesteDao dao = new TesteDao();
		TestDrive testDrive = new TestDrive();
		testDrive.setCliente(cliente.getCadastro());
		testDrive.setVeiculo(String.valueOf(veiculo.getId()));
		testDrive.setHsaida(hSaida.getText());
		testDrive.setHchegada(hChegada.getText());
		testDrive.setKmsaida(kmSaida.getText());
		testDrive.setKmchegada(kmChegada.getText());
		
		dao.adicionaTeste(testDrive);
		}else
		{
			JOptionPane.showMessageDialog(null,
					"Selecione veiculo e/ou cliente",
					"Ninguém encontrado",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

}
