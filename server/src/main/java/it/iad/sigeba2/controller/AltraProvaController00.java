package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.SoloDataDto;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Deprecated
@RestController
public class AltraProvaController00 {

    @RequestMapping("/data00")
    @ResponseBody
    public SoloDataDto data00() {
        String d = LocalDate.now().toString();
        SoloDataDto sd = new SoloDataDto(d);
        return sd;
    }
}
