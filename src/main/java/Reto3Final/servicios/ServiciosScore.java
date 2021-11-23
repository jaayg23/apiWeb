
package Reto3Final.servicios;

import Reto3Final.entidades.Score;
import Reto3Final.repositorios.RepositorioScore;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacobo
 */
@Service
public class ServiciosScore {
    
    @Autowired
    private RepositorioScore repositorioScore;
    
    public List<Score> getAll(){
        return repositorioScore.getAll();
    }
    
    public Optional<Score> getScore(Integer id){
        return repositorioScore.getScore(id);
    }
    
    public Score save(Score score){
        if(score.getIdScore() == null){
            return repositorioScore.save(score);
        } else {
            Optional<Score> scoreAux = repositorioScore.getScore(score.getIdScore());
            if(scoreAux.isEmpty()){
                return repositorioScore.save(score);
            } else {
                return score;
            }
        }
    }
}
