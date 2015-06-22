package br.facom.apsoo.sisauto.tela;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;

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

import br.facom.apsoo.sisauto.Controller;
import br.facom.apsoo.sisauto.dao.ClienteDao;
import br.facom.apsoo.sisauto.model.Cliente;

public class TelaBuscaCliente extends JFrame {

	Class pai;
	Controller controller;
	JFrame me;
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

	public TelaBuscaCliente(Class	cl, Controller control) {
		this.pai = cl;
		this.controller = control;
		me=this;
		construtorTela();

	}

	private void construtorTela() {

		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(layout);
		MaskFormatter mascaraCpf;
		textClienteNome = new JTextField(100);
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
			textClienteCpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		scrollpane = new JScrollPane();

		buscarCliente = new JButton("Buscar");
		buscarCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					cliente = buscar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cancelar = new JButton("cancelar");

		labelClienteNome = new JLabel("Nome:");
		labelClienteCpf = new JLabel("CPF:");

		addComp(labelClienteNome, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL);

		addComp(labelClienteCpf, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL);

		addComp(buscarCliente, 2, 2, 1, 1, GridBagConstraints.HORIZONTAL);

		constraints.weightx = 7;
		addComp(textClienteNome, 0, 1, 1, 3, GridBagConstraints.HORIZONTAL);
		addComp(textClienteCpf, 1, 1, 1, 3, GridBagConstraints.HORIZONTAL);

		add(scrollpane);

		JPanel resultado = new JPanel();
		resultado.setLayout(new FlowLayout(FlowLayout.CENTER));

		addComp(resultado, 3, 0, 1, 3, GridBagConstraints.HORIZONTAL);

		setVisible(true);
		setSize(250, 400);

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

	public Cliente buscar() throws SQLException {

		ClienteDao dao = new ClienteDao();
		String[][] clientes;
		if (!textClienteNome.getText().isEmpty()) {
			clientes = dao.getWithName(textClienteNome.getText());
		} else {
			clientes = dao.getWithCpf(textClienteCpf.getText());
		}

		if (clientes != null) {

			tabela = new JTable(clientes, colunas);
			tabela.addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					int row = tabela.rowAtPoint(e.getPoint());
					if (row >= 0) {
						cliente = new Cliente();
						cliente.setNome(""+tabela.getValueAt(row, 0));
						cliente.setCadastro(""+tabela.getValueAt(row, 1));
						System.out.println(pai.getName());
						controller.retornaCliente(pai, cliente);
						dispose();
						
					}
				}

				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});

			scrollpane.getViewport().setView(tabela);
			addComp(tabela, 4, 0, 10, 2, GridBagConstraints.HORIZONTAL);

		} else {
			JOptionPane.showMessageDialog(null, "Ninguém encontrado",
					"Ninguém encontrado", JOptionPane.INFORMATION_MESSAGE);
		}

		revalidate();
		repaint();
		return this.cliente;
	}
}
