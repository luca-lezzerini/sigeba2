package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioInserimentoTipoContoDto;
import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.model.TipoConto;
import java.util.List;

public interface TipologieContiService {


    List<TipoContoDto> cercaTipoConto(CriterioTipoContoDto criterio);

    List<TipoContoDto> inserisciTipoConto(CriterioInserimentoTipoContoDto contoDaInserireDto);

    List<TipoContoDto> cancellaTipoConto(CriterioCancellazioneTipoContoDto cancella);

    List<TipoContoDto> modificaTipoConto(CriterioModificaTipoContoDto modifica);
    
    TipoConto leggiTipoConto(SimpleIdDto tipocontodto);

}
