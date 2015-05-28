package br.facom.apsoo.sisauto.tela;

import java.awt.MenuItem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaInicial extends JFrame{
	
	JMenu menu = new JMenu("Adicionar");
	JMenuItem menuItem1 = new JMenuItem("Add Cliente");
	JMenuItem menuItem2 = new JMenuItem("Add Veiculo");
	JMenuBar menuBar = new JMenuBar();
	
	JButton venda = new JButton("Venda");
	JButton td = new JButton("TestDrive");
	JPanel panel = new JPanel();
	
	public TelaInicial(){
		super.setSize(300, 300);
		super.setVisible(true);
		panel.add(venda);
		panel.add(td);
		menu.add(menuItem1);
		menu.add(menuItem2);
		menuBar.add(menu);
		panel.add(menuBar);
		add(panel);
	
	}
	
}
