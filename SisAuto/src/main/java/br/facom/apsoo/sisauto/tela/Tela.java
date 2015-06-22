package br.facom.apsoo.sisauto.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Tela extends JFrame {

	JMenu menu = new JMenu("Adicionar");
	JMenuItem menuItem1 = new JMenuItem("Add Cliente");
	JMenuItem menuItem2 = new JMenuItem("Add Veiculo");
	JMenuBar menuBar = new JMenuBar();

	public Tela() {
		menu.add(menuItem1);
		menu.add(menuItem2);
		menuBar.add(menu);
		super.setJMenuBar(menuBar);

		menuItem1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new TelaAdicionaCliente();
			}
		});

		menuItem2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new TelaAdicionaVeiculo();

			}
		});
		
		
		setVisible(true);
		setSize(300, 300);
	}

	public void troca(JPanel panel) {
		removeAll();
		add(panel);
	}

}
