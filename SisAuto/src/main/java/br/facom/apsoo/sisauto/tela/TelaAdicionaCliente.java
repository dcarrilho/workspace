package br.facom.apsoo.sisauto.tela;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.MaskFormatter;

import br.facom.apsoo.sisauto.dao.ClienteDao;
import br.facom.apsoo.sisauto.model.Cliente;

public class TelaAdicionaCliente extends JFrame {

	private final JLabel lnome;
	private final JTextField nome;
	private final JLabel lcpf;
	private JTextField cpf;
	private final JLabel lendereco;
	private final JTextField endereco;
	private final JLabel lcidade;
	private final JTextField cidade;
	private final JLabel lestado;
	private final JTextField estado;
	private Container container;
	private final JButton salvar;
	private final JLabel jTextPane;

	// paineis
	// private final JPanel clienteJPanel;
	// private final JPanel endJPanel;

	public TelaAdicionaCliente() {
		super.setTitle("SisAuto - Adiciona Cliente");
		MaskFormatter mascaraCpf;
		cpf = new JTextField(14);
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
			cpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lnome = new JLabel("Nome");
		lcpf = new JLabel("CPF/CNPJ");
		lendereco = new JLabel("Endereco");
		lcidade = new JLabel("Cidade");
		lestado = new JLabel("Estado");
		nome = new JTextField(15);
		endereco = new JTextField(15);
		cidade = new JTextField(10);
		estado = new JTextField(2);
		salvar = new JButton("Salvar");
		salvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				salvarInfos();

			}
		});

		GridLayout layout = new GridLayout(6, 2);
		setLayout(layout);

		Panel pnome = new Panel();
		pnome.add(lnome);
		pnome.add(nome);
		add(pnome);

		Panel pcpf = new Panel();
		pcpf.add(lcpf);
		pcpf.add(cpf);
		add(pcpf);

		Panel pend = new Panel();
		pend.add(lendereco);
		pend.add(endereco);
		add(pend);

		Panel pcid = new Panel();
		pcid.add(lcidade);
		pcid.add(cidade);
		add(pcid);

		Panel puf = new Panel();
		puf.add(lestado);
		puf.add(estado);
		add(puf);

		add(salvar);

		jTextPane = new JLabel();

		super.setSize(300, 300);
		super.setVisible(true);
	}

	public void salvarInfos() {

		Cliente cliente = new Cliente();

		cliente.setNome(nome.getText());
		cliente.setCadastro(cpf.getText());
		cliente.setEndereco(endereco.getText());
		cliente.setCidade(cidade.getText());
		cliente.setEstado(estado.getText());
		cliente.setDataCadastro(Calendar.getInstance());

		ClienteDao dao = new ClienteDao();
		try {
			dao.adicionaCliente(cliente);
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Cliente Não pode ser Salvo!");
		}

	}

}
