package br.facom.apsoo.sisauto.tela;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class TelaBuscaCliente extends JFrame {
	JButton buscarCliente, cancelar;
	JLabel labelClienteNome, labelClienteCpf;
	JTextField textClienteNome;
	JFormattedTextField textClienteCpf;

	JScrollPane scrollpane;
	String[] colunas = { "Nome", "CPF" };
	JTable tabela;

	int idRequest;
	
	public TelaBuscaCliente() {
		
		MaskFormatter mascaraCpf;
		
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
			textClienteCpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scrollpane = new JScrollPane();

		buscarCliente = new JButton("Buscar");
		cancelar = new JButton("cancelar");

		labelClienteNome = new JLabel("Nome:");
		labelClienteCpf = new JLabel("CPF:");

		textClienteNome = new JTextField(100);
		
		JPanel busca = new JPanel();
		busca.setLayout(new GridBagLayout());
		

		busca.add(labelClienteNome);
		busca.add(textClienteNome);
		busca.add(labelClienteCpf);
		busca.add(textClienteCpf);
		busca.add(buscarCliente);
		busca.add(cancelar);
		
		add(busca);

		add(scrollpane);

		JPanel resultado = new JPanel();
		resultado.setLayout(new FlowLayout(FlowLayout.CENTER));

		add(resultado);
		
		setVisible(true);
		setSize(200, 200);
		revalidate();
		repaint();
		
	}

}
