package it.iad.sigeba2.dto;

public class CriterioInserimentoClienteDto extends CriterioModificaClienteDto{

    public CriterioInserimentoClienteDto() {
    }

    public CriterioInserimentoClienteDto(ClienteDto cliente) {
        super(cliente);
    }

    public CriterioInserimentoClienteDto(ClienteDto cliente, CriterioClienteDto filtro) {
        super(cliente, filtro);
    }

}
