
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
            if(scoreAux == null){
                return repositorioScore.save(score);
            } else {
                return score;
            }
        }
    }
    
    public Score update(Score score){
        if(score.getIdScore() != null){
            Optional<Score> scoreAux = repositorioScore.getScore(score.getIdScore());
            if(!scoreAux.isEmpty()){
                
                if(score.getMessageText() != null){
                    scoreAux.get().setMessageText(score.getMessageText());
                }
                
                if(score.getStars() != null){
                    scoreAux.get().setStars(score.getStars());
                }
                
                repositorioScore.save(scoreAux.get());
                return scoreAux.get();
            } else {
                return score;
            }
        } else {
            return score;
        }
    }
    
    public Boolean delScore(Integer id){
        Boolean aBoolean = getScore(id).map(score -> {repositorioScore.delScore(score);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
