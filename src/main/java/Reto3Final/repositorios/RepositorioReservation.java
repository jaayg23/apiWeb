
package Reto3Final.repositorios;

import Reto3Final.entidades.Client;
import Reto3Final.entidades.Reservation;
import Reto3Final.entidades.especificas.CountClient;
import Reto3Final.interfaces.InterfaceReservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jacobo
 */
@Repository
public class RepositorioReservation {
    
    @Autowired
    private InterfaceReservation interfaceReservation;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) interfaceReservation.findAll();
    }
    
    public Optional<Reservation> getReservation(Integer id){
        return interfaceReservation.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return interfaceReservation.save(reservation);
    }
    
    public void delReservation(Reservation reservation){
        interfaceReservation.delete(reservation);
    }
    
    public List<Reservation> getReservationByStatus(String status){
        return interfaceReservation.findAllByStatus(status);
    }
    
    public List<Reservation> getReservationByDate(Date dateOne, Date dateTwo){
        return interfaceReservation.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    
    public List<CountClient> getTopClient(){
        List<CountClient> result = new ArrayList<>();
        List<Object[]> report = interfaceReservation.countTotalClientByReservation();
        
        for(int i=0; i < report.size(); i++){
            
            Client client = (Client) report.get(i)[0];
            Long count = (Long) report.get(i)[1];
            
            CountClient cc = new CountClient(count, client);
            
            result.add(cc);
        }
        
        return result;
    }
}

