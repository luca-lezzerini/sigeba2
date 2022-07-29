package it.iad.sigeba2.dto;

import java.time.LocalTime;

public class SalutoDto01 {

	private LocalTime orario;
	private String saluto;

	public SalutoDto01() {}

	public SalutoDto01(LocalTime orario, String saluto) {
		super();
		this.orario = orario;
		this.saluto = saluto;
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

	public String getSaluto() {
		return saluto;
	}

	public void setSaluto(String saluto) {
		this.saluto = saluto;
	}

}
