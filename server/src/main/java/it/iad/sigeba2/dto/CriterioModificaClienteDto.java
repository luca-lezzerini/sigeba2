package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaClienteDto {

    private ClienteDto cliente;
    private CriterioClienteDto filtro;
}
