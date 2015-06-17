/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisauto;


import java.awt.*;

import javax.swing.*;
/**
 *
 * @author fcm3
 */
public class ViewManager extends JFrame {
	
	ViewInicial viewInicial;
	ViewEfetuarVenda viewEfetuarVenda;
	ViewSelecionarCliente viewSelecionarCliente;
	ViewTestDrive viewTestDrive;
	
	ControlerPrincipal controler;
	ViewManager( ControlerPrincipal controlerEnt )
	{
		super("SISAUTO");
		controler= controlerEnt;
		
		viewEfetuarVenda= new ViewEfetuarVenda(controler);
		viewInicial= new ViewInicial(controler);
		viewSelecionarCliente= new ViewSelecionarCliente(controler);
		viewTestDrive= new ViewTestDrive(controler);
		
		setContentPane( viewInicial );
		this.setLocation( Config.viewInicialDisplayX, Config.viewInicialDisplayY);
		this.setSize( Config.viewInicialDisplayW, Config.viewInicialDisplayH );
		revalidate();
		repaint();
		
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable(false);
		this.setVisible( true );
	}
	
	
	
	public void startViewEfetuarVenda(){
		viewEfetuarVenda.init();
		setContentPane( viewEfetuarVenda );
		this.setLocation( Config.viewInicialDisplayX, Config.viewInicialDisplayY);
		this.setSize( Config.viewInicialDisplayW, Config.viewInicialDisplayH );
		revalidate();
		repaint();
	}
	
	public void startViewTestDrive(){
		viewTestDrive.init();
		setContentPane( viewTestDrive );
		this.setLocation( Config.viewInicialDisplayX, Config.viewInicialDisplayY);
		this.setSize( Config.viewInicialDisplayW, Config.viewInicialDisplayH );
		revalidate();
		repaint();
	}
	
	public void startViewInicial(){
		setContentPane( viewInicial );
		this.setLocation( Config.viewInicialDisplayX, Config.viewInicialDisplayY);
		this.setSize( Config.viewInicialDisplayW, Config.viewInicialDisplayH );
		revalidate();
		repaint();
	}

	public void startViewSelecionarCliente() {
		setContentPane( viewSelecionarCliente );
		this.setLocation( Config.viewInicialDisplayX, Config.viewInicialDisplayY);
		this.setSize( Config.viewInicialDisplayW, Config.viewInicialDisplayH );
		revalidate();
		repaint();
	}
	
	public void selecionarCliente(int j)
	{
		viewSelecionarCliente.idRequest= j;
		viewSelecionarCliente.setVisible(true);
	}
	
}
