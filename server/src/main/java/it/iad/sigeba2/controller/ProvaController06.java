/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.DataDto06;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Deprecated
@RestController
public class ProvaController06 {

    @RequestMapping("/prova06")
    @ResponseBody
    public DataDto06 prova06() {
        System.out.println("Ciao! Sei riuscito a chiamare questo controller");
        String s = LocalDateTime.now().toString();

        DataDto06 dto = new DataDto06(s);
         return dto;
    
    
    }

}
