package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
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
    public List<ClienteDto> cercaCliente(CriterioClienteDto criterio) throws SigebaException {

        log.debug("Entrato in cercaCliente");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Criterio di ricerca null non ammissibile");
        }
        String crit = criterio.getCriterio();
        List<ClienteDto> listaClienti = new ArrayList<>();

        // trova i clienti
        String chiaveParziale = "%" + crit + "%";
        List<Cliente> clienti = clienteRepository.findByNomeLikeOrCognomeLikeOrCodiceFiscaleLike(chiaveParziale, chiaveParziale, chiaveParziale);

        // li converte in lista di DTO
        for (Cliente cliente : clienti) {
            listaClienti.add(new ClienteDto(cliente));
        }
        log.debug("Uscito da cercaCliente");
        return listaClienti;
    }

    @Override
    public List<ClienteDto> inserisciCliente(CriterioInserimentoClienteDto clienteDaInserireDto) throws SigebaException {
        log.debug("Entra in inserisciCliente");
        if (clienteDaInserireDto == null || clienteDaInserireDto.getCliente() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Cliente da inserire null!!!");
        }

        Cliente cliente = new Cliente(clienteDaInserireDto.getCliente());

        clienteRepository.save(cliente);

        log.debug("Esci da inserisciCliente");
        List<ClienteDto> clienteDto = cercaCliente(clienteDaInserireDto.getFiltro());
        return clienteDto;
    }

    @Override
    public Cliente leggiCliente(SimpleIdDto chiave) throws SigebaException {
        log.debug("Entrato in modifica cliente");
        if (chiave == null || chiave.getId() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id cliente da leggere null!!");
        }
        return clienteRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }

    @Override
    public List<ClienteDto> modificaCliente(CriterioModificaClienteDto modifica) throws SigebaException {
        log.debug("Entrato in modificaClienti");
        if (modifica == null || modifica.getCliente() != null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Cliente da modificare null!!!");
        }

        ClienteDto clienteModificato = modifica.getCliente();
        Cliente clienteCheSostituisce = new Cliente(clienteModificato);

        clienteRepository.save(clienteCheSostituisce);

        log.debug("Esce da modificaClienti");
        return cercaCliente(modifica.getFiltro());
    }

    @Override
    public List<ClienteDto> cancellaCliente(CriterioCancellazioneClienteDto dtoCancellazione) throws SigebaException {

        log.debug("Entrato in cancellaCliente");
        if (dtoCancellazione == null || dtoCancellazione.getIdCliente() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id da cancellare null!!");
        }

        Long idDaRimuovere = dtoCancellazione.getIdCliente();

        clienteRepository.deleteById(idDaRimuovere);

        // recupera i clienti rimasti
        List<ClienteDto> clientiRimasti;
        clientiRimasti = cercaCliente(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaCliente");
        return clientiRimasti;
    }

}
