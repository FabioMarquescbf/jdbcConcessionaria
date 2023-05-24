package br.com.aula.model;

public class Veiculo {
	private int id;
	private String modelo;
	private String marca;
	private String placa;
	private double valor;
	
	public Veiculo() {
		
	}
	

	public Veiculo(int id, String modelo, String marca, String placa, double valor) {
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
		this.placa = placa;
		this.valor = valor;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}



	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", modelo=" + modelo + ", marca=" + marca + ", placa=" + placa + ", valor=" + valor
				+ "]";
	}
	
	
	
	
}
