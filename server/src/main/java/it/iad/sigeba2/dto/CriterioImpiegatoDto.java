package it.iad.sigeba2.dto;

import lombok.Data;




@Data
public class CriterioImpiegatoDto {

public CriterioImpiegatoDto()  {}

public CriterioImpiegatoDto(String criterio){
    this.criterio = criterio;
}

private String criterio = "";

}
