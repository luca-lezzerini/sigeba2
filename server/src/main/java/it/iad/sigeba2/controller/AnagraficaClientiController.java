package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.service.AnagraficaClientiService;
import it.iad.sigeba2.service.impl.AnagraficaClientiServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AnagraficaClientiController {

    @Autowired
    private AnagraficaClientiService anagraficaClientiService;

    /**
     * Cerca i clienti che soddisfano il criterio di ricerca passato.
     *
     * @param criterio DTO con il criterio stringa, chiave parziale in or per
     * nome, cognome e codice fiscale
     * @return la lista dei clienti che soddisfano il criterio, se non ne trova
     * ritorna una lista vuota
     */
    @RequestMapping("/cercaCliente")
    @ResponseBody
    public List<ClienteDto> cercaCliente(@RequestBody CriterioClienteDto criterio) {
        return anagraficaClientiService.cercaCliente(criterio);
    }

    /**
     * Inserisce un nuovo dto nel DB
     *
     * @param dto il DTO con i dati del dto da inserire (id deve essere null o
     * zero) e il criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei clienti, dopo l'inserimento, che soddisfano il
     * criterio passato.
     */
    @ResponseBody
// inserisco  la request verso inseriscicliente  
    @RequestMapping("/inserisciCliente")
//creo il metodo inserisciCliente
    public List<ClienteDto> inserisciCliente(@RequestBody CriterioInserimentoClienteDto dto) {
        return anagraficaClientiService.inserisciCliente(dto);

    }

    /**
     * Recupera i dati di un cliente fornito l'ID. Se non lo trova ritorna null.
     *
     * @param cliente il DTO con l'ID del cliente da cercare (diverso da null e
     * da 0)
     * @return il cliente trovato o null se non lo trova
     */
    @ResponseBody
    @RequestMapping("/leggiCliente")
    public Cliente leggiCliente(@RequestBody SimpleIdDto cliente) {
        return anagraficaClientiService.leggiCliente(cliente);
    }

    /**
     * Il metodo modifica il cliente sostituendolo con quello passato cercandolo
     * tramite id
     *
     * @param modificaDto contienem il cliente da sostituire sul data base, ed
     * il criterio per ritornare la lista dei clienti aggiornata.
     * @return la lista dei clienti aggiornata filtrata con il criterio passato
     */
    @ResponseBody
    @RequestMapping("/modificaCliente")
    public List<ClienteDto> modificaCliente(@RequestBody CriterioModificaClienteDto modificaDto) {
        return anagraficaClientiService.modificaCliente(modificaDto);
    }

    /**
     * Cancella il cliente dal DB. Il cliente da cancellare viene identificato
     * mediante
     *
     * @param dtoCancellazione
     * @return
     */
    @RequestMapping("/cancellaCliente")
    @ResponseBody
    public List<ClienteDto> cancellaCliente(@RequestBody CriterioCancellazioneClienteDto dtoCancellazione) {
        return anagraficaClientiService.cancellaCliente(dtoCancellazione);
    }
}
