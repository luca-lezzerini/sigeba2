package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnagraficaClientiServiceImpl implements AnagraficaClientiService {

    private final Map<Long, Cliente> mappaClienti = new HashMap<>();
    private Long prossimaChiave = 0L;

    @Override
    public List<ClienteDto> cercaCliente(CriterioClienteDto criterio) {

        log.debug("Entrato in cercaCliente");
        String crit = criterio.getCriterio();
        List<ClienteDto> dtos = new ArrayList<>();

        // FIXME: dopo cambio mappa non funziona più
//        for (Cliente cliente : clienti) {
//            if (cliente.getNome().contains(crit) || cliente.getCognome().contains(crit)
//                    || cliente.getCodiceFiscale().contains(crit)) {
//                log.debug("Entrato in if cercaCliente");
//                ClienteDto dto = new ClienteDto();
//                dto.setCodiceFiscale(cliente.getCodiceFiscale());
//                dto.setCognome(cliente.getCognome());
//                dto.setId(cliente.getId());
//                dto.setNome(cliente.getNome());
//                dtos.add(dto);
//            }
//        }
        log.debug("Uscito da cercaCliente");
        return dtos;
    }

    @Override
    public List<ClienteDto> inserisciCliente(ClienteDto dto) {
        log.debug("Entra in inserisciCliente");
        // riceve il DTO e lo trasforma in Cliente
        Cliente cliente = new Cliente();

// imposta la chiave in base alla nuova posizione dove sarà aggiunto il cliente nella lista clienti
        cliente.setId(prossimaChiave);
        prossimaChiave += 1;

        // popolo gli altri campi
        cliente.setNome(dto.getNome());
        cliente.setCognome(dto.getCognome());
        cliente.setCodiceFiscale(dto.getCodiceFiscale());
        // aggiunge il dto alla lista dei clienti
        mappaClienti.put(cliente.getId(), cliente);
        // chiama il metodo cercaCliente per ritornare la lista filtrata
        // TODO: da rimuovere
        log.debug("Esci da inserisciCliente");
        List<ClienteDto> clienteDto = mostraTuttiClienti();
        return clienteDto;
    }

    @Override
    public Cliente leggiCliente(SimpleIdDto cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ClienteDto> modificaCliente(CriterioModificaClienteDto modificaDto) {
        log.debug("Entrato in modificaClienti");

        ClienteDto clienteModificato = modificaDto.getCliente();

        Cliente clienteCheSostituisce = new Cliente();
        clienteCheSostituisce.setNome(clienteModificato.getNome());
        clienteCheSostituisce.setCognome(clienteModificato.getCognome());
        clienteCheSostituisce.setCodiceFiscale(clienteModificato.getCodiceFiscale());
        clienteCheSostituisce.setId(clienteModificato.getId());

        Long posizioneDaCambiare = clienteModificato.getId();
        mappaClienti.put(posizioneDaCambiare, clienteCheSostituisce);
        log.debug("Esce da modificaClienti");
        return mostraTuttiClienti();
    }

    @Override
    public List<ClienteDto> cancellaCliente(CriterioCancellazioneClienteDto dtoCancellazione) {
        log.debug("Entrato in cancellaCliente");
        // recupera l'id del cliente da rimuovere
        Long idDaRimuovere = dtoCancellazione.getIdCliente();

        // lo rimuove
        mappaClienti.remove(idDaRimuovere);

        // recupera i clienti rimasti
        List<ClienteDto> clientiRimasti;
        clientiRimasti = mostraTuttiClienti();
        log.debug("In uscita da cancellaCliente");
        return clientiRimasti;
    }

    @Override
    public List<ClienteDto> mostraTuttiClienti() {
        log.debug("Entrato in mostraTuttiClienti");
        // crea la lista risultato (vuota)
        List<ClienteDto> clientiTrovati = new ArrayList<>();
        // copia tutti i clienti nei DTO
        // FIXME: cambiare dopo usao di mappa
        Collection<Cliente> listaClienti = mappaClienti.values();
        for (Cliente cliente : listaClienti) {
            ClienteDto dto = new ClienteDto();
            dto.setCodiceFiscale(cliente.getCodiceFiscale());
            dto.setCognome(cliente.getCognome());
            dto.setId(cliente.getId());
            dto.setNome(cliente.getNome());

            clientiTrovati.add(dto);
        }
        log.debug("Uscito da mostraTuttiClienti");
        // ritorna la lista dei DTO
        return clientiTrovati;
    }

}
