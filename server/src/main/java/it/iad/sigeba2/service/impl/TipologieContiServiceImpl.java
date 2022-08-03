package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.service.TipologieContiService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TipologieContiServiceImpl implements TipologieContiService {

    @Override
    public List<TipoContoDto> cercaTipoContoDto(CriterioTipoContoDto criterio) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<TipoContoDto> inserisciTipoContoDto(TipoContoDto inserisci) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<TipoContoDto> cancellaTipoContoDto(CriterioCancellazioneTipoContoDto cancella) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<TipoContoDto> modificaTipoContoDto(CriterioModificaTipoContoDto modifica) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public TipoConto leggiCliente(SimpleIdDto cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
