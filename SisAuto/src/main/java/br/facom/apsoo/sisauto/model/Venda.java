package br.facom.apsoo.sisauto.model;

import java.util.Calendar;

public class Venda {
	private long id;
	private String cliente;
	private long veiculo;
	private boolean financiado; 
	private Calendar dataVenda;
	
	
	public Venda(){
		this.dataVenda = Calendar.getInstance();
	}

	//getters and setters
	public boolean isFinanciado() {
		return financiado;
	}

	public void setFinanciado(boolean financiado) {
		this.financiado = financiado;
	}

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
