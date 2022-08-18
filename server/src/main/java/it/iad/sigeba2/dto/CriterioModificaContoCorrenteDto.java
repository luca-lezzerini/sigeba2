package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaContoCorrenteDto {

    public CriterioModificaContoCorrenteDto() {
    }

    public CriterioModificaContoCorrenteDto(ContoCorrenteDto contoCorrente) {
        this.contoCorrente = contoCorrente;
    }

    public CriterioModificaContoCorrenteDto(ContoCorrenteDto contoCorrente, CriterioContoCorrenteDto filtro) {
        this.contoCorrente = contoCorrente;
        this.filtro = filtro;
    }
    private ContoCorrenteDto contoCorrente;
    private CriterioContoCorrenteDto filtro = new CriterioContoCorrenteDto("");
}
