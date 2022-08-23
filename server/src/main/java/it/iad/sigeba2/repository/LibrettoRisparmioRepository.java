package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.LibrettoRisparmio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrettoRisparmioRepository extends JpaRepository<LibrettoRisparmio, Long>{
    
    List<LibrettoRisparmio> findByCodiceLike(String critCodice);
    
}
