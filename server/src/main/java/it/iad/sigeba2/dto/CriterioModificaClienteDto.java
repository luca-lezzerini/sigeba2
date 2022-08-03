package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaClienteDto {

    public CriterioModificaClienteDto() {
    }

    public CriterioModificaClienteDto(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public CriterioModificaClienteDto(ClienteDto cliente, CriterioClienteDto filtro) {
        this.cliente = cliente;
        this.filtro = filtro;
    }

    private ClienteDto cliente;
    private CriterioClienteDto filtro = new CriterioClienteDto("");
}
