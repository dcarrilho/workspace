/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisauto;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import static javax.swing.BoxLayout.LINE_AXIS;
import static javax.swing.BoxLayout.PAGE_AXIS;

/**
 *
 * @author fcm3
 */
public class ViewInicial extends JPanel {
	JButton efetuarVenda, efetuarTestDrive;
	ControlerPrincipal controler;
	ViewInicial(ControlerPrincipal controlerEnt)
	{
		controler= controlerEnt;
		GridBagConstraints cons = new GridBagConstraints();
		
		
		efetuarVenda= new JButton( "Efetuar venda" );
		efetuarTestDrive= new JButton( "Test drive" );
		
		efetuarVenda.addActionListener(new ListenerEfetuarVenda());
		efetuarTestDrive.addActionListener(new ListenerEfetuarTestDrive());
		
		
		//efetuarVenda.setBounds( 50, 50, 100, 100 );
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		cons.insets = new Insets(4,4,4,4);
		
		cons.weighty = 1;
        cons.gridx = 1;
		cons.gridy = 1;
		cons.weightx = 0;
		add(efetuarVenda, cons);
		
		cons.gridx = 2;
		cons.weightx = 1;
		add( efetuarTestDrive, cons );
		
		
		add( efetuarVenda );
		add( efetuarTestDrive );
		
		setVisible(true);
		revalidate();
	}
	
	
	private class ListenerEfetuarVenda implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
				controler.efetuarVenda();
		}
	}
	
	private class ListenerEfetuarTestDrive implements ActionListener  
	{
		public void actionPerformed(ActionEvent e) {
				controler.efetuarTestDrive();
		}
	}
}
