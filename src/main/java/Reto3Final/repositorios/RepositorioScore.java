
package Reto3Final.repositorios;

import Reto3Final.entidades.Score;
import Reto3Final.interfaces.InterfaceScore;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jacobo
 */
@Repository
public class RepositorioScore {
    
    @Autowired
    private InterfaceScore interfaceScore;
    
    public List<Score> getAll(){
        return (List<Score>) interfaceScore.findAll();
    }
    
    public Optional<Score> getScore(Integer id){
        return interfaceScore.findById(id);
    }
    
    public Score save(Score score){
        return interfaceScore.save(score);
    }
}
