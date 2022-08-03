package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaTipoContoDto {

    private TipoContoDto tipoConto;
    private TipoContoDto filtro;
}
