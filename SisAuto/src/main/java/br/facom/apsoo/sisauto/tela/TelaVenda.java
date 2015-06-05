package br.facom.apsoo.sisauto.tela;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaVenda extends JFrame{

	private final JTextField cliente;
	
	private final JPanel clienteJPanel;
	private final JPanel veiculoJPanel;
	
	public TelaVenda(){
		super("Vendas SisAuto");
		
		clienteJPanel =new JPanel();
		clienteJPanel.setLayout(new BorderLayout());
		clienteJPanel.add(new JLabel("Cliente"), BorderLayout.WEST);
		cliente = new JTextField();
		clienteJPanel.add(cliente, BorderLayout.CENTER);
		clienteJPanel.add(new Button("buscar"), BorderLayout.EAST);
		clienteJPanel.setSize(getMinimumSize());
		add(clienteJPanel);
		
		veiculoJPanel =new JPanel();
		veiculoJPanel.setLayout(new BorderLayout());
		veiculoJPanel.add(new JLabel("Modelo"), BorderLayout.WEST);
		veiculoJPanel.add(cliente, BorderLayout.CENTER);
		veiculoJPanel.setSize(getMinimumSize());
		add(veiculoJPanel);
		
		
	
		
		super.setSize(300, 300);
		super.setVisible(true);
	}

}
