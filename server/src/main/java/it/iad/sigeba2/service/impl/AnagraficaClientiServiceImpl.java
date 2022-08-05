package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.repository.ClienteRepository;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnagraficaClientiServiceImpl implements AnagraficaClientiService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> cercaCliente(CriterioClienteDto criterio) {

        log.debug("Entrato in cercaCliente");
        String crit = criterio.getCriterio();
        List<ClienteDto> dtos = new ArrayList<>();

        // trova i clienti
        String chiaveParziale = "%" + crit + "%";
        List<Cliente> clienti = clienteRepository.findByNomeLikeOrCognomeLikeOrCodiceFiscaleLike(chiaveParziale, chiaveParziale, chiaveParziale);

        // li converte in lista di DTO
        for (Cliente cliente : clienti) {
            dtos.add(new ClienteDto(cliente));
        }
        log.debug("Uscito da cercaCliente");
        return dtos;
    }

    @Override
    public List<ClienteDto> inserisciCliente(CriterioInserimentoClienteDto dto) {
        log.debug("Entra in inserisciCliente");
        // riceve il DTO e lo trasforma in Cliente
        Cliente cliente = new Cliente(dto.getCliente());

        // aggiunge il dto alla lista dei clienti
        clienteRepository.save(cliente);

        // chiama il metodo cercaCliente per ritornare la lista filtrata
        log.debug("Esci da inserisciCliente");
        List<ClienteDto> clienteDto = cercaCliente(dto.getFiltro());
        return clienteDto;
    }

    @Override
    public Cliente leggiCliente(SimpleIdDto chiave) {
        return clienteRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }

    @Override
    public List<ClienteDto> modificaCliente(CriterioModificaClienteDto modificaDto) {
        log.debug("Entrato in modificaClienti");

        ClienteDto clienteModificato = modificaDto.getCliente();
        Cliente clienteCheSostituisce = new Cliente(clienteModificato);

        clienteRepository.save(clienteCheSostituisce);

        log.debug("Esce da modificaClienti");
        return cercaCliente(modificaDto.getFiltro());
    }

    @Override
    public List<ClienteDto> cancellaCliente(CriterioCancellazioneClienteDto dtoCancellazione) {
        log.debug("Entrato in cancellaCliente");
        // recupera l'id del cliente da rimuovere
        Long idDaRimuovere = dtoCancellazione.getIdCliente();

        // lo rimuove
        clienteRepository.deleteById(idDaRimuovere);

        // recupera i clienti rimasti
        List<ClienteDto> clientiRimasti;
        clientiRimasti = cercaCliente(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaCliente");
        return clientiRimasti;
    }

}
