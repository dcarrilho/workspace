package br.facom.apsoo.sisauto.tela;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.facom.apsoo.sisauto.dao.ClienteDao;
import br.facom.apsoo.sisauto.model.Cliente;

public class TelaAdicionaCliente extends JFrame {
	
	private final JLabel lnome;
	private final JTextField nome;
	private final JLabel lcpf;
	private final JTextField cpf;
	private final JLabel lendereco;
	private final JTextField endereco;
	private final JLabel lcidade;
	private final JTextField cidade;
	private final JLabel lestado;
	private final JTextField estado;
	private Container container;
	private final JButton salvar;
	
	public TelaAdicionaCliente(){
		super.setTitle("SisAuto - Adiciona Cliente");
		super.setSize(300, 300);
		super.setVisible(true);
		lnome = new JLabel("Nome");
		lcpf = new JLabel("CPF/CNPJ");
		lendereco = new JLabel("Endereco");
		lcidade = new JLabel("Cidade");
		lestado = new JLabel("Estado");
		nome = new JTextField(15);
		cpf = new JTextField(11);
		endereco = new JTextField(15);
		cidade = new JTextField(10);
		estado = new JTextField(2);
		salvar = new JButton("Salvar");
		
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarInfos();
				
			}
		});
		

		container = getContentPane();
		container.setLayout(new FlowLayout());
		
		Panel pnome = new Panel();
		pnome.add(lnome);
		pnome.add(nome);
		container.add(pnome);
		
		Panel pcpf = new Panel();
		pcpf.add(lcpf);
		pcpf.add(cpf);
		container.add(pcpf);
		
		Panel pend = new Panel();
		pend.add(lendereco);
		pend.add(endereco);
		container.add(pend);

		Panel pcid = new Panel();
		pcid.add(lcidade);
		pcid.add(cidade);
		container.add(pcid);
		

		Panel puf = new Panel();
		puf.add(lestado);
		puf.add(estado);
		container.add(puf);
		
		container.add(salvar);
	}
	
	public void salvarInfos(){
		
		
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
