/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisauto;

/**
 *
 * @author fcm3
 */
public class Config {
	final static int viewInicialDisplayX = 100;
	final static int viewInicialDisplayY = 20;
	final static int viewInicialDisplayW = 350;
	final static int viewInicialDisplayH = 500;
	
	final static int viewBuscarClienteDisplayX = 150;
	final static int viewBuscarClienteDisplayY = 50;
	final static int viewBuscarClienteDisplayW = 350;
	final static int viewBuscarClienteDisplayH = 500;
	 
	 
	final static String DATABASE_BD= "sisauto";
	final static String DATABASE_PORTA= "3306";
	final static String DATABASE_HOST= "localhost";
	final static String DATABASE_URL = "jdbc:mysql://"+DATABASE_HOST+":"+DATABASE_PORTA+"/"+DATABASE_BD;
	final static String DATABASE_LOGIN = "sisauto";
	final static String DATABASE_PASSWORD = "sisauto";
}
