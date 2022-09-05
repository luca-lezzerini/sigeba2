package it.iad.sigeba2.service;

import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.model.ContoCorrente;

public interface AssociazioniService {
    
    /**
     * Dato un conto corrente gli associa un cliente
     * @param idCliente da associare al conto
     * @param idConto a cui associare il cliente
     */
    void associaClienteAConto(Long idConto, Long idCliente) throws SigebaException; 
    
    
}
