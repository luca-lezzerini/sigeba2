package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.model.TipoConto;
import java.util.List;

public interface TipologieContiService {


    List<TipoContoDto> cercaTipoContoDto(CriterioTipoContoDto criterio);

    List<TipoContoDto> inserisciTipoContoDto(TipoContoDto inserisci);

    List<TipoContoDto> cancellaTipoContoDto(CriterioCancellazioneTipoContoDto cancella);

    List<TipoContoDto> modificaTipoContoDto(CriterioModificaTipoContoDto modifica);
    
    TipoConto leggiCliente(SimpleIdDto cliente);

}
