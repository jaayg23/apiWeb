
package Reto3Final.interfaces;

import Reto3Final.entidades.Score;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jacobo
 */
public interface InterfaceScore extends CrudRepository<Score, Integer> {
    
}
