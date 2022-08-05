package it.iad.sigeba2.dao.impl;

import it.iad.sigeba2.dao.ClienteDao;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDaoImpl implements ClienteDao {

    private final Map<Long, Cliente> mappaClienti = new HashMap<>();
    private Long prossimaChiave = 0L;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> trovaTutti() {
//        Collection<Cliente> collezione = mappaClienti.values();
//        List<Cliente> risultato = new ArrayList<>(collezione);

        return clienteRepository.findAll();
    }

    @Override
    public Cliente inserisci(Cliente cliente) {
        // imposta la chiave primaria e la incrementa per il prossimo inserimento
//        cliente.setId(prossimaChiave);
//        prossimaChiave += 1;
//        mappaClienti.put(cliente.getId(), cliente);

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente trovaClientePerId(Long id) {
//        return mappaClienti.get(id);
        Cliente cliente = clienteRepository.findById(id)
                .map(cx -> cx)
                .orElse(null);
        return cliente;
    }

    @Override
    public Cliente modifica(Cliente nuovo) {
//        return mappaClienti.put(nuovo.getId(), nuovo);
        return clienteRepository.save(nuovo);
    }

    @Override
    public Cliente elimina(Long id) {
        Cliente cliente = trovaClientePerId(id);
        clienteRepository.delete(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> trovaPerNomeCognomeCodiceFiscale(String criterio) {
        List<Cliente> dtos = new ArrayList<>();
        Collection<Cliente> lista = mappaClienti.values();
        for (Cliente cliente : lista) {
            if (cliente.getNome().contains(criterio)
                    || cliente.getCognome().contains(criterio)
                    || cliente.getCodiceFiscale().contains(criterio)) {
                dtos.add(cliente);
            }
        }
        return dtos;
    }

}
