package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.DataDayDto06;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class New1Controller06 {

    @RequestMapping("/new1Controller06")
    @ResponseBody

   public  DataDayDto06  New1Controller06(){         
    String s = LocalDateTime.now().toString();    

        DataDayDto06 dto = new DataDayDto06(s);
        return dto;
    }

}
