package br.facom.apsoo.sisauto.tela;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.facom.apsoo.sisauto.dao.VeiculoDao;

public class TelaVenda extends JFrame {

	private JTextField cliente;
	private Button buscarCliente;
	private JComboBox marca;
	private JComboBox modelo;
	private JComboBox cor;
	private String smarca, smodelo, scor, sano, spreco;
	private JPanel painelCliente, painelVeiculo, painelConclusao;
	private JRadioButton vista, prazo;
	private JButton buscar, finalizar;
	VeiculoDao dao;

	public TelaVenda() throws SQLException {
		super("Vendas - SisAuto");
		
		dao = new VeiculoDao();

		setLayout(new BorderLayout(5, 5));
		painelCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		this.cliente = new JTextField(20);

		painelCliente.add(cliente);
		this.buscar = new JButton("Buscar Cliente");
		painelCliente.add(buscar);
		add(painelCliente, BorderLayout.NORTH);
		
		this.marca = new JComboBox();
		 this.marca.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				smarca = (String) marca.getSelectedItem();
				try {
					atualizaModelo(smarca);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		this.modelo = new JComboBox();
		this.modelo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				smodelo = (String) modelo.getSelectedItem();
				try {
					atualizaCor(smodelo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		this.cor = new JComboBox();
		painelVeiculo = new JPanel(new GridLayout(5, 1, 5, 5));

		painelVeiculo.add(marca);
		painelVeiculo.add(modelo);
		painelVeiculo.add(cor);
		painelVeiculo.add(new JComboBox());
		painelVeiculo.add(new JTextField());
		
		
		add(painelVeiculo, BorderLayout.CENTER);
		
		painelConclusao = new JPanel();
		
		this.vista = new JRadioButton("A Vista");
		this.prazo = new JRadioButton("Financiado");
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(vista);
		group.add(prazo);
		
		painelConclusao.add(vista);
		painelConclusao.add(prazo);
		this.finalizar = new JButton("Finalizar");
		painelConclusao.add(finalizar);
		
		add(painelConclusao, BorderLayout.SOUTH);
		
		atualizaMarca();

		setSize(400, 300);
		
		setVisible(true);

	}

	// constroeLayout();
	// atualizaMarca();
	//
	// }
	//
	private void atualizaMarca() {

		marca.addItem("Marca");
		List<String> lista = dao.getAllMarca();
		for (String string : lista) {
			marca.addItem(string);

		}

	}
	
	
	//
	// private void constroeLayout() {
	// setLayout(new GridLayout(8, 1, 5, 5));
	//
	// dao = new VeiculoDao();
	// layout = new FlowLayout();
	//
	// cliente = new JLabel("Cliente:");
	// buscarCliente = new Button("buscar");
	// JPanel jPanelCliente = new JPanel(layout);
	// jPanelCliente.add(cliente);
	// jPanelCliente.add(buscarCliente);
	// add(jPanelCliente);
	//
	// marca = new JComboBox();
	// JPanel panelMarca = new JPanel(layout);
	//
	//
	// panelMarca.add(marca);
	// add(panelMarca);
	//
	// modelo = new JComboBox();
	// // JPanel panelModelo = new JPanel(layout);
	// // panelModelo.add(new JLabel("Modelo"));
	// // panelModelo.add(modelo);
	// // add(panelModelo);
	// add(modelo);
	//
	//
	// }
	//
	private void atualizaModelo(String smarca) throws SQLException {
		if (!smarca.equalsIgnoreCase("marca")) {
			List<String> lista;
			painelVeiculo.remove(modelo);
			this.modelo = new JComboBox();
			painelVeiculo.add(modelo);
			lista = dao.getModelo(smarca);
			for (String string : lista) {
				this.modelo.addItem(string);

			}
		}
	}
	
	private void atualizaCor(String smodelo) throws SQLException{
			List<String> lista;
			painelVeiculo.remove(cor);
			this.cor = new JComboBox();
			painelVeiculo.add(cor);
			lista = dao.getCor(smodelo);
			for (String string : lista) {
				this.modelo.addItem(string);

			}
		
	}

}
