package it.iad.sigeba2;

import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.repository.ClienteRepository;
import it.iad.sigeba2.repository.ContoCorrenteRepository;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RelazioniTest {

    private static final int NUMERO_CLIENTI = 100;
    private static final int NUMERO_CONTI_CORRENTI = 200;

    Random random = new Random();
    @Autowired
    AnagraficaClientiService anagraficaClientiService;

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContoCorrenteRepository contoCorrenteRepository;

    @Test
    public void testaClienteContoCorrente() {
        cancellaDatiEsistenti();

        creaClienti();
        creaContiCorrenti();

        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<Cliente> listaClienti = clienteRepository.findAll();

        ContoCorrente cc = listaConti.get(0);
        Cliente cli = listaClienti.get(0);

        cc.setCliente(cli);
        contoCorrenteRepository.save(cc);
    }

    private void creaClienti() {
        // creazione clienti
        for (int i = 0; i < NUMERO_CLIENTI; i++) {
            String n = "Nome" + i;
            String c = "Cognome" + i;
            String cf = "CF" + i;
            clienteRepository.save(new Cliente(n, c, cf));
        }
    }

    private void creaContiCorrenti() {
        // creazione clienti
        for (int i = 0; i < NUMERO_CONTI_CORRENTI; i++) {
            String iban = "IBAN" + i;
            double fido = 0.0;
            contoCorrenteRepository.save(new ContoCorrente(iban, fido));
        }
    }

    private void cancellaDatiEsistenti() {
        //Cancello tutti i dati
        contoCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();
    }
}
