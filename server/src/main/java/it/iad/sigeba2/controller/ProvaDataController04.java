/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.DataDto;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvaDataController04 {

    @RequestMapping("/data04")
    @ResponseBody

    public DataDto data04() {
        String d = LocalDate.now().toString();
        DataDto sd = new DataDto(d);
        return sd;
    }
}
