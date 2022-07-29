package it.iad.sigeba2.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller01 {

	@RequestMapping("/generaData01")
	@ResponseBody
	public String generaData01() {
		System.out.println("Sto generando la data di oggi...");
		LocalDate data = LocalDate.now();
		return data.toString();
	}

}
