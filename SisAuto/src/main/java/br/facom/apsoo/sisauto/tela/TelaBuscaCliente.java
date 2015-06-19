package br.facom.apsoo.sisauto.tela;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.facom.apsoo.sisauto.model.Cliente;

public class TelaBuscaCliente extends JFrame {
	JButton buscarCliente, cancelar;
	JLabel labelClienteNome, labelClienteCpf;
	JTextField textClienteNome;
	JFormattedTextField textClienteCpf;

	JScrollPane scrollpane;
	String[] colunas = { "Nome", "CPF" };
	JTable tabela;
	
	private GridBagConstraints constraints;
	private GridBagLayout layout;

	int idRequest;
	
	private Cliente cliente;
	
	private void construtorTela() {
		
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(layout);
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
				

		addComp(labelClienteNome, 0, 0 , 1, 1, GridBagConstraints.HORIZONTAL);
		
		addComp(labelClienteCpf, 1, 0 , 1, 1, GridBagConstraints.HORIZONTAL);
		
		addComp(buscarCliente, 2, 2 , 1, 1, GridBagConstraints.HORIZONTAL);
		
		constraints.weightx = 7;
		addComp(textClienteNome, 0, 1 , 1, 3, GridBagConstraints.HORIZONTAL);
		addComp(textClienteCpf, 1, 1 , 1, 3, GridBagConstraints.HORIZONTAL);
	

		add(scrollpane);

		JPanel resultado = new JPanel();
		resultado.setLayout(new FlowLayout(FlowLayout.CENTER));


		addComp(resultado, 3, 0 , 1, 3, GridBagConstraints.HORIZONTAL);
		
		setVisible(true);
		setSize(250, 200);
		revalidate();
		repaint();
		
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
	
	public Cliente buscar(){
		
		construtorTela();
		
		return this.cliente;
	}

}
