package it.iad.sigeba2.controller;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.iad.sigeba2.dto.SalutoDto01;

@RestController
public class SalutoController01 {
	
	@RequestMapping("/saluto01")
	@ResponseBody
	public SalutoDto01 saluto01() {
		
		LocalTime ora = LocalTime.now();
		int orario = LocalTime.now().getHour();
		String saluto;
		
		if (orario > 6 && orario < 13){
			saluto ="Buongiorno!";
		} else if (orario >= 13 && orario < 17) {
			saluto = "Buon pomeriggio!";
		} else {
			saluto = "Buonasera!";
		}
		SalutoDto01 dto = new SalutoDto01(ora, saluto);
		return dto;
		
	}

}
