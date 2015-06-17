/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisauto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 *
 * @author fcm3
 */
@DatabaseTable(tableName = "cliente")
public class ModelCliente {
	
	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField
	String nome;
	
	@DatabaseField
	String cpf;
	
	@DatabaseField
	String telefone;
	
	@DatabaseField
	String email;
	
	@DatabaseField
	String endereco;
	
	@DatabaseField
	String observacoes;
	
	
}
