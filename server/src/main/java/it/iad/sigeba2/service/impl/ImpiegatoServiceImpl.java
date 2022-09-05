package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneImpiegatoDto;
import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioImpiegatoDto;
import it.iad.sigeba2.dto.CriterioInserimentoImpiegatoDto;
import it.iad.sigeba2.dto.CriterioModificaImpiegatoDto;

import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.ImpiegatoDto;

import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.Impiegato;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.repository.ImpiegatoRepository;
import it.iad.sigeba2.repository.TipoContoRepository;
import it.iad.sigeba2.service.ImpiegatoService;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {

    @Autowired
    private ImpiegatoRepository impiegatoRepository;

    @Override
    public List<ImpiegatoDto> cercaImpiegato(CriterioImpiegatoDto criterio) throws SigebaException {
        log.debug("Entrato in cercaImpiegato");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Criterio di ricerca null non è ammissibile");
        }
        String crit = criterio.getCriterio();
        List<ImpiegatoDto> listaImpiegati = new ArrayList<>();

        //Trova Impiegato
        String chiaveParziale = "%" + crit + "%";
        List<Impiegato> listaImpiegato = impiegatoRepository.findByNomeLikeOrCognomeLikeOrMatricolaLike (chiaveParziale, chiaveParziale, chiaveParziale);

        //li converte in lista Dto
        for (Impiegato impiegato : listaImpiegato) {
            listaImpiegati.add(new ImpiegatoDto(impiegato));
        }
        log.debug("Uscito da cercaTipoConto");
        return listaImpiegati;
    }

    @Override
    public List<ImpiegatoDto> inserisciImpiegato(CriterioInserimentoImpiegatoDto impiegatoDaInserireDto) throws SigebaException {
        log.debug("Entra in inserisciTipoConto");

        if (impiegatoDaInserireDto == null || impiegatoDaInserireDto.getImpiegato()== null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Tipo conto da inserire null!!!");
        }

        Impiegato impiegato = new Impiegato(impiegatoDaInserireDto.getImpiegato());

        impiegatoRepository.save(impiegato);

        log.debug("Esci da inserisciTipoConto");
        List<ImpiegatoDto> tipoContoDto = cercaImpiegato(impiegatoDaInserireDto.getFiltro());
        return tipoContoDto;
    }

    @Override
    public List<ImpiegatoDto> cancellaImpiegato(CriterioCancellazioneImpiegatoDto dtoCancellazione) throws SigebaException {

        log.debug("Entrato in cancellaTipoConto");
        if (dtoCancellazione == null || dtoCancellazione.getIdImpiegato()== null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id da cancellare null!!");
        }
        // recupera l'id del tipoConto da rimuovere
        Long idDaRimuovere = dtoCancellazione.getIdImpiegato();
        // lo rimuove
        impiegatoRepository.deleteById(idDaRimuovere);

        // recupera i tipoConto rimasti
        List<ImpiegatoDto> impiegatiRimasti;
        impiegatiRimasti = cercaImpiegato(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaTipoConto");
        return impiegatiRimasti;

    }

    @Override
    public List<ImpiegatoDto> modificaImpiegato(CriterioModificaImpiegatoDto modifica) throws SigebaException {
        log.debug("Entrato in modificaImpiegato");
        if (modifica == null || modifica.getImpiegato() != null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Tipo conto da modificare null!!!");
        }
        ImpiegatoDto impiegatoModificato = modifica.getImpiegato();
        Impiegato impiegatoCheSostituisce = new Impiegato(impiegatoModificato);

        impiegatoRepository.save(impiegatoCheSostituisce);

        log.debug("Esce da modificaTipoConto");
        return cercaImpiegato(modifica.getFiltro());
    }

    @Override

    public Impiegato leggiImpiegato(SimpleIdDto chiave) throws SigebaException {
        log.debug("Entrato in modificaTipoConto");
        if (chiave == null || chiave.getId() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id tipo conto da leggere null!!");
        }
        return impiegatoRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }

}
