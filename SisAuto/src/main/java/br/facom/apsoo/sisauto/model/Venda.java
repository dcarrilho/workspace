package br.facom.apsoo.sisauto.model;

import java.util.Calendar;

public class Venda {
	private long id;
	private String cliente;
	private long veiculo;
	private Calendar dataVenda;
	
	public Venda(){
		this.dataVenda = Calendar.getInstance();
	}

	//getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public long getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(long veiculo) {
		this.veiculo = veiculo;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	

}
