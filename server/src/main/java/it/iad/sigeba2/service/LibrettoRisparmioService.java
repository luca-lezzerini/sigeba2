package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioInserimentoLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioModificaLibrettoRisparmioDto;
import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.LibrettoRisparmio;
import java.util.List;

public interface LibrettoRisparmioService {
 
    /**
     * 
     * @param criterio
     * @return
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> cercaLibrettoRisparmio(CriterioLibrettoRisparmioDto criterio) throws SigebaException;
    
    /**
     * 
     * @param librettoRisparmioDaInserireDto
     * @return
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> inserisciLibrettoRisparmio(CriterioInserimentoLibrettoRisparmioDto librettoRisparmioDaInserireDto) throws SigebaException;
    
    /**
     * 
     * @param dtoCancellazione
     * @return
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> cancellaLibrettoRisparmio(CriterioCancellazioneLibrettoRisparmioDto dtoCancellazione) throws SigebaException;
    
    /**
     * 
     * @param modifica
     * @return
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> modificaLibrettoRisparmio(CriterioModificaLibrettoRisparmioDto modifica) throws SigebaException;
    
    /**
     * 
     * @param librettoRisparmioDto
     * @return
     * @throws SigebaException 
     */
    LibrettoRisparmio leggiLibrettoRisparmio(SimpleIdDto librettoRisparmioDto) throws SigebaException;
    
    
}
