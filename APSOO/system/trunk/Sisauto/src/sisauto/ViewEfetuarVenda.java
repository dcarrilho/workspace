
package sisauto;

import java.awt.*;
import java.awt.event.*;
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
public class ViewEfetuarVenda extends JPanel {
	JButton cancelar, concluir;
	ControlerPrincipal controler;
	JLabel labelClienteNome, labelMarca, labelModelo, labelCor;
	JLabel labelAno, labelvalor, labelFormaPagamento;
	
	
	
	JTextField textClienteNome, textMarca, textModelo, textCor;
	JTextField textvalor;
	JFormattedTextField textAno;
	
	
	JScrollPane scrollpane;
	
	JPanel body;
	GridBagConstraints cons;
	
	
	JRadioButton formaPagamento1, formaPagamento2;
	ButtonGroup grupo;
	
	ViewEfetuarVenda(ControlerPrincipal controlerEnt)
	{
		controler= controlerEnt;
		
		cons = new GridBagConstraints();

		setLayout(new GridLayout(1, 1));
		
		
		
		cancelar= new JButton( "cancelar" );
		concluir= new JButton( "concluir" );
		
		cancelar.addActionListener(new ListenerCancelar());
		concluir.addActionListener(new ListenerConcluir());
		
		
		labelClienteNome= new JLabel( "Cliente" );
		labelMarca= new JLabel( "Marca" );
		labelModelo= new JLabel( "Modelo" );
		labelCor= new JLabel( "Cor" );
		labelAno= new JLabel( "Ano" );
		labelvalor= new JLabel( "Valor" );
		labelFormaPagamento= new JLabel( "Forma de pagamento" );
		
		
		textClienteNome= new JTextField(100);
		textClienteNome.setEditable(false);
		textClienteNome.setText("buscar...");
		textClienteNome.addMouseListener(new ListenerBuscarCliente());
		
		
		textMarca= new JTextField(100);
		textModelo= new JTextField(100);
		textCor= new JTextField(100);
		try {
			textAno= new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException ex) {
			Logger.getLogger(ViewEfetuarVenda.class.getName()).log(Level.SEVERE, null, ex);
		}
		textvalor= new JTextField(100);
		
		formaPagamento1 = new JRadioButton("A vista", false);
		formaPagamento2 = new JRadioButton("financiado", false);
		
		
		
		body= new JPanel();
		body.setLayout(new GridBagLayout());
		
		
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		cons.insets = new Insets(4,4,4,4);
		
		
		addLinha(labelClienteNome,textClienteNome);
		addLinha(labelMarca,textMarca);
		addLinha(labelModelo,textModelo);
		addLinha(labelCor,textCor);
		//addLinha(labelAno,textAno);
		
		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 1;
		cons.weightx = 0;
		body.add(labelAno, cons);
		
		cons.gridx = 2;
		cons.weightx = 1;
		textAno.setMinimumSize(new Dimension(100, 20));
		body.add( textAno, cons );
		
		addLinha(labelvalor,textvalor);
		//addLinha(labelFormaPagamento,textFormaPagamento);

		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 1;
		cons.weightx = 0;
		body.add(labelFormaPagamento, cons);
		
		cons.gridx = 2;
		cons.weightx = 1;
		//text.setMinimumSize(text.getPreferredSize());
		
		grupo = new ButtonGroup();
		grupo.add(formaPagamento1);
		grupo.add(formaPagamento2);
		
		
		JPanel fp= new JPanel();
		fp.setLayout(new FlowLayout(FlowLayout.CENTER));
		fp.add( formaPagamento1 );
		fp.add( formaPagamento2 );
		body.add(fp, cons);
		
		
		
		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 100;
		cons.weightx = 0;
		body.add( concluir, cons );
		
		cons.gridx = 2;
		cons.weightx = 1;
		body.add( cancelar, cons );
		
		add(body);
		
		
		
		
		
		
		
		setVisible(true);
		revalidate();
	}
	
	void init()
	{
		textClienteNome.setText("buscar...");
		textMarca.setText("");
		textModelo.setText("");
		textCor.setText("");
		textAno.setText("");
		textvalor.setText("");
	}
	
	void setCliente(String clienteNome)
	{
		textClienteNome.setText(clienteNome);
	}
	
	
	void addLinha(JLabel label,JTextField text)
	{
		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 1;
		cons.weightx = 0;
		body.add(label, cons);
		
		cons.gridx = 2;
		cons.weightx = 1;
		text.setMinimumSize(text.getPreferredSize());
		body.add( text, cons );
	}
	
	
	
	private class ListenerCancelar implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
			controler.cancelar();
		}
	}
	
	private class ListenerBuscarCliente implements MouseListener  
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			controler.selecionarCliente( 1 );
			//JOptionPane.showMessageDialog( null, "Não implementado", "Não implementado", JOptionPane.INFORMATION_MESSAGE );
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	private class ListenerConcluir implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
			int i=0;
			String dados[][]= new String[10][2];
			
			dados[i][0]= "marca";
			dados[i++][1]= textMarca.getText();
			dados[i][0]= "modelo";
			dados[i++][1]=textModelo.getText();
			dados[i][0]= "cor";
			dados[i++][1]=textCor.getText();
			dados[i][0]="ano";
			dados[i++][1]=textAno.getText();
			dados[i][0]= "valor";
			dados[i++][1]=textvalor.getText();
			dados[i][0]= "formaPagamento";
			if( formaPagamento1.isSelected() )
				dados[i++][1]= "1";
			else if( formaPagamento2.isSelected() )
				dados[i++][1]= "2";
			else
				dados[i++][1]= "0";
			
			
			controler.concluirVenda(dados);
		}
	}
	
	private class ListenerFormaPagamento implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if(formaPagamento1.isSelected())
				JOptionPane.showMessageDialog(null,"msg1!");
			if(formaPagamento2.isSelected())
				JOptionPane.showMessageDialog(null,"msg2!");
		}
		
	}
}
