package br.facom.apsoo.sisauto.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.facom.apsoo.sisauto.Controller;

public class TelaInicial extends JFrame{
	
	Controller controller;
	
	JMenu menu = new JMenu("Adicionar");
	JMenuItem menuItem1 = new JMenuItem("Add Cliente");
	JMenuItem menuItem2 = new JMenuItem("Add Veiculo");
	JMenuBar menuBar = new JMenuBar();
	
	JButton venda = new JButton("Venda");
	JButton td = new JButton("TestDrive");
	JPanel panel = new JPanel();
	
	public TelaInicial(Controller control){
		
		this.controller = control;
		
		super.setTitle("SisAuto");
		super.setSize(300, 300);
		super.setVisible(true);
		panel.add(venda);
		panel.add(td);
		menu.add(menuItem1);
		menu.add(menuItem2);
		menuBar.add(menu);
		super.setJMenuBar(menuBar);
//		panel.add(menuBar);
		add(panel);
		
		venda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
					controller.venderVeiculo();			
				
			}
		});
		
		td.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.TestarVeiculo();
				
			}
		});
		
		menuItem1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.cadastrarCliente();
			}
		});
		
		menuItem2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.cadastrarVeiculo();
				
			}
		});
	
	}
	
	
}
