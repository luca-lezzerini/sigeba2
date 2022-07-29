package it.iad.sigeba2.dto;

import java.time.LocalDate;

public class GiornoFerragostoDto {

	private LocalDate data;
	private String giorno;

	public GiornoFerragostoDto() {}

	public GiornoFerragostoDto(LocalDate data, String giorno) {
		super();
		this.data = data;
		this.giorno = giorno;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

}
