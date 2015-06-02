package br.facom.apsoo.sisauto.tela;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaAdicionaVeiculo extends JFrame {
	
	private final JTextField marca;
	private final JTextField modelo;
//	private final JTextField cor;
//	private final JTextField anoFabricacao;
//	private final JTextField anoModelo;
//	private final JTextField preco;
	private final Container container;
	public TelaAdicionaVeiculo(){
		super.setTitle("SisAuto - Adiciona Veículo");
		super.setSize(300, 300);
		super.setVisible(true);
		
		container = getContentPane();
		container.setLayout(new FlowLayout());
		
		Panel pMarca = new Panel();
		marca = new JTextField(20);
		pMarca.add(new JLabel("Marca"));
		pMarca.add(marca);
		container.add(pMarca, BorderLayout.NORTH);
		
		Panel pModelo = new Panel();
		modelo = new JTextField(20);
		pModelo.add(new JLabel("Modelo"));
		pModelo.add(modelo);
		container.add(pModelo, BorderLayout.NORTH);
		
		
		
	}

}
