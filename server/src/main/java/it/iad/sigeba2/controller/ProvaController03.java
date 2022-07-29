package it.iad.sigeba2.controller;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import it.iad.sigeba2.dto.DataNumeroDto;
import it.iad.sigeba2.dto.ProvaControllerDto;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import java.time.Year;

@RestController
public class ProvaController03 {

    @RequestMapping("/prova03")
    @ResponseBody

    public ProvaControllerDto prova03() {
        System.out.println("Stiamo provando a scrivere la data, l'ora estta e un numero casuale!");
        String s = LocalDateTime.now().toString();
        Random r = new Random();
        int n = r.nextInt(1000) + 1;

        ProvaControllerDto dto = new ProvaControllerDto(s, n);
        return dto;
    }

    @RequestMapping("/moltiplica03")
    @ResponseBody
    public ProvaControllerDto moltiplica03() {
        System.out.println("8 * 5 /2");
        String s = LocalDateTime.now().toString();
        int n = 8;
        int n1 = 5;
        int risultato = n * n1 / 2;
        ProvaControllerDto dto = new ProvaControllerDto(s, risultato);
        return dto;

    }

    @RequestMapping("/anno03")
    @ResponseBody
    public String anno03() {
        System.out.println("L'anno corrente e'");
        String s = Year.now().toString();
        return s;
    }

}
