
package Reto3Final.servicios;

import Reto3Final.entidades.Reservation;
import Reto3Final.repositorios.RepositorioReservation;
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
            if(!reservationAux.isEmpty()){
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
}
