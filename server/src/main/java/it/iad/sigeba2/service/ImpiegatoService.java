package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneImpiegatoDto;
import it.iad.sigeba2.dto.CriterioImpiegatoDto;
import it.iad.sigeba2.dto.CriterioInserimentoImpiegatoDto;
import it.iad.sigeba2.dto.CriterioModificaImpiegatoDto;
import it.iad.sigeba2.dto.ImpiegatoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.Impiegato;
import java.util.List;

public interface ImpiegatoService {

    /**
     *
     * @param criterio
     * @return
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<ImpiegatoDto> cercaImpiegato(CriterioImpiegatoDto criterio) throws SigebaException;

    /**
     *
     * @param impiegatoDaInseriredto
     * @return
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<ImpiegatoDto> inserisciImpiegato(CriterioInserimentoImpiegatoDto impiegatoDaInseriredto) throws SigebaException;

    /**
     *
     * @param dtoCancellazione
     * @return
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<ImpiegatoDto> cancellaImpiegato(CriterioCancellazioneImpiegatoDto dtoCancellazione) throws SigebaException;

    /**
     *
     * @param Modifica
     * @return
     * @throws SigebaException
     */
    List<ImpiegatoDto> modificaImpiegato(CriterioModificaImpiegatoDto Modifica) throws SigebaException;

   
    /**
     * 
     * @param impiegatodto
     * @return
     * @throws SigebaException 
     */
    
    
    
    
    Impiegato leggiImpiegato(SimpleIdDto impiegatodto) throws SigebaException;

}
