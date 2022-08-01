package it.iad.sigeba2.dto;


@Deprecated
public class RandomPariDispariDto {

	private Integer randomNumero;
	private String responso;

	public RandomPariDispariDto() {}

	public RandomPariDispariDto(Integer randomNumero, String responso) {
		super();
		this.randomNumero = randomNumero;
		this.responso = responso;
	}

	public Integer getRandomNumero() {
		return randomNumero;
	}

	public void setRandomNumero(Integer randomNumero) {
		this.randomNumero = randomNumero;
	}

	public String getResponso() {
		return responso;
	}

	public void setResponso(String responso) {
		this.responso = responso;
	}

}
