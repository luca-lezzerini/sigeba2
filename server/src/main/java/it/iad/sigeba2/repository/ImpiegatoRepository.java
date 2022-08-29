
package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.Impiegato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpiegatoRepository  extends JpaRepository<Impiegato, Long> {
    
    List<Impiegato> findByNomeLikeOrCognomeLikeOrMatricolaLike (String critNome, String critCognome, String critMatricola);
    
}
