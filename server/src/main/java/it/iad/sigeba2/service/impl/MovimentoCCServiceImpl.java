package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioInserimentoMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioModificaMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioMovimentoCCDto;
import it.iad.sigeba2.dto.MovimentoCCDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.MovimentoCC;
import it.iad.sigeba2.repository.MovimentoCCRepository;
import it.iad.sigeba2.service.MovimentoCCService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovimentoCCServiceImpl implements MovimentoCCService {

    @Autowired
    private MovimentoCCRepository movimentoCcRepository;

    @Override
    public List<MovimentoCCDto> cercaMovimentoCC(CriterioMovimentoCCDto criterio) throws SigebaException {
        log.debug("Entrato in cercaMovimentoCC");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Criterio di ricerca null non e' ammissibile");
        }
        String crit = criterio.getCriterio();
        List<MovimentoCC> listaMovimentoCC;
        List<MovimentoCCDto> risultatoMovCc = new ArrayList<>();

        String chiaveParziale = "%" + crit + "%";
        listaMovimentoCC = movimentoCcRepository.findByTipoLikeOrDescrizioneLike(chiaveParziale, chiaveParziale);

        for (MovimentoCC movimentocc : listaMovimentoCC) {
            risultatoMovCc.add(new MovimentoCCDto(movimentocc));
        }
        log.debug("Uscito da cerca MovimentoCC");
        return risultatoMovCc;
    }

    @Override
    public List<MovimentoCCDto> cancellaMovimentoCC(CriterioCancellazioneMovimentoCCDto dtoCancellazione) throws SigebaException {

        log.debug("Entrato in cancellaMovimentoCC");
        if (dtoCancellazione == null || dtoCancellazione.getIdMovimentoCC() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id da cancellare null!!");
        }
        Long idDaRimuovere = dtoCancellazione.getIdMovimentoCC();
        movimentoCcRepository.deleteById(idDaRimuovere);

        List<MovimentoCCDto> movimentoccRimasti;
        movimentoccRimasti = cercaMovimentoCC(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaMovimentoCC");
        return movimentoccRimasti;

    }

    @Override
    public List<MovimentoCCDto> modificaMovimentoCC(CriterioModificaMovimentoCCDto modifica) throws SigebaException {
        log.debug("Entrato in modificaMovimentoCC");
        if (modifica == null || modifica.getMovimentoCc() != null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("MovimentoCC da modificare null!!!");
        }
        MovimentoCCDto movimentoccModificato = modifica.getMovimentoCc();
        MovimentoCC filialeCheSostituisce = new MovimentoCC(movimentoccModificato);

        movimentoCcRepository.save(filialeCheSostituisce);

        log.debug("Esce da modificaMovimentoCC");
        return cercaMovimentoCC(modifica.getFiltro());
    }

    @Override
    public MovimentoCC leggiMovimentoCC(SimpleIdDto chiave) throws SigebaException {
        log.debug("Entrato in modificaMovimentoCC");
        if (chiave == null || chiave.getId() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id movimentocc da leggere null!!");
        }
        return movimentoCcRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }

    @Override
    public List<MovimentoCCDto> inserisciMovimentoCC(CriterioInserimentoMovimentoCCDto movimentoccDaInserireDto) throws SigebaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
