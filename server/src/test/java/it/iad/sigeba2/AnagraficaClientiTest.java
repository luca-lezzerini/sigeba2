package it.iad.sigeba2;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnagraficaClientiTest {
    
    @Autowired
    AnagraficaClientiService anagraficaClientiService;
    
     @Test
     public void testaCaricamentoClienti() {
         ClienteDto dto = new ClienteDto();
         dto.setCodiceFiscale("ABCD");
         dto.setCognome("Rossi");
         dto.setId(null);
         dto.setNome("Mario");
         anagraficaClientiService.inserisciCliente(dto);
         dto = new ClienteDto();
         dto.setCodiceFiscale("EFGH");
         dto.setCognome("Bianchi");
         dto.setId(null);
         dto.setNome("Antonio");
         anagraficaClientiService.inserisciCliente(dto);
         dto = new ClienteDto();
         dto.setCodiceFiscale("IJKL");
         dto.setCognome("Verdi");
         dto.setId(null);
         dto.setNome("Elisa");
         anagraficaClientiService.inserisciCliente(dto);
         
         // verifico che i clienti siano 3
         List<ClienteDto> lista = anagraficaClientiService.mostraTuttiClienti();
         Assertions.assertTrue(lista.size() == 3);
         
         // verifico che cliente con id 1 sia Antonio Bianchi
         SimpleIdDto sid = new SimpleIdDto();
         sid.setId(1L);
         Cliente cli = anagraficaClientiService.leggiCliente(sid);
         Assertions.assertTrue(cli != null);
         Assertions.assertTrue(cli.getNome().equals("Antonio") && cli.getCognome().equals("Bianchi"));
         
         // verifico ricerca cliente
         CriterioClienteDto criterio = new CriterioClienteDto();
         criterio.setCriterio("oss");
         List<ClienteDto> clientiTrovati = anagraficaClientiService.cercaCliente(criterio);
         Assertions.assertTrue(clientiTrovati.size() == 1);
         Assertions.assertTrue(clientiTrovati.get(0).getCognome().equals("Rossi"));
         criterio.setCriterio("r");
         clientiTrovati = anagraficaClientiService.cercaCliente(criterio);
         Assertions.assertTrue(clientiTrovati.size() == 2);
        
     }
}
