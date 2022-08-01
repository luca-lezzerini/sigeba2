package it.iad.sigeba2.controller;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.iad.sigeba2.dto.GiornoFerragostoDto;

@Deprecated
@RestController
public class FerragostoController01 {
	
	
	@RequestMapping("giornoferragosto01")
	@ResponseBody
	public GiornoFerragostoDto giornoferragosto01() {
		
		LocalDate data = LocalDate.of(2022, 8, 15);
		String giorno = data.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.ITALY).toString();
		GiornoFerragostoDto dto = new GiornoFerragostoDto(data, giorno);
		
		return dto;
		
	}
	

}
