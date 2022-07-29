package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.ClienteDto;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagraficaClientiController {

    @RequestMapping("/cercaCliente")
    @ResponseBody
    public List<ClienteDto> cercaCliente(@RequestBody CriterioClienteDto criterio) {
        throw new UnsupportedOperationException();
    }
    @ResponseBody
    @RequestMapping("/inserisciCliente")
    public List<ClienteDto> inserisciCliente(@RequestBody ClienteDto cliente) {
        throw new UnsupportedOperationException();
    }
    
}
