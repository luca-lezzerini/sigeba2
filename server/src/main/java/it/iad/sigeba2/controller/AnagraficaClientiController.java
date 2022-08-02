package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AnagraficaClientiController {

    private final List<Cliente> clienti = new ArrayList<>();

    /* Il metodo prende in input un criterio di ricerca inserito dall'utente, tipicamente una stringa, 
    che potrebbe essere una parte del nome o del cognome o del codice fiscale del cliente da cercare. 
    Una volta inserito questo criterio di ricerca, comparirà l'elenco dei clienti che soddisfano 
    quella ricerca, sotto forma di tabella. */
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

        String crit = criterio.getCriterio();
        List<ClienteDto> dtos = new ArrayList<>();
        log.debug("Entrato in cercaCliente");
        
        for (Cliente cliente : clienti) {
            if(cliente.getNome().contains(crit) || cliente.getCognome().contains(crit)
                    || cliente.getCodiceFiscale().contains(crit)){
                log.debug("Entrato in if cercaCliente");
                ClienteDto dto = new ClienteDto();
                dto.setCodiceFiscale(cliente.getCodiceFiscale());
                dto.setCognome(cliente.getCognome());
                dto.setId(cliente.getId());
                dto.setNome(cliente.getNome());
                dtos.add(dto);
        }
    }
        log.debug("Uscito da cercaCliente");
        return dtos;
    }

    /**
     * Inserisce un nuovo dto nel DB
     *
     * @param dto il DTO con i dati del dto da inserire (id deve essere
 null o zero) e il criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei clienti, dopo l'inserimento, che soddisfano ilc
     * riterio passato.
     */
    @ResponseBody
// inserisco  la request verso inseriscicliente  
    @RequestMapping("/inserisciCliente")
//creo il metodo inserisciCliente
    public List<ClienteDto> inserisciCliente(@RequestBody ClienteDto dto) {
        log.debug("Entra in inserisciCliente");
        // riceve il DTO e lo trasforma in Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCognome(dto.getCognome());
        cliente.setId(dto.getId());
        cliente.setCodiceFiscale(dto.getCodiceFiscale());
        // aggiunge il dto alla lista dei clienti
        clienti.add(cliente);
        // chiama il metodo cercaCliente per ritornare la lista filtrata
        // TODO: da rimuovere
        log.debug("Esci da inserisciCliente");
        List<ClienteDto> clienteDto = mostraTuttiClienti();
        return clienteDto;

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
        System.out.println("Entrato");
        throw new UnsupportedOperationException();

    }

    /**
     * Il metodo modifica il cliente sostituendolo con quello passato cercandolo
     * tramite id
     *
     * @param modifica contienem il cliente da sostituire sul data base, ed il
     * criterio per ritornare la lista dei clienti aggiornata.
     * @return la lista dei clienti aggiornata filtrata con il criterio passato
     */
    @ResponseBody
    @RequestMapping("/modificaCliente")
    public List<ClienteDto> modificaCliente(@RequestBody CriterioModificaClienteDto modifica) {
        System.out.println("Entrato");
        throw new UnsupportedOperationException();

    }

    /**
     * Cancella il cliente dal DB. Il cliente da cancellare viene identificato
     * mediante
     *
     * @param criterio
     * @return
     */
    @RequestMapping("/cancellaCliente")
    @ResponseBody
    public List<ClienteDto> cancellaCliente(@RequestBody CriterioCancellazioneClienteDto criterio) {
        System.out.println("Entrato");
        throw new UnsupportedOperationException();
    }

    @RequestMapping("/mostraTuttiClienti")
    @ResponseBody
    public List<ClienteDto> mostraTuttiClienti() {
        log.debug("Entrato in mostraTuttiClienti");
        // crea la lista risultato (vuota)
        List<ClienteDto> dtos = new ArrayList<>();
        // copia tutti i clienti nei DTO
        for (Cliente cliente : clienti) {
            ClienteDto dto = new ClienteDto();
            dto.setCodiceFiscale(cliente.getCodiceFiscale());
            dto.setCognome(cliente.getCognome());
            dto.setId(cliente.getId());
            dto.setNome(cliente.getNome());

            dtos.add(dto);
        }
        log.debug("Uscito da mostraTuttiClienti");
        // ritorna la lista dei DTO
        return dtos;
    }
}
