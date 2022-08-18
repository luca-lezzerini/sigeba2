package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.ContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioInserimentoContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioModificaContoCorrenteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.ContoCorrente;
import java.util.List;

public interface ContoCorrenteService {

    /**
     *
     * @param criterio
     * @return
     * @throws SigebaException
     */
    List<ContoCorrenteDto> cercaContoCorrente(CriterioContoCorrenteDto criterio) throws SigebaException;

    /**
     *
     * @param contoCorrenteDaInserireDto
     * @return
     * @throws SigebaException
     */
    List<ContoCorrenteDto> inserisciContoCorrente(CriterioInserimentoContoCorrenteDto contoCorrenteDaInserireDto) throws SigebaException;
     /**
      * 
      * @param dtoCancellazione
      * @return
      * @throws SigebaException 
      */
    List<ContoCorrenteDto> cancellaContoCorrente(CriterioCancellazioneContoCorrenteDto dtoCancellazione) throws SigebaException;
      /**
       * 
       * @param modifica
       * @return
       * @throws SigebaException 
       */
    List<ContoCorrenteDto> modificaContoCorrente(CriterioModificaContoCorrenteDto modifica) throws SigebaException;
       /**
        * 
        * @param contoCorrenteDto
        * @return
        * @throws SigebaException 
        */
    ContoCorrente leggiContoCorrente(SimpleIdDto contoCorrenteDto) throws SigebaException;
}
