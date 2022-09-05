package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioInserimentoLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioModificaLibrettoRisparmioDto;
import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.LibrettoRisparmio;
import it.iad.sigeba2.repository.LibrettoRisparmioRepository;
import it.iad.sigeba2.service.LibrettoRisparmioService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LibrettoRisparmioServiceImpl implements LibrettoRisparmioService{

    @Autowired
    private LibrettoRisparmioRepository librettoRisparmioRepository;
    
    @Override
    public List<LibrettoRisparmioDto> cercaLibrettoRisparmio(CriterioLibrettoRisparmioDto criterio) throws SigebaException {
        log.debug("Entrato in cercaLibrettoRisparmio");
        if (criterio == null || criterio.getCriterio() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Criterio di ricerca null non è ammissibile");
        }
        String crit = criterio.getCriterio();
        List<LibrettoRisparmioDto> listaLibretti = new ArrayList<>();

        //Trova LibrettoRisparmio
        String chiaveParziale = "%" + crit + "%";
        List<LibrettoRisparmio> listaLibrettoRisparmio = librettoRisparmioRepository.findByCodiceLike(chiaveParziale);

        //li converte in lista Dto
        for (LibrettoRisparmio librettoRisparmio : listaLibrettoRisparmio) {
            listaLibretti.add(new LibrettoRisparmioDto(librettoRisparmio));
        }
        log.debug("In uscita da cercaLibrettoRisparmio");
        return listaLibretti;
    }

    @Override
    public List<LibrettoRisparmioDto> inserisciLibrettoRisparmio(CriterioInserimentoLibrettoRisparmioDto librettoRisparmioDaInserireDto) throws SigebaException {
        log.debug("Entra in inserisciLibrettoRisparmio");

        if (librettoRisparmioDaInserireDto == null || librettoRisparmioDaInserireDto.getLibrettoRisparmio() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("LibrettoRisparmio da inserire null!");
        }

        LibrettoRisparmio librettoRisparmio = new LibrettoRisparmio(librettoRisparmioDaInserireDto.getLibrettoRisparmio());

        librettoRisparmioRepository.save(librettoRisparmio);

        log.debug("In uscita da inserisciLibrettoRisparmio");
        List<LibrettoRisparmioDto> librettoRisparmioDto = cercaLibrettoRisparmio(librettoRisparmioDaInserireDto.getFiltro());
        return librettoRisparmioDto; 
    }

    @Override
    public List<LibrettoRisparmioDto> cancellaLibrettoRisparmio(CriterioCancellazioneLibrettoRisparmioDto dtoCancellazione) throws SigebaException {
      log.debug("Entrato in cancellaLibrettoRisparmio");
        if (dtoCancellazione == null || dtoCancellazione.getIdLibrettoRisparmio()== null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id da cancellare null!");
        }
        // recupera l'id del librettoRisparmio da rimuovere
        Long idDaRimuovere = dtoCancellazione.getIdLibrettoRisparmio();
        // lo rimuove
        librettoRisparmioRepository.deleteById(idDaRimuovere);

        // recupera i librettoRisparmio rimasti
        List<LibrettoRisparmioDto> librettoRisparmioRimasti;
        librettoRisparmioRimasti = cercaLibrettoRisparmio(dtoCancellazione.getFiltro());
        log.debug("In uscita da cancellaLibrettoRisparmio");
        return librettoRisparmioRimasti;
    }

    @Override
    public List<LibrettoRisparmioDto> modificaLibrettoRisparmio(CriterioModificaLibrettoRisparmioDto modifica) throws SigebaException {
        log.debug("Entrato in modificaLibrettoRisparmio");
        if (modifica == null || modifica.getLibrettoRisparmio()!= null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("LibrettoRisparmio da modificare null!");
        }
        LibrettoRisparmioDto librettoRisparmioModificato = modifica.getLibrettoRisparmio();
        LibrettoRisparmio librettoRisparmioCheSostituisce = new LibrettoRisparmio(librettoRisparmioModificato);

        librettoRisparmioRepository.save(librettoRisparmioCheSostituisce);

        log.debug("In uscita da modificaLibrettoRisparmio");
        return cercaLibrettoRisparmio(modifica.getFiltro());
    }

    @Override
    public LibrettoRisparmio leggiLibrettoRisparmio(SimpleIdDto librettoRisparmioDto) throws SigebaException {
        log.debug("Entrato in leggiLibrettoRisparmio");
        if (librettoRisparmioDto == null || librettoRisparmioDto.getId() == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.UTENTE_NON_AUTORIZZATO);
            throw new SigebaException("Id librettoRisparmio da leggere null!");
        }
        return librettoRisparmioRepository.findById(librettoRisparmioDto.getId())
                .map(cx -> cx)
                .orElse(null);
    } 
    
}
    
    
