package it.iad.sigeba2;

import it.iad.sigeba2.dto.CriterioCancellazioneLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioInserimentoLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioLibrettoRisparmioDto;
import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.LibrettoRisparmio;
import it.iad.sigeba2.repository.LibrettoRisparmioRepository;
import it.iad.sigeba2.service.LibrettoRisparmioService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibrettoRisparmioTest {
    
    Random random = new Random();

    @Autowired
    LibrettoRisparmioService librettoRisparmioService;

    @Autowired
    LibrettoRisparmioRepository librettoRisparmioRepository;

    @Test
    public void testaCaricamentoLibrettoRisparmio() {
        cancellaDatiEsistenti();

        int numeroLibrettiRisparmioInseriti = creaLibrettoRisparmioDiTest();

        verificaLibrettiRisparmioInseriti(numeroLibrettiRisparmioInseriti);

        LibrettoRisparmio tipoScelto = scegliLibrettoRisparmioACaso();

        leggeEVerificaLetturaLibrettoRisparmio(tipoScelto);

        verificaRicercaPerChiaveParziale(numeroLibrettiRisparmioInseriti);

        verificaCancellazione(tipoScelto);
    }

    private void verificaCancellazione(LibrettoRisparmio tipoScelto) {
        Assertions.assertThrows(
                SigebaException.class,
                () -> librettoRisparmioService.cancellaLibrettoRisparmio(null));
        CriterioCancellazioneLibrettoRisparmioDto criterioCancellazioneLibrettoRisparmio = new CriterioCancellazioneLibrettoRisparmioDto();
        criterioCancellazioneLibrettoRisparmio.setIdLibrettoRisparmio(tipoScelto.getId());
        try {
            librettoRisparmioService.cancellaLibrettoRisparmio(criterioCancellazioneLibrettoRisparmio);
        } catch (SigebaException e) {
            Assertions.fail();
        }
        LibrettoRisparmio l = librettoRisparmioRepository.findById(tipoScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(l == null, "Trovato record cancellato");
    }

    private void verificaRicercaPerChiaveParziale(int numeroLibrettiRisparmioInseriti) {
        CriterioLibrettoRisparmioDto criterio = new CriterioLibrettoRisparmioDto();
        criterio.setCriterio("GG123");
        try {
            List<LibrettoRisparmioDto> librettiRisparmioTrovati = librettoRisparmioService.cercaLibrettoRisparmio(criterio);
            Assertions.assertTrue(librettiRisparmioTrovati.size() == 1);
            for (LibrettoRisparmioDto l : librettiRisparmioTrovati) {
                Assertions.assertTrue(l.getCodice().contains("GG123"));
            }

            criterio.setCriterio("");
            librettiRisparmioTrovati = librettoRisparmioService.cercaLibrettoRisparmio(criterio);
            Assertions.assertTrue(librettiRisparmioTrovati.size() == numeroLibrettiRisparmioInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private void leggeEVerificaLetturaLibrettoRisparmio(LibrettoRisparmio tipoScelto) {
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(tipoScelto.getId());
        try {
            LibrettoRisparmio l = librettoRisparmioService.leggiLibrettoRisparmio(sid);
            Assertions.assertTrue(l != null);
            Assertions.assertTrue(l.getCodice()
                    .equals(tipoScelto.getCodice())
                    && l.getMassimale()
                            .equals(tipoScelto.getMassimale()));
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private LibrettoRisparmio scegliLibrettoRisparmioACaso() {
        List<LibrettoRisparmio> libretti = librettoRisparmioRepository.findAll();
        int numLibretti = libretti.size();
        int numLibrettoPrescelto = random.nextInt(numLibretti);
        LibrettoRisparmio tipoLibrettoScelto = libretti.get(numLibrettoPrescelto);
        return tipoLibrettoScelto;
    }

    private void verificaLibrettiRisparmioInseriti(int numeroLibrettiRisparmioInseriti) {
        CriterioLibrettoRisparmioDto criterioTuttiILibretti = new CriterioLibrettoRisparmioDto("");
        try {
            List<LibrettoRisparmioDto> lista = librettoRisparmioService.cercaLibrettoRisparmio(criterioTuttiILibretti);
            Assertions.assertTrue(lista.size() == numeroLibrettiRisparmioInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private int creaLibrettoRisparmioDiTest() {
        int numeroLibrettiRisparmioInseriti = 0;
        try {
            LibrettoRisparmioDto librettoRisparmioDto = new LibrettoRisparmioDto();
            librettoRisparmioDto.setMassimale(5000D);
            librettoRisparmioDto.setCodice("GG123");
            librettoRisparmioDto.setId(null);
            CriterioInserimentoLibrettoRisparmioDto criterioInserimentoLibrettoRisparmio = new CriterioInserimentoLibrettoRisparmioDto(librettoRisparmioDto);
            librettoRisparmioService.inserisciLibrettoRisparmio(criterioInserimentoLibrettoRisparmio);
            numeroLibrettiRisparmioInseriti++;
            
            librettoRisparmioDto = new LibrettoRisparmioDto();
            librettoRisparmioDto.setMassimale(8000D);
            librettoRisparmioDto.setCodice("LL123");
            librettoRisparmioDto.setId(null);
            criterioInserimentoLibrettoRisparmio = new CriterioInserimentoLibrettoRisparmioDto(librettoRisparmioDto);
            librettoRisparmioService.inserisciLibrettoRisparmio(criterioInserimentoLibrettoRisparmio);
            numeroLibrettiRisparmioInseriti++;
            
            librettoRisparmioDto = new LibrettoRisparmioDto();
            librettoRisparmioDto.setMassimale(20000D);
            librettoRisparmioDto.setCodice("MM123");
            librettoRisparmioDto.setId(null);
            criterioInserimentoLibrettoRisparmio = new CriterioInserimentoLibrettoRisparmioDto(librettoRisparmioDto);
            librettoRisparmioService.inserisciLibrettoRisparmio(criterioInserimentoLibrettoRisparmio);
            numeroLibrettiRisparmioInseriti++;
            
            librettoRisparmioDto = new LibrettoRisparmioDto();
            librettoRisparmioDto.setMassimale(1000D);
            librettoRisparmioDto.setCodice("II123");
            librettoRisparmioDto.setId(null);
            criterioInserimentoLibrettoRisparmio = new CriterioInserimentoLibrettoRisparmioDto(librettoRisparmioDto);
            librettoRisparmioService.inserisciLibrettoRisparmio(criterioInserimentoLibrettoRisparmio);
            numeroLibrettiRisparmioInseriti++;
            
            librettoRisparmioDto = new LibrettoRisparmioDto();
            librettoRisparmioDto.setMassimale(500000D);
            librettoRisparmioDto.setCodice("OO123");
            librettoRisparmioDto.setId(null);
            criterioInserimentoLibrettoRisparmio = new CriterioInserimentoLibrettoRisparmioDto(librettoRisparmioDto);
            librettoRisparmioService.inserisciLibrettoRisparmio(criterioInserimentoLibrettoRisparmio);
            numeroLibrettiRisparmioInseriti++;
           
        } catch (SigebaException e) {
            Assertions.fail();
        }
        return numeroLibrettiRisparmioInseriti;
    }

    private void cancellaDatiEsistenti() {
        librettoRisparmioRepository.deleteAll();
    }
}
