package it.iad.sigeba2;

import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.model.Filiale;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.repository.ClienteRepository;
import it.iad.sigeba2.repository.ContoCorrenteRepository;
import it.iad.sigeba2.repository.FilialeRepository;
import it.iad.sigeba2.repository.TipoContoRepository;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RelazioniTest {

    private static final int NUMERO_CLIENTI = 100;
    private static final int NUMERO_CONTI_CORRENTI = 200;
    private static final int NUMERO_FILIALI = 10;
    private static final int NUMERO_TIPO_CONTO = 20;


    Random random = new Random(12345);
    /*@Autowired
    AnagraficaClientiService anagraficaClientiService;
    */

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContoCorrenteRepository contoCorrenteRepository;
    @Autowired
    FilialeRepository filialeRepository;
     @Autowired
    TipoContoRepository tipoContoRepository;

    @Test
    public void testaClienteContoCorrente() {
        cancellaDatiEsistenti();

        creaClienti();
        creaContiCorrenti();

        System.out.println("Sto associando conti e clienti");
        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<Cliente> listaClienti = clienteRepository.findAll();
//        listaClienti.remove(0);
//        listaConti.remove(0);
        for (ContoCorrente cc : listaConti) {
            int clienteScelto = random.nextInt(NUMERO_CLIENTI);
            Cliente cli = listaClienti.get(clienteScelto);
            cc.setCliente(cli);
            contoCorrenteRepository.save(cc);
        }

        System.out.println("Sto verificando conti e clienti");
        for (Cliente cli : listaClienti) {
            int numCC = contoCorrenteRepository.contaCCPerClienteJPQL(cli.getId());
            Assertions.assertTrue(cli.getContiCorrenti().size() == numCC, "Numero conti errato!");
        }
    }

    private void creaClienti() {
        // creazione clienti
        System.out.println("Creazione clienti ...");
        for (int i = 0; i < NUMERO_CLIENTI; i++) {
            String n = "Nome" + i;
            String c = "Cognome" + i;
            String cf = "CF" + i;
            clienteRepository.save(new Cliente(n, c, cf));
        }
    }

    private void creaContiCorrenti() {
        // creazione clienti
        System.out.println("Creazione conti ...");
        for (int i = 0; i < NUMERO_CONTI_CORRENTI; i++) {
            String iban = "IBAN" + i;
            double fido = 0.0;
            contoCorrenteRepository.save(new ContoCorrente(iban, fido));
        }
    }


    @Test
    public void testaContoCorrenteCliente() {
        cancellaConti();

        creaContiCorrenti();
        creaClienti();

        System.out.println("Sto associando clienti a conti");
        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<Cliente> listaClienti = clienteRepository.findAll();
        for (Cliente cli : listaClienti) {
            int ccScelto = random.nextInt(NUMERO_CONTI_CORRENTI);
            ContoCorrente cf = listaConti.get(ccScelto);
            cli.getContiCorrenti().add(cf);
            clienteRepository.save(cli);
        }
    }

    private void cancellaConti() {
        //Cancello tutti i dati
        clienteRepository.deleteAllInBatch();
        Instant i1 = Instant.now();
        contoCorrenteRepository.deleteAllInBatch();
        Instant i2 = Instant.now();
        Duration d = Duration.between(i1, i2);
        System.out.println("Tempo impiegato per cancellare " + d.toMillis());
    }

    @Test
    public void testaFilialeContoCorrente() {
        cancellaDatiEsistenti();

        creaFiliale();
        creaContiCorrenti();

        System.out.println("Sto associando filili e conti");
        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<Filiale> listaFiliali = filialeRepository.findAll();
        for (ContoCorrente cc : listaConti) {
            int filialeScelta = random.nextInt(NUMERO_FILIALI);
            Filiale f = listaFiliali.get(filialeScelta);
            cc.setFiliale(f);
            contoCorrenteRepository.save(cc);
        }

        System.out.println("Sto verificando filiali e conti");
        for (Filiale f : listaFiliali) {
            int numCC = contoCorrenteRepository.contaCCPerFilialeJPQL(f.getId());
            Assertions.assertTrue(f.getContiCorrenti().size() == numCC, "Numero conti errato!");
        }
    }

    private void creaFiliale() {
        // creazione filiali
        System.out.println("Creazione filiali ...");
        for (int i = 0; i < NUMERO_FILIALI; i++) {
            String n = "Nome" + i;
            String c = "Codice" + i;
            filialeRepository.save(new Filiale(n, c));
        }
    }

    /*
    private void cancellaFiliali() {
        //Cancello tutti i dati
        contoCorrenteRepository.deleteAllInBatch();
        Instant i1 = Instant.now();
        filialeRepository.deleteAllInBatch();
        Instant i2 = Instant.now();
        Duration d = Duration.between(i1, i2);
        System.out.println("Tempo impiegato per cancellare " + d.toMillis());
    }
*/

    @Test
    public void testaContoCorrenteFiliale() {
        cancellaDatiEsistenti();

        creaContiCorrenti();
        creaFiliale();

        System.out.println("Sto associando conti e filiali");
        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<Filiale> listaFiliali = filialeRepository.findAll();
        for (Filiale f : listaFiliali) {
            int ccScelto = random.nextInt(NUMERO_CONTI_CORRENTI);
            ContoCorrente cf = listaConti.get(ccScelto);
            f.getContiCorrenti().add(cf);
            filialeRepository.save(f);
        }

    }

    /*
    private void cancellaContiCorrenti() {
        //Cancello tutti i dati
        filialeRepository.deleteAllInBatch();
        Instant i1 = Instant.now();
        contoCorrenteRepository.deleteAllInBatch();
        Instant i2 = Instant.now();
        Duration d = Duration.between(i1, i2);
        System.out.println("Tempo impiegato per cancellare " + d.toMillis());
    }
*/

    @Test
    public void testaTipoContoConto() {
        cancellaDatiEsistenti();

        creaTipiConto();
        creaContiCorrenti();

        System.out.println("Sto associando conto e tipoconto");
        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<TipoConto> listaTipoConto = tipoContoRepository.findAll();
        
        for (ContoCorrente cc : listaConti) {
            int tipoContoScelto = random.nextInt(NUMERO_TIPO_CONTO);
            TipoConto tc = listaTipoConto.get(tipoContoScelto);
            cc.setTipo(tc);
            contoCorrenteRepository.save(cc);
        }

        /*
        System.out.println("Sto verificando conti e TipoConto");
        for (TipoConto tc : listaTipoConto) {
            int numCC = contoCorrenteRepository.contaCCPerClienteJPQL(tc.getId());
            Assertions.assertTrue(tc.getContiCorrenti().size() == numCC, "Numero conti errato!");
        }
    */ 
}


    private void creaTipiConto(){
        // creazione tipo conto
        System.out.println("Creazione tipi conto ...");
        for (int i = 0; i < NUMERO_TIPO_CONTO; i++) {
            String n = "Nome" + i;
            String d = "Descrizione" + i;
            Double co = (double)i;
            Integer nog = i;
            Double ia = (double)i;
            Double fd = (double)i;
            Boolean cdc = true;
            Double cob = (double)i;
            tipoContoRepository.save(new TipoConto(n,d,co,nog,ia,fd,cdc,cob)); 
        }
    }

    
     @Test
    public void testaContoTipoConto() {
        cancellaDatiEsistenti();

        creaContiCorrenti();
        creaTipiConto();
       

        System.out.println("Sto associando tipiConto e conti");
        List<ContoCorrente> listaConti = contoCorrenteRepository.findAll();
        List<TipoConto> listaTipoConto = tipoContoRepository.findAll();
        
        for (TipoConto tc : listaTipoConto) {
            int ContoScelto = random.nextInt(NUMERO_CONTI_CORRENTI);
            ContoCorrente c = listaConti.get(ContoScelto);
            tc.getContiCorrenti().add(c);
            tipoContoRepository.save(tc);
        }
    }
    
    private void cancellaDatiEsistenti() {
        //Cancello tutti i dati
        contoCorrenteRepository.deleteAllInBatch();
        Instant i1 = Instant.now();
        clienteRepository.deleteAllInBatch();
        Instant i2 = Instant.now();
        tipoContoRepository.deleteAllInBatch();
        Instant i3 = Instant.now();
        filialeRepository.deleteAllInBatch();
        Instant i4 = Instant.now();
        Duration d = Duration.between(i1, i2);
        Duration d2 = Duration.between(i3, i4);
        System.out.println("Tempo impiegato per cancellare " + (d.toMillis() + d2.toMillis()) );
    }

}
