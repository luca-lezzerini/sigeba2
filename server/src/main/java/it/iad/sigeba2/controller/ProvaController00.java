package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.DataNumeroDto;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Deprecated
@RestController
public class ProvaController00 {

    @RequestMapping("/prova00")
    @ResponseBody
    public DataNumeroDto prova00() {
        System.out.println("Ciao! Sei riuscito a chiamare questo controller");
        String s = LocalDateTime.now().toString();
        Random r = new Random();
        int n = r.nextInt(90) + 1;
        
        DataNumeroDto dto = new DataNumeroDto(s, n);
        return dto;
    }

    @RequestMapping("/generaNumero00")
    @ResponseBody
    public Integer generaNumero00() {
        System.out.println("Giochiamo a tombola ...");
        Random r = new Random();
        int n = r.nextInt(90) + 1;
        return n;
    }
}
