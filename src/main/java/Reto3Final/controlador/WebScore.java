
package Reto3Final.controlador;

import Reto3Final.entidades.Score;
import Reto3Final.servicios.ServiciosScore;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jacobo
 */
@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*", methods ={RequestMethod.GET, RequestMethod.POST})
public class WebScore {
    
    @Autowired
    private ServiciosScore serviciosScore;
    
    @GetMapping("all")
    public List<Score> getScore(){
        return serviciosScore.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Score> getPartyroom(@PathVariable("{id}") Integer id){
        return serviciosScore.getScore(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score save(Score score){
        return serviciosScore.save(score);
    }
}
