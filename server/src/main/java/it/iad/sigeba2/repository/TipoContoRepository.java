
 
package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.TipoConto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContoRepository extends JpaRepository<TipoConto, Long> {
    List<TipoConto> findByNomeLikeOrDescrizioneLike(String critNome, String critDescrizione);   
}
