package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.service.TipologieContiService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class TipologieContiServiceImpl implements TipologieContiService {
    
  private final Map<Long, TipoConto> mappaTipoConti = new HashMap<>();
    @Override
   
    public List<TipoContoDto> cercaTipoConto(CriterioTipoContoDto criterio) {
     log.debug("Entrato in cercaTipoConto");
        String crit = criterio.getCriterio();
        List<TipoContoDto> listaConti= new ArrayList<>();
        
        Collection<TipoConto> lista = mappaTipoConti.values();
        for ( TipoConto tipoConto : lista) {
            if (tipoConto.getNome().contains(crit) || tipoConto.getDescrizione().contains(crit)){
                
                listaConti.add(new TipoContoDto(tipoConto));
            }
        }
        log.debug("Uscito da cercaCliente");
        return listaConti;
    }
    

    @Override
    public List<TipoContoDto> inserisciTipoConto(TipoContoDto inserisci) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public List<TipoContoDto> cancellaTipoConto(CriterioCancellazioneTipoContoDto cancella) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TipoContoDto> modificaTipoConto(CriterioModificaTipoContoDto modifica) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoConto leggiCliente(SimpleIdDto cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
