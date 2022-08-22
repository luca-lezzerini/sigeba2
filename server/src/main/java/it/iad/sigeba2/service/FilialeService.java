package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneFilialeDto;
import it.iad.sigeba2.dto.CriterioInserimentoFilialeDto;
import it.iad.sigeba2.dto.CriterioModificaFilialeDto;
import it.iad.sigeba2.dto.CriterioFilialeDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.FilialeDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.Filiale;
import java.util.List;

public interface FilialeService {


    List<FilialeDto> cercaFiliale(CriterioFilialeDto criterio) throws SigebaException;

    List<FilialeDto> inserisciFiliale(CriterioInserimentoFilialeDto filialeDaInserireDto) throws SigebaException;

    List<FilialeDto> cancellaFiliale(CriterioCancellazioneFilialeDto dtoCancellazione) throws SigebaException;

    List<FilialeDto> modificaFiliale(CriterioModificaFilialeDto modifica) throws SigebaException;

    Filiale leggiFiliale(SimpleIdDto filialedto) throws SigebaException;

}
