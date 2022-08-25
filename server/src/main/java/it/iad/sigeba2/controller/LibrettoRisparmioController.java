package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioCancellazioneLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioInserimentoLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioModificaLibrettoRisparmioDto;
import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.LibrettoRisparmio;
import it.iad.sigeba2.service.LibrettoRisparmioService;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LibrettoRisparmioController {
    
    @Autowired
    private LibrettoRisparmioService librettoRisparmioService;
    
    /**
     * Cerca i libretti che soddisfano il criterio di ricerca passato.
     * @param criterio DTO con il criterio stringa, chiave parziale per il codice del libretto
     * @return la lista dei libretti che soddisfano il criterio di ricerca passato,
     * se non ne trova torna una lista vuota.
     * @throws SigebaException 
     */
    @RequestMapping("/cercaLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> cercaLibrettoRisparmio(@RequestBody CriterioLibrettoRisparmioDto criterio) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.cercaLibrettoRisparmio(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cercaLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    /**
     * Inserisce un nuovo libretto risparmio nel DB
     * @param librettoRisparmioDaInserireDto il DTO con i dati del librettoRisparmio 
     * da inserire (id deve essere null o zero) e il criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei librettiRisparmio, dopo l'inserimento, che soddisfano il criterio passato
     * @throws SigebaException 
     */
    @RequestMapping("/inserisciLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> inserisciLibrettoRisparmio(@RequestBody CriterioInserimentoLibrettoRisparmioDto librettoRisparmioDaInserireDto) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.inserisciLibrettoRisparmio(librettoRisparmioDaInserireDto);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller inserisciLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    /**
     * Cancella il librettoRisparmio dal DB. Il libretto da cancellare viene identificato mediante 
     * @param dtoCancellazione che contiene l'id del libretto da cancellare
     * @return la lista dei librettiRisparmio, dopo la rimozione.
     * @throws SigebaException 
     */
    @RequestMapping("/cancellaLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> cancellaLibrettoRisparmio(@RequestBody CriterioCancellazioneLibrettoRisparmioDto dtoCancellazione) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.cancellaLibrettoRisparmio(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    /**
     * Il metodo modifica il librettoRisparmio sostituendolo con quello passato 
     * cercandolo tramite id
     * @param modifica contiene il libretto da sostituire sul DB, e il criterio 
     * per ritornare la lista dei librettiRisparmio aggiornata.
     * @return la lista dei libretti aggiornata e filtrata con il criterio passato
     * @throws SigebaException 
     */
    @RequestMapping("/modificaLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> modificaLibrettoRisparmio(@RequestBody CriterioModificaLibrettoRisparmioDto modifica) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.modificaLibrettoRisparmio(modifica);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller modificaLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    /**
     * Recupera i dati di un librettoRisparmio, fornito l'ID. Se non lo trova ritorna null.
     * @param librettoRisparmioDto il DTO con l'ID del libretto da cercare (diverso da null e da 0)
     * @return il libretto trovato o null se non lo trova
     * @throws SigebaException 
     */
    @RequestMapping("/leggiLibrettoRisparmio")
    @ResponseBody
    public LibrettoRisparmio leggiLibrettoRisparmio(@RequestBody SimpleIdDto librettoRisparmioDto) throws SigebaException {
        return librettoRisparmioService.leggiLibrettoRisparmio(librettoRisparmioDto);
    }
}
