package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.New2Dto06;
import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Deprecated
public class New2Controller06 {

    @RequestMapping("/New206")
    @ResponseBody
    public New2Dto06 New206() {
     
     String g = LocalDateTime.now().toString();
    
    
     New2Dto06 dto = new New2Dto06(g);
         return dto;
             
    }

   



}
