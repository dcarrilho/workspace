/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisauto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author fcm3
 */

@DatabaseTable(tableName = "venda")
public class ModelVenda {
	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField(canBeNull = false, foreign = true)
	ModelCliente cliente;
	
	@DatabaseField(canBeNull = false, foreign = true)
    ModelVeiculo veiculo;
	
	@DatabaseField
	int formaPagamento;
	
	@DatabaseField
	Date data;
	
	@DatabaseField
	float valor;
	
	ControlerPrincipal controler;
	
	public void setCliente( int idCliente )
	{
		List<ModelCliente> clienteList;
		try {			
			
			//daoCliente.
			clienteList = controler.daoCliente.query(
			controler.daoCliente.queryBuilder().where()
				.eq("id", idCliente)
				.prepare());
			if( clienteList.size()>0)
			{
				cliente= clienteList.get(0);
			}
			else
				JOptionPane.showMessageDialog( null, "ERROR", "ERROR", JOptionPane.INFORMATION_MESSAGE );
		} catch (SQLException ex) {
			Logger.getLogger(ModelVenda.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void setControler(ControlerPrincipal controler)
	{
		this.controler= controler;
	}
	
	public void efetuarVenda(String dados[][])
	{
		veiculo= new ModelVeiculo();
		try
		{
			for(int i=0;i<dados.length;i++)
			{
				if(dados[i]!=null && dados[i][0]!=null)
				{
					switch(dados[i][0])
					{
						case "marca":
							veiculo.marca= dados[i][1];
							break;
						case "modelo":
							veiculo.modelo= dados[i][1];
							break;
						case "cor":
							veiculo.cor= dados[i][1];
							break;
						case "ano":
							veiculo.ano= dados[i][1];
							break;
						case "valor":
							valor= Float.parseFloat(dados[i][1]);
							break;
						case "formaPagamento":
							formaPagamento= Integer.parseInt(dados[i][1]);
							break;
					}
				}
			}

		}
		catch (Exception ex) {
			//Logger.getLogger(ViewSelecionarCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
