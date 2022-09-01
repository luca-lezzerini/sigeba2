package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.ContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioInserimentoContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioModificaContoCorrenteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.repository.ContoCorrenteRepository;
import it.iad.sigeba2.service.ContoCorrenteService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContoCorrenteServiceImpl implements ContoCorrenteService {

    @Autowired
    private ContoCorrenteRepository contoCorrenteRepository;

    @Override
    public List<ContoCorrenteDto> cercaContoCorrente(CriterioContoCorrenteDto criterio) throws SigebaException {
        log.debug("Entrato in cercaContoCorrente");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage("Criterio di ricerca null non consentito",
                    "Inserire un criterio di ricerca valido", GravitaStatoEnum.CRITICA);
            throw new SigebaException("Criterio di ricerca null non e' ammissibile");
        }
        String crit = criterio.getCriterio();
        List<ContoCorrenteDto> listaContiCorrenti = new ArrayList<>();

        //Trova ContoCorrente
        List<ContoCorrente> listaConti;
        if (crit.isBlank()) {
            listaConti = contoCorrenteRepository.findAll();
        } else {
            String chiaveParziale = "%" + crit + "%";
            listaConti = contoCorrenteRepository.findByIbanLike(chiaveParziale);
        }

        //li converte il lista Dto
        for (ContoCorrente contoCorrente : listaConti) {
            listaContiCorrenti.add(new ContoCorrenteDto(contoCorrente));
        }
        log.debug("Uscito da cerca ContoCorrente");
        return listaContiCorrenti;
    }

    @Override
    public List<ContoCorrenteDto> inserisciContoCorrente(CriterioInserimentoContoCorrenteDto contoCorrenteDaInserireDto) throws SigebaException {
        log.debug("Entra in inserisciContoCorrente");

        if (contoCorrenteDaInserireDto == null || contoCorrenteDaInserireDto.getContoCorrente() == null) {
            SigebaStateCollector.addStatusMessage("Conto Corrente null non consentito",
                    "Inserire un Conto Corrente valido", GravitaStatoEnum.CRITICA);
            throw new SigebaException("Conto Corrente da inserire null!!!");
        }

        ContoCorrente contoCorrente = new ContoCorrente(contoCorrenteDaInserireDto.getContoCorrente());

        contoCorrenteRepository.save(contoCorrente);

        log.debug("Esci da inserisciContoCorrente");
        List<ContoCorrenteDto> contoCorrenteDto = cercaContoCorrente(contoCorrenteDaInserireDto.getFiltro());
        return contoCorrenteDto;
    }

    @Override
    public List<ContoCorrenteDto> cancellaContoCorrente(CriterioCancellazioneContoCorrenteDto dtoCancellazione) throws SigebaException {

        log.debug("Entrato in Canella Conto Corrente");
        if (dtoCancellazione == null || dtoCancellazione.getIdContoCorrente() == null) {
            SigebaStateCollector.addStatusMessage("Id Conto da cancellare null non consentito",
                    "Inserire un Id conto valido", GravitaStatoEnum.CRITICA);
            throw new SigebaException("Id Conto da cancellare null!!");
        }
        //recupere l'Id del Conto Corrente da rimuovere
        Long idContoDaRimuovere = dtoCancellazione.getIdContoCorrente();
        //lo rimuove
        contoCorrenteRepository.deleteById(idContoDaRimuovere);

        //recupera i conti correnti rimasti
        List<ContoCorrenteDto> ContiCorrentiRimasti;
        ContiCorrentiRimasti = cercaContoCorrente(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancella conto corrente");
        return ContiCorrentiRimasti;
    }

    @Override
    public List<ContoCorrenteDto> modificaContoCorrente(CriterioModificaContoCorrenteDto modifica) throws SigebaException {

        log.debug("Entrato in modifica Conto Corrente");
        if (modifica == null || modifica.getContoCorrente() != null) {
            SigebaStateCollector.addStatusMessage("Tipo Conto Corrente da modificare null non consentito",
                    "Inserire tipo conto corrente valido", GravitaStatoEnum.CRITICA);
            throw new SecurityException("Tipo Conto Corrente da modificare null!!");
        }
        ContoCorrenteDto contoCorrenteModificato = modifica.getContoCorrente();
        ContoCorrente contoCorrenteCheSostituisce = new ContoCorrente(contoCorrenteModificato);

        contoCorrenteRepository.save(contoCorrenteCheSostituisce);

        log.debug("Esce da modificaContoCorrente");
        return cercaContoCorrente(modifica.getFiltro());
    }

    @Override
    public ContoCorrente leggiContoCorrente(SimpleIdDto chiave) throws SigebaException {
        log.debug("Entrato in modificaContoCorrente");
        if (chiave == null || chiave.getId() == null) {
            SigebaStateCollector.addStatusMessage("Id da leggere null non consentito",
                    "Inserire Id Conto Corrente valido", GravitaStatoEnum.CRITICA);
            throw new SigebaException("Id Conto Corrrente null!!");
        }
        return contoCorrenteRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }
}
