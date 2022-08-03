package it.iad.sigeba2.dto;


public class CriterioInserimentoTipoContoDto extends CriterioModificaTipoContoDto{

    public CriterioInserimentoTipoContoDto() {
    }

    public CriterioInserimentoTipoContoDto(TipoContoDto tipoConto) {
        super(tipoConto);
    }

    public CriterioInserimentoTipoContoDto(TipoContoDto tipoConto, CriterioTipoContoDto filtro) {
        super(tipoConto, filtro);
    }

}
