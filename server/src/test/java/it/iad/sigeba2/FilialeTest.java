package it.iad.sigeba2;

import it.iad.sigeba2.dto.FilialeDto;
import it.iad.sigeba2.dto.CriterioCancellazioneFilialeDto;
import it.iad.sigeba2.dto.CriterioFilialeDto;
import it.iad.sigeba2.dto.CriterioInserimentoFilialeDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.Filiale;
import it.iad.sigeba2.repository.FilialeRepository;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilialeTest {

    Random random = new Random();

    @Autowired
    FilialeService filialeService;

    @Autowired
    FilialeRepository filialeRepository;

    @Test
    public void testaCaricamentoFiliale() {
        cancellaDatiEsistenti();

        int numeroFilialiInserite = creaFilialeDiTest();

        verificaNumeroFilialiInserite(numeroFilialiInserite);

        Filiale tipoScelto = scegliFilialeACaso();

        leggeEVerificaLetturaFiliale(tipoScelto);

        verificaRicercaPerChiaveParziale(numeroFilialiInserite);

        verificaCancellazione(tipoScelto);
    }

    private void verificaCancellazione(Filiale tipoScelto) {
        Assertions.assertThrows(
                SigebaException.class,
                () -> filialeService.cancellaFiliale(null));
        CriterioCancellazioneFilialeDto criterioCancellazioneFiliale = new CriterioCancellazioneFilialeDto();
        criterioCancellazioneFiliale.setIdFiliale(tipoScelto.getId());
        try {
            filialeService.cancellaFiliale(criterioCancellazioneFiliale);
        } catch (SigebaException e) {
            Assertions.fail();
        }
        Filiale f = filialeRepository.findById(tipoScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(f == null, "Trovato record cancellato");
    }

    private void verificaRicercaPerChiaveParziale(int numeroFilialiInserite) {
        CriterioFilialeDto criterio = new CriterioFilialeDto();
        criterio.setCriterio("RM1234");
        try {
            List<FilialeDto> filialiTrovate = filialeService.cercaFiliale(criterio);
            Assertions.assertTrue(filialiTrovate.size() == 1);
            for (FilialeDto f : filialiTrovate) {
                Assertions.assertTrue(f.getCodice().contains("RM1234"));
            }

            criterio.setCriterio("");
            filialiTrovate = filialeService.cercaFiliale(criterio);
            Assertions.assertTrue(filialiTrovate.size() == numeroFilialiInserite);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private void leggeEVerificaLetturaFiliale(Filiale tipoScelto) {
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(tipoScelto.getId());
        try {
            Filiale f = filialeService.leggiFiliale(sid);
            Assertions.assertTrue(f != null);
            Assertions.assertTrue(f.getNome()
                    .equals(tipoScelto.getNome())
                    && f.getCodice()
                            .equals(tipoScelto.getCodice()));
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private Filiale scegliFilialeACaso() {
        List<Filiale> filiali = filialeRepository.findAll();
        int numFiliali = filiali.size();
        int numFilialePrescelto = random.nextInt(numFiliali);
        Filiale tipoFilialeScelto = filiali.get(numFilialePrescelto);
        return tipoFilialeScelto;
    }

    private void verificaNumeroFilialiInserite(int numeroFilialiInserite) {
        CriterioFilialeDto criterioTutteLeFiliali = new CriterioFilialeDto("");
        try {
            List<FilialeDto> lista = filialeService.cercaFiliale(criterioTutteLeFiliali);
            Assertions.assertTrue(lista.size() == numeroFilialiInserite);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private int creaFilialeDiTest() {
        int numeroFilialiInserite = 0;
        try {
            FilialeDto filialeDto = new FilialeDto();
            filialeDto.setNome("Roma Trastevere");
            filialeDto.setCodice("RM1234");
            filialeDto.setId(null);
            CriterioInserimentoFilialeDto criterioInserimentoFiliale = new CriterioInserimentoFilialeDto(filialeDto);
            filialeService.inserisciFiliale(criterioInserimentoFiliale);
            numeroFilialiInserite++;
            
            filialeDto = new FilialeDto();
            filialeDto.setNome("Roma Monteverde");
            filialeDto.setCodice("RM4567");
            filialeDto.setId(null);
            criterioInserimentoFiliale = new CriterioInserimentoFilialeDto(filialeDto);
            filialeService.inserisciFiliale(criterioInserimentoFiliale);
            numeroFilialiInserite++;
            
            filialeDto = new FilialeDto();
            filialeDto.setNome("Roma Boccea");
            filialeDto.setCodice("RM5486");
            filialeDto.setId(null);
            criterioInserimentoFiliale = new CriterioInserimentoFilialeDto(filialeDto);
            filialeService.inserisciFiliale(criterioInserimentoFiliale);
            numeroFilialiInserite++;
            
            filialeDto = new FilialeDto();
            filialeDto.setNome("Roma Casilina");
            filialeDto.setCodice("RM3647");
            filialeDto.setId(null);
            criterioInserimentoFiliale = new CriterioInserimentoFilialeDto(filialeDto);
            filialeService.inserisciFiliale(criterioInserimentoFiliale);
            numeroFilialiInserite++;
            
            filialeDto = new FilialeDto();
            filialeDto.setNome("Roma Trionfale");
            filialeDto.setCodice("RM9563");
            filialeDto.setId(null);
            criterioInserimentoFiliale = new CriterioInserimentoFilialeDto(filialeDto);
            filialeService.inserisciFiliale(criterioInserimentoFiliale);
            numeroFilialiInserite++;
           
        } catch (SigebaException e) {
            Assertions.fail();
        }
        return numeroFilialiInserite;
    }

    private void cancellaDatiEsistenti() {
        filialeRepository.deleteAll();
    }
}
