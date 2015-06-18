package br.facom.apsoo.sisauto.tela;

import java.awt.Button;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.facom.apsoo.sisauto.dao.VeiculoDao;

public class TelaVenda extends JFrame {

	private JTextField cliente;
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

	public TelaVenda() {
		dao = new VeiculoDao();
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(layout);
		constraints.anchor = GridBagConstraints.WEST;

		cliente = new JTextField(50);
		preco = new JTextField(20);
		buscar = new JButton("Buscar");
		marca = new JComboBox();
		marca.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				smarca = (String) marca.getSelectedItem();
				try {
					atualizaModelo(smarca);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		modelo = new JComboBox();
		modelo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				smodelo = (String) modelo.getSelectedItem();
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
			}
		});

		addComp(new JLabel("Nome: "), 0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
		addComp(cliente, 0, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		addComp(buscar, 0, 5, 1, 1, GridBagConstraints.HORIZONTAL);

		addComp(marca, 1, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		addComp(new JLabel("Marca: "), 1, 0, 1, 1, 
				GridBagConstraints.HORIZONTAL);

		addComp(new JLabel("Modelo: "), 2, 0, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addComp(modelo, 2, 1, 1, 3, GridBagConstraints.HORIZONTAL);
//		
		 addComp(new JLabel("Cor"), 3, 0, 1, 1,
		 GridBagConstraints.HORIZONTAL);
		 addComp(cor, 3, 1, 1, 3, GridBagConstraints.HORIZONTAL);
//		
		 addComp(new JLabel("Ano"), 4, 0, 1, 1,
		 GridBagConstraints.HORIZONTAL);
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
		 cancelar = new JButton("Cancelar");
		 finalizar = new JButton("Confirmar");
//		 
//
		 addComp(cancelar, 9, 0, 1, 1,  GridBagConstraints.HORIZONTAL);
		 addComp(finalizar, 9, 2, 1, 1, GridBagConstraints.HORIZONTAL);
		 
		atualizaMarca();

		setSize(400, 500);
		setVisible(true);

	}

	private void addComp(Component component, int row, int column, int gh,
			int gw, int fill) {
		constraints.gridx = column; //coluna em que o componente será colocado
		constraints.gridy = row; //linha em que o componente será colocado
		constraints.gridheight = gh; //numero de linhas que o componente ocupa
		constraints.gridwidth = gw; //numero de colunas que o componente ocupa
		constraints.fill = fill; //redireciona o componente na direção especificada
		layout.setConstraints(component, constraints);
		add(component);
	}

	private void atualizaMarca() {
		List<String> lista = dao.getAllMarca();
		marca.addItem("Selecione");
		for (String string : lista)
			marca.addItem(string);
	}

	private void atualizaModelo(String smarca) throws SQLException {
		
		if (!smarca.equalsIgnoreCase("Selecione")) {
			modelo.removeAllItems();
			List<String> lista = dao.getModelo(smarca);
			modelo.addItem("Selecione");
			for (String string : lista)
				modelo.addItem(string);
			
		}else{
			modelo.removeAllItems();
		}
	}
	
	private void atualizaCor(String smodelo) throws SQLException {
		if (!smodelo.equalsIgnoreCase("")&&!smodelo.equalsIgnoreCase("Selecione")) {
			cor.removeAllItems();
			List<String> lista = dao.getCor(smodelo);
			cor.addItem("Selecione");
			for (String string : lista)
				cor.addItem(string);
			
		}else{
			cor.removeAllItems();
		}
	}
	
	private void atualizaAno(String scor) throws SQLException {
		if (!scor.equalsIgnoreCase("")&&!scor.equalsIgnoreCase("Selecione")) {
			ano.removeAllItems();
			List<String> lista = dao.getAno(scor);
			ano.addItem("Selecione");
			for (String string : lista)
				ano.addItem(string);
			
		}else{
			ano.removeAllItems();
		}
	}

}
