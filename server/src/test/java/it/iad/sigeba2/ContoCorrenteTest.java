package it.iad.sigeba2;

import it.iad.sigeba2.dto.ContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioInserimentoContoCorrenteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.repository.ContoCorrenteRepository;
import it.iad.sigeba2.service.ContoCorrenteService;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ContoCorrenteTest {

    private static final int NUMERO_CONTI_GENERATI = 100;

    Random random = new Random();

    @Autowired
    ContoCorrenteService contoCorrenteService;

    @Autowired
    ContoCorrenteRepository contoCorrenteRepository;

    @Test
    public void testaCaricamentoContoCorrente() {
        cancellaDatiEsistenti();

        int numeroContiCorrentiInseriti = creaContoCorrenteDiTest();

        verificaNumeroContoCorrenteInseriti(numeroContiCorrentiInseriti);

        ContoCorrente tipoScelto = scegliContoCorrenteACaso();

        leggeEVerificaLetturaContoCorrente(tipoScelto);

        verificaRicercaPerChiaveParziale();

        verificaCancellazione(tipoScelto);
    }

    private void verificaCancellazione(ContoCorrente tipoScelto) {
//Verifico cancellaContoCorrente
        Assertions.assertThrows(
                SigebaException.class,
                () -> contoCorrenteService.cancellaContoCorrente(null));
        CriterioCancellazioneContoCorrenteDto criterioCancellazioneContoCorrenete = new CriterioCancellazioneContoCorrenteDto();
        criterioCancellazioneContoCorrenete.setIdContoCorrente(tipoScelto.getId());
        try {
            contoCorrenteService.cancellaContoCorrente(criterioCancellazioneContoCorrenete);
        } catch (SigebaException e) {
            Assertions.fail();
        }
        ContoCorrente cc = contoCorrenteRepository.findById(tipoScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(cc == null, "Trovato record cancellato");
    }

    private void verificaRicercaPerChiaveParziale() {
        //verifico ricerca conto corrente
        List<ContoCorrente> listaTuttiCc = contoCorrenteRepository.findAll();
        int ccScelto = random.nextInt(listaTuttiCc.size());
        String k = listaTuttiCc.get(ccScelto).getIban();
        CriterioContoCorrenteDto criterio = new CriterioContoCorrenteDto(k);
        try {
            List<ContoCorrenteDto> contiCorrentiTrovati = contoCorrenteService.cercaContoCorrente(criterio);
            Assertions.assertTrue(contiCorrentiTrovati.size() == 1);
            for (ContoCorrenteDto cc : contiCorrentiTrovati) {
                Assertions.assertTrue(cc.getIban().contains(k));
            }
            criterio.setCriterio("");
            contiCorrentiTrovati = contoCorrenteService.cercaContoCorrente(criterio);
            Assertions.assertTrue(contiCorrentiTrovati.size() == listaTuttiCc.size());
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private void leggeEVerificaLetturaContoCorrente(ContoCorrente tipoScelto) {
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(tipoScelto.getId());
        try {
            ContoCorrente cc = contoCorrenteService.leggiContoCorrente(sid);
            Assertions.assertTrue(cc != null);
            Assertions.assertTrue(cc.getFido()
                    .equals(tipoScelto.getFido())
                    && cc.getIban()
                            .equals(tipoScelto.getIban()));
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private ContoCorrente scegliContoCorrenteACaso() {
// Leggo tutti i conti correnti e ne scelgo uno a caso dalla lista appena letta
        List<ContoCorrente> contiCorrenti = contoCorrenteRepository.findAll();
        int numConti = contiCorrenti.size();
        int numContoCorrentePrescelto = random.nextInt(numConti);
        log.debug("Il conto corrente prescelto e' il numero " + numContoCorrentePrescelto);
        ContoCorrente tipoContoScelto = contiCorrenti.get(numContoCorrentePrescelto);
        return tipoContoScelto;
    }

    private void verificaNumeroContoCorrenteInseriti(int numeroContiCorrentiInseriti) {
// Vverifico che i conti correnti siano 5
        CriterioContoCorrenteDto criterioTuttiIContiCorrenti = new CriterioContoCorrenteDto("");
        try {
            List<ContoCorrenteDto> lista = contoCorrenteService.cercaContoCorrente(criterioTuttiIContiCorrenti);
            Assertions.assertTrue(lista.size() == numeroContiCorrentiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private int creaContoCorrenteDiTest() {
// creo i conti correnti di test
        int numeroContiCorrentiInseriti = 0;
        try {
            ContoCorrenteDto contoCorrenteDto;

            for (int i = 0; i < NUMERO_CONTI_GENERATI; i++) {
                String ib = "IBANXYZ" + i;
                Double fi = 10.0 * i;
                contoCorrenteDto = new ContoCorrenteDto(ib, fi);
                CriterioInserimentoContoCorrenteDto criterioInserimentoConto = new CriterioInserimentoContoCorrenteDto(contoCorrenteDto);
                contoCorrenteService.inserisciContoCorrente(criterioInserimentoConto);
                numeroContiCorrentiInseriti++;
            }
        } catch (SigebaException e) {
            Assertions.fail();
        }
        return numeroContiCorrentiInseriti;
    }

    private void cancellaDatiEsistenti() {
        // cancello tutti i conti correnti
        contoCorrenteRepository.deleteAll();
    }
}
