package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Collection<Cliente> lista = mappaClienti.values();
        for (Cliente cliente : lista) {
            if (cliente.getNome().contains(crit) || cliente.getCognome().contains(crit)
                    || cliente.getCodiceFiscale().contains(crit)) {
                dtos.add(new ClienteDto(cliente));
            }
        }
        log.debug("Uscito da cercaCliente");
        return dtos;
    }

    @Override
    public List<ClienteDto> inserisciCliente(CriterioInserimentoClienteDto dto) {
        log.debug("Entra in inserisciCliente");
        // riceve il DTO e lo trasforma in Cliente
        Cliente cliente = new Cliente(dto.getCliente());

        // imposta la chiave in base alla nuova posizione dove sarà aggiunto il cliente nella lista clienti
        cliente.setId(prossimaChiave);
        prossimaChiave += 1;

        // aggiunge il dto alla lista dei clienti
        mappaClienti.put(cliente.getId(), cliente);
        // chiama il metodo cercaCliente per ritornare la lista filtrata
        log.debug("Esci da inserisciCliente");
        List<ClienteDto> clienteDto = cercaCliente(dto.getFiltro());
        return clienteDto;
    }

    @Override
    public Cliente leggiCliente(SimpleIdDto chiave) {
        return mappaClienti.get(chiave.getId());
    }

    @Override
    public List<ClienteDto> modificaCliente(CriterioModificaClienteDto modificaDto) {
        log.debug("Entrato in modificaClienti");

        ClienteDto clienteModificato = modificaDto.getCliente();
        Cliente clienteCheSostituisce = new Cliente(clienteModificato);
        Long posizioneDaCambiare = clienteModificato.getId();

        mappaClienti.put(posizioneDaCambiare, clienteCheSostituisce);
        log.debug("Esce da modificaClienti");
        return cercaCliente(modificaDto.getFiltro());
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
        clientiRimasti = cercaCliente(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaCliente");
        return clientiRimasti;
    }

   
}
