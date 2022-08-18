package it.iad.sigeba2.dto;

public class CriterioInserimentoContoCorrenteDto extends CriterioModificaContoCorrenteDto {

    public CriterioInserimentoContoCorrenteDto() {
    }

    public CriterioInserimentoContoCorrenteDto(ContoCorrenteDto contoCorrente) {
        super(contoCorrente);
    }

    public CriterioInserimentoContoCorrenteDto(ContoCorrenteDto contoCorrente, CriterioContoCorrenteDto filtro) {
        super(contoCorrente, filtro);
    }

}
