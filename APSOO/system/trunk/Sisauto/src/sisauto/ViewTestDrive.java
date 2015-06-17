
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
public class ViewTestDrive extends JPanel {
	JButton cancelar, concluir, avaliacao;
	ControlerPrincipal controler;
	JLabel labelClienteNome, labelMarca, labelModelo, labelCor;
	JLabel labelAno;
	
	JLabel labelHora, labelHoraSaida, labelHoraChegada;
	JLabel labelKm, labelKmSaida, labelKmChegada;
	
	
	
	
	JTextField textClienteNome, textMarca, textModelo, textCor, textKmSaida, textKmChegada;
	JFormattedTextField textAno;
	JFormattedTextField textHoraSaida, textHoraChegada;
	
	JScrollPane scrollpane;
	
	JPanel body;
	GridBagConstraints cons;
	
	
	ViewTestDrive(ControlerPrincipal controlerEnt)
	{
		controler= controlerEnt;
		
		cons = new GridBagConstraints();

		setLayout(new GridLayout(1, 1));
		
		
		
		cancelar= new JButton( "cancelar" );
		concluir= new JButton( "concluir" );
		avaliacao= new JButton("avaliação");
		
		cancelar.addActionListener(new ListenerCancelar());
		concluir.addActionListener(new ListenerConcluir());
		
		
		labelClienteNome= new JLabel( "Cliente" );
		labelMarca= new JLabel( "Marca" );
		labelModelo= new JLabel( "Modelo" );
		labelCor= new JLabel( "Cor" );
		labelAno= new JLabel( "Ano" );
		
		labelHora= new JLabel( "hora" );
		labelHoraSaida= new JLabel( "hora saída" );
		labelHoraChegada= new JLabel( "hora chegada" );
		labelKm= new JLabel( "Km" );
		labelKmSaida= new JLabel( " Kmsaída" );
		labelKmChegada= new JLabel( "Km chagada" );
		
		
		
		textClienteNome= new JTextField(100);
		textClienteNome.setEditable(false);
		textClienteNome.setText("buscar...");
		textClienteNome.addMouseListener(new ListenerBuscarCliente());
		
		textMarca= new JTextField(100);
		textModelo= new JTextField(100);
		textCor= new JTextField(100);
		
		textKmSaida= new JTextField(100);
		textKmChegada= new JTextField(100);
		
		try {
			textAno= new JFormattedTextField(new MaskFormatter("####"));
			
			textHoraSaida= new JFormattedTextField(new MaskFormatter("##:##"));
			textHoraChegada= new JFormattedTextField(new MaskFormatter("##:##"));
			
		} catch (ParseException ex) {
			Logger.getLogger(ViewEfetuarVenda.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
		body= new JPanel();
		body.setLayout(new GridBagLayout());
		
		
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		cons.insets = new Insets(4,4,4,4);
		
		
		addLinha(labelClienteNome,textClienteNome);
		addLinha(labelMarca,textMarca);
		addLinha(labelModelo,textModelo);
		addLinha(labelCor,textCor);
		
		textAno.setMinimumSize(new Dimension(50, 20));
		addLinha(labelAno,textAno, 1);
		
		textHoraSaida.setMinimumSize(new Dimension(50, 20));
		addLinha(labelHoraSaida,textHoraSaida, 1);
		
		textHoraChegada.setMinimumSize(new Dimension(50, 20));
		addLinha(labelHoraChegada,textHoraChegada, 1);
		
		textKmSaida.setMinimumSize(new Dimension(50, 20));
		addLinha(labelKmSaida,textKmSaida, 1);
		
		textKmChegada.setMinimumSize(new Dimension(50, 20));
		addLinha(labelKmChegada,textKmChegada, 1);
		
		
		
		
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
	
	void addLinha(JLabel label,JTextField text, int opt)
	{
		cons.gridx = 1;
		cons.gridy++;
		cons.weighty= 1;
		cons.weightx = 0;
		body.add(label, cons);
		
		cons.gridx = 2;
		cons.weightx = 1;
		body.add( text, cons );
	}
	
	private class ListenerBuscarCliente implements MouseListener  
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			controler.selecionarCliente( 2 );
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
	
	
	private class ListenerCancelar implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
			controler.cancelar();
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
			
			dados[i++][1]=textKmSaida.getText();
			dados[i][0]= "kmSaida";
			dados[i++][1]=textKmChegada.getText();
			dados[i][0]= "kmChegada";
			dados[i++][1]=textHoraSaida.getText();
			dados[i][0]= "horaSaida";
			dados[i++][1]=textHoraChegada.getText();
			dados[i][0]= "horaChegada";
			
			
			controler.concluirTestDrive(dados);
		}
	}

}
