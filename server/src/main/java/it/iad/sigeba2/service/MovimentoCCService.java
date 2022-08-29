
package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioInserimentoMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioModificaMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioMovimentoCCDto;
import it.iad.sigeba2.dto.MovimentoCCDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.MovimentoCC;
import java.util.List;


public interface MovimentoCCService {
    
    List<MovimentoCCDto> cercaMovimentoCC (CriterioMovimentoCCDto criterio) throws SigebaException;
    
    List<MovimentoCCDto> inserisciMovimentoCC(CriterioInserimentoMovimentoCCDto movimentoccDaInserireDto)throws SigebaException;
    
    List<MovimentoCCDto> cancellaMovimentoCC(CriterioCancellazioneMovimentoCCDto dtoCancellazione)throws SigebaException;
    
    List<MovimentoCCDto> modificaMovimentoCC (CriterioModificaMovimentoCCDto modifica)throws SigebaException;
    
    MovimentoCC leggiMovimentoCC (SimpleIdDto movimentoccdto) throws SigebaException;
}
