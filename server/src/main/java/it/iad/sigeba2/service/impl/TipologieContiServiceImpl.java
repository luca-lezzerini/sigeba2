package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioInserimentoTipoContoDto;
import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.repository.TipoContoRepository;
import it.iad.sigeba2.service.TipologieContiService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TipologieContiServiceImpl implements TipologieContiService {

    @Autowired
    private TipoContoRepository tipoContoRepository;

    @Override
    public List<TipoContoDto> cercaTipoConto(CriterioTipoContoDto criterio) throws SigebaException {
        log.debug("Entrato in cercaTipoConto");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage("Criterio di ricerca null non consentito", 
                    "Inserire un criterio di ricerca vamido", GravitaStatoEnum.CRITICA);
            throw new SigebaException("Criterio di ricerca null non è ammissibile");
        }
        String crit = criterio.getCriterio();
        List<TipoContoDto> listaConti = new ArrayList<>();

        //Trova TipoConto
        String chiaveParziale = "%" + crit + "%";
        List<TipoConto> listaTipoConto = tipoContoRepository.findByNomeLikeOrDescrizioneLike(chiaveParziale, chiaveParziale);

        //li converte in lista Dto
        for (TipoConto tipoConto : listaTipoConto) {
            listaConti.add(new TipoContoDto(tipoConto));
        }
        log.debug("Uscito da cercaTipoConto");
        return listaConti;
    }

    @Override
    public List<TipoContoDto> inserisciTipoConto(CriterioInserimentoTipoContoDto contoDaInserireDto) throws SigebaException {
        log.debug("Entra in inserisciTipoConto");

        if (contoDaInserireDto == null || contoDaInserireDto.getTipoConto() == null) {
            throw new SigebaException("Tipo conto da inserire null!!!");
        }

        TipoConto tipoConto = new TipoConto(contoDaInserireDto.getTipoConto());

        tipoContoRepository.save(tipoConto);

        log.debug("Esci da inserisciTipoConto");
        List<TipoContoDto> tipoContoDto = cercaTipoConto(contoDaInserireDto.getFiltro());
        return tipoContoDto;
    }

    @Override
    public List<TipoContoDto> cancellaTipoConto(CriterioCancellazioneTipoContoDto dtoCancellazione) throws SigebaException {

        log.debug("Entrato in cancellaTipoConto");
        if (dtoCancellazione == null || dtoCancellazione.getIdTipoConto() == null) {
            throw new SigebaException("Oggetto da canellare null!!");
        }
        // recupera l'id del tipoConto da rimuovere
        Long idDaRimuovere = dtoCancellazione.getIdTipoConto();
        // lo rimuove
        tipoContoRepository.deleteById(idDaRimuovere);

        // recupera i tipoConto rimasti
        List<TipoContoDto> tipoContoRimasti;
        tipoContoRimasti = cercaTipoConto(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaTipoConto");
        return tipoContoRimasti;

    }

    @Override
    public List<TipoContoDto> modificaTipoConto(CriterioModificaTipoContoDto modifica) throws SigebaException {
        log.debug("Entrato in modificaTipoConto");
        if (modifica == null || modifica.getTipoConto() != null) {
            throw new SigebaException("Tipo conto da modificare null!!!");
        }
        TipoContoDto tipoContoModificato = modifica.getTipoConto();
        TipoConto tipoContoCheSostituisce = new TipoConto(tipoContoModificato);

        tipoContoRepository.save(tipoContoCheSostituisce);

        log.debug("Esce da modificaTipoConto");
        return cercaTipoConto(modifica.getFiltro());
    }

    @Override

    public TipoConto leggiTipoConto(SimpleIdDto chiave) throws SigebaException {
        log.debug("Entrato in modificaTipoConto");
        if (chiave == null || chiave.getId() == null) {
            throw new SigebaException("Id tipo conto da leggere null!!");
        }
        return tipoContoRepository.findById(chiave.getId())
                .map(cx -> cx)
                .orElse(null);
    }

}
