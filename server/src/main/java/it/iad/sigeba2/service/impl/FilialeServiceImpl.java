package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneFilialeDto;
import it.iad.sigeba2.dto.CriterioInserimentoFilialeDto;
import it.iad.sigeba2.dto.CriterioModificaFilialeDto;
import it.iad.sigeba2.dto.CriterioFilialeDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.FilialeDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.Filiale;
import it.iad.sigeba2.repository.FilialeRepository;
import it.iad.sigeba2.service.FilialeService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FilialeServiceImpl implements FilialeService {

    @Autowired
    private FilialeRepository filialeRepository;

    @Override
    public List<FilialeDto> cercaFiliale(CriterioFilialeDto criterio) throws SigebaException {
        log.debug("Entrato in cercaFiliale");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Criterio di ricerca null non è ammissibile");
        }
        String crit = criterio.getCriterio();
        List<FilialeDto> listaFiliali = new ArrayList<>();
        
        List<Filiale> listaFiliale;
        if (crit.isBlank()) {
            listaFiliale = filialeRepository.findAll();
        } else {
            String chiaveParziale = "%" + crit + "%";
            listaFiliale = filialeRepository.findByNomeLikeOrCodiceLike(chiaveParziale, chiaveParziale);
        }

        for (Filiale filiale : listaFiliale) {
            listaFiliali.add(new FilialeDto(filiale));
        }
        log.debug("Uscito da cercaFiliale");
        return listaFiliali;
    }

    @Override
    public List<FilialeDto> inserisciFiliale(CriterioInserimentoFilialeDto filialeDaInserireDto) throws SigebaException {
        log.debug("Entra in inserisciFiliale");

        if (filialeDaInserireDto == null || filialeDaInserireDto.getFiliale() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Filiale da inserire null!!!");
        }

        Filiale filiale = new Filiale(filialeDaInserireDto.getFiliale());

        filialeRepository.save(filiale);

        log.debug("Esci da inserisciFiliale");
        List<FilialeDto> filialeDto = cercaFiliale(filialeDaInserireDto.getFiltro());
        return filialeDto;
    }

    @Override
    public List<FilialeDto> cancellaFiliale(CriterioCancellazioneFilialeDto dtoCancellazione) throws SigebaException {

        log.debug("Entrato in cancellaFiliale");
        if (dtoCancellazione == null || dtoCancellazione.getIdFiliale() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id da cancellare null!!");
        }
        Long idDaRimuovere = dtoCancellazione.getIdFiliale();
        filialeRepository.deleteById(idDaRimuovere);

        List<FilialeDto> filialiRimaste;
        filialiRimaste = cercaFiliale(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaFiliale");
        return filialiRimaste;

    }

    @Override
    public List<FilialeDto> modificaFiliale(CriterioModificaFilialeDto modifica) throws SigebaException {
        log.debug("Entrato in modificaFiliale");
        if (modifica == null || modifica.getFiliale() != null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Filiale da modificare null!!!");
        }
        FilialeDto filialeModificata = modifica.getFiliale();
        Filiale filialeCheSostituisce = new Filiale(filialeModificata);

        filialeRepository.save(filialeCheSostituisce);

        log.debug("Esce da modificaFiliale");
        return cercaFiliale(modifica.getFiltro());
    }

    @Override

    public Filiale leggiFiliale(SimpleIdDto chiave) throws SigebaException {
        log.debug("Entrato in modificaFiliale");
        if (chiave == null || chiave.getId() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id filiale da leggere null!!");
        }
        return filialeRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }

}
