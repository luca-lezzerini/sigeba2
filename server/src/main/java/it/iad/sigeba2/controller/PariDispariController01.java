package it.iad.sigeba2.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.iad.sigeba2.dto.RandomPariDispariDto;

@RestController
public class PariDispariController01 {


	@RequestMapping("/paridispari01")
	@ResponseBody
	public RandomPariDispariDto paridispari01() {

		Random r = new Random();
		Integer numero = r.nextInt(90) + 1;
		String testo;

		if(numero % 2 == 0) {
			testo = "Il numero è pari.";
		} else {
			testo = "Il numero è dispari.";
		}

		RandomPariDispariDto dto = new RandomPariDispariDto(numero, testo);
		return dto;	
	}


}
