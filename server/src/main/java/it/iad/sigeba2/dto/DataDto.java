/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class DataDto {
    private String data;
    
    public DataDto(){
    }
    
    public DataDto (String data){
        this.data = data;
    }
}
