
package Reto3Final.servicios;

import Reto3Final.entidades.Reservation;
import Reto3Final.entidades.especificas.CountClient;
import Reto3Final.entidades.especificas.StatusAmount;
import Reto3Final.repositorios.RepositorioReservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacobo
 */
@Service
public class ServiciosReservation {
    
    @Autowired
    private RepositorioReservation metodosCrud;
    
    public List<Reservation> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Reservation> getReservation(Integer id){
        return metodosCrud.getReservation(id);
    }
    
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return metodosCrud.save(reservation);
        } else {
            Optional<Reservation> reservationAux = metodosCrud.getReservation(reservation.getIdReservation());
            if(reservationAux == null){
                return metodosCrud.save(reservation);
            } else {
                return reservation;
            }
        }
    }
    
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation() == null){
            Optional<Reservation> reservationAux = metodosCrud.getReservation(reservation.getIdReservation());
            if(reservationAux.isPresent()){
                if(reservation.getStartDate() != null){
                    reservationAux.get().setStartDate(reservation.getStartDate());
                }
                
                if(reservation.getDevolutionDate() != null){
                    reservationAux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                metodosCrud.save(reservationAux.get());
                return reservationAux.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }
    
    public Boolean delReservation(Integer id){
        Boolean aBoolean = getReservation(id).map(reservation -> {metodosCrud.delReservation(reservation);
        return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public List<CountClient> getTopClient(){
        return metodosCrud.getTopClient();
    }
    
    public StatusAmount getStatusReport(){
        List<Reservation> completed = metodosCrud.getReservationByStatus("completed");
        List<Reservation> cancelled = metodosCrud.getReservationByStatus("cancelled");
        
        StatusAmount statusAmount = new StatusAmount(completed.size(), cancelled.size());
        return statusAmount;
    }
    
    public List<Reservation> getReservationPeriod(String d1, String d2){
        
        //Formato fecha: yyyy-MM-dd
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        
        try{
            dateOne = parser.parse(d1);
            dateTwo = parser.parse(d2);
        } catch(ParseException e){
            e.printStackTrace();
        }
        
        if(dateOne.before(dateTwo)){
            return metodosCrud.getReservationByDate(dateOne, dateTwo);
        } else {
            return new ArrayList<>();
        }
        
    }
}
