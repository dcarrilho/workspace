package br.facom.apsoo.sisauto.tela;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.facom.apsoo.sisauto.Controller;
import br.facom.apsoo.sisauto.dao.VeiculoDao;
import br.facom.apsoo.sisauto.dao.VendaDao;
import br.facom.apsoo.sisauto.model.Cliente;
import br.facom.apsoo.sisauto.model.Veiculo;
import br.facom.apsoo.sisauto.model.Venda;

public class TelaVenda extends JFrame{

	Controller controller;

	private Veiculo veiculo;
	private Cliente cliente;
	private JTextField tCliente;
	private JTextField preco;
	private JComboBox marca;
	private JComboBox modelo;
	private JComboBox cor;
	private JComboBox ano;
	private String smarca, smodelo, scor, sano, spreco;
	private JRadioButton vista, prazo;
	private JButton buscar, finalizar, cancelar;
	private VeiculoDao dao;
	private GridBagLayout layout;
	private GridBagConstraints constraints;

	public TelaVenda(Controller control) {
		super("SisAuto - Venda");
		this.controller = control;
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

		addComp(new JLabel("Nome: "), 0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(tCliente, 0, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		addComp(buscar, 0, 5, 1, 1, GridBagConstraints.HORIZONTAL);

		addComp(marca, 1, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		addComp(new JLabel("Marca: "), 1, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);

		addComp(new JLabel("Modelo: "), 2, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(modelo, 2, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		//
		addComp(new JLabel("Cor"), 3, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(cor, 3, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		//
		addComp(new JLabel("Ano"), 4, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(ano, 4, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		//
		addComp(new JLabel("Preço: "), 5, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(preco, 5, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		//
		ButtonGroup group = new ButtonGroup();
		vista = new JRadioButton("A Vista");

		prazo = new JRadioButton("Financiado");
		group.add(vista);
		group.add(prazo);
		//
		addComp(vista, 6, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(prazo, 6, 1, 1, 1, GridBagConstraints.HORIZONTAL);
		//
		cancelar = new JButton("Nova Pesquisa");
		finalizar = new JButton("Confirmar");

		cancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.venderVeiculo();
				dispose();
			}
		});

		finalizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					finalizarVenda();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//
		//
		addComp(cancelar, 9, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(finalizar, 9, 2, 1, 1, GridBagConstraints.HORIZONTAL);

		atualizaMarca();

//		setSize(400, 350);
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

	private void finalizarVenda() throws SQLException {
		if (veiculo != null && cliente != null) {
			if (!vista.isSelected() && !prazo.isSelected()) {
				prazo.setBackground(Color.red);
				vista.setBackground(Color.red);
			} else {
				Venda venda = new Venda();
				venda.setVeiculo(veiculo.getId());
				venda.setCliente(cliente.getCadastro());
				if (prazo.isSelected())
					venda.setFinanciado(true);
				else
					venda.setFinanciado(false);
				VendaDao vendaDao = new VendaDao();
				vendaDao.adicionaVenda(venda);
				dao.setVenda(veiculo.getId());
				controller.fecharTela(this);
			}
		} else
			JOptionPane.showMessageDialog(null,
					"Selecione veiculo e/ou cliente", "Ninguém encontrado",
					JOptionPane.INFORMATION_MESSAGE);
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

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
		marca.addItem(veiculo.getMarca().toString());
		modelo.addItem(veiculo.getModelo().toString());
		cor.addItem(veiculo.getCor().toString());
		ano.addItem(String.valueOf(veiculo.getAnoFabricacao()));
		preco.setText(String.valueOf(veiculo.getPreco()));

	}

}
