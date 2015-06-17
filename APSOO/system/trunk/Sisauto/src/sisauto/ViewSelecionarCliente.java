package sisauto;

import java.awt.*;
import java.awt.event.*;
import java.beans.Visibility;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import static javax.swing.BoxLayout.LINE_AXIS;
import static javax.swing.BoxLayout.PAGE_AXIS;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author fcm3
 */
public class ViewSelecionarCliente extends JFrame {
	JButton buscarCliente, cancelar;
	ControlerPrincipal controler;
	JLabel labelClienteNome, labelClienteCpf;
	JTextField textClienteNome;
	JFormattedTextField textClienteCpf;
	
	
	JScrollPane scrollpane;
	//String [] colunas = {"Nome", "CPF", "ID"};
	String [] colunas = {"Nome", "CPF"};
	ViewSelecionarCliente that;
	JTable tabela;
	
	int idRequest;
	
	ViewSelecionarCliente(ControlerPrincipal controlerEnt)
	{
		super("Selecionar cliente");
		that= this;
		idRequest= 0;
		controler= controlerEnt;
		GridBagConstraints cons = new GridBagConstraints();
		
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
			textClienteCpf = new JFormattedTextField(mascaraCpf);
			
		} catch (ParseException ex) {
			Logger.getLogger(ViewSelecionarCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(3, 1));
		
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setLayout(new GridBagLayout());
		//setLayout(null);
		//setLayout(new BorderLayout(2,1));
		
		//scrollpane = new JScrollPane( new JTable( this.screenControler.getSalaTableModel() ) );
		//scrollpane.setBounds(5, 95, 680, 390);
		
		
		
		scrollpane= new JScrollPane();
		
		
		
		buscarCliente= new JButton( "Buscar" );
		cancelar= new JButton( "cancelar" );
		
		labelClienteNome= new JLabel( "Nome:" );
		labelClienteCpf= new JLabel( "CPF:" );
		
		textClienteNome=  new JTextField(100);
		
		
		buscarCliente.addActionListener(new ListenerBuscarCliente());
		cancelar.addActionListener(new ListenerCancelar());
		
		JPanel busca= new JPanel();
		busca.setLayout(new GridBagLayout());
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		cons.insets = new Insets(4,4,4,4);
		
		cons.weighty = 1;
        cons.gridx = 1;
		cons.gridy = 1;
		cons.weightx = 0;
		busca.add(labelClienteNome, cons);
		
		cons.gridx = 2;
		cons.weightx = 1;
		textClienteNome.setMinimumSize(textClienteNome.getPreferredSize());
		busca.add( textClienteNome, cons );
		
		// ------------
		
		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 1;
		cons.weightx = 0;
		busca.add(labelClienteCpf, cons);
		
		
		
		cons.gridx = 2;
		cons.weightx = 1;
		textClienteCpf.setMinimumSize(textClienteCpf.getPreferredSize());
		busca.add( textClienteCpf, cons );
		
		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 100;
		cons.weightx = 0;
		busca.add( buscarCliente, cons );
		cons.gridx = 2;
		cons.weightx = 1;
		busca.add( cancelar, cons );
		
		add(busca);
		
		add(scrollpane);
		
		JPanel resultado= new JPanel();
		resultado.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		
		add(resultado);
		this.setLocation( Config.viewBuscarClienteDisplayX, Config.viewBuscarClienteDisplayY);
		this.setSize( Config.viewBuscarClienteDisplayW, Config.viewBuscarClienteDisplayH );
		setVisible(false);
		revalidate();
		repaint();
	}
	
	
	private class ListenerBuscarCliente implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
			String dados[][];
			dados= controler.buscarCliente(textClienteNome.getText(), textClienteCpf.getText() );

			if( dados!=null )
			{

				tabela = new JTable(dados, colunas);
				tabela.addMouseListener( new ListenerClickTabela() );
				
				scrollpane.getViewport().setView(tabela);
				that.revalidate();
				that.repaint();
				
			}
			else
			{
				JOptionPane.showMessageDialog( null, "Ninguém encontrado", "Ninguém encontrado", JOptionPane.INFORMATION_MESSAGE );
			}
		}
	}
	
	private class ListenerClickTabela extends MouseAdapter 
	{
		@Override
		public void mouseClicked(MouseEvent evt) 
		{
			int row = tabela.rowAtPoint(evt.getPoint());
			if (row >= 0) {
				int id=-1;
				id= Integer.parseInt((String)tabela.getModel().getValueAt(row, 2));
				System.out.println("ID: "+id);
				controler.selecionarClienteResponse(idRequest, id);
				setVisible(false);
			}
		}
	}
	
	private class ListenerCancelar implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
				controler.cancelar();
		}
	}
}
