
package Reto3Final.servicios;

import Reto3Final.entidades.Partyroom;
import Reto3Final.repositorios.RepositorioPartyroom;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacobo
 */
@Service
public class ServiciosPartyroom {
    
    
    @Autowired
    private RepositorioPartyroom metodosCrud;
    
    public List<Partyroom> getALL(){
        return metodosCrud.getALL();
    }
    
    public Optional<Partyroom> getPartyroom(int id){
        return metodosCrud.getPartytoom(id);
    }
    
    public Partyroom save(Partyroom partyroom){
        if(partyroom.getId()==null){
            return metodosCrud.save(partyroom);
        }else{
            Optional<Partyroom> evt=metodosCrud.getPartytoom(partyroom.getId());
            if(evt == null){
                return metodosCrud.save(partyroom);
            }else{
                return partyroom;
            }
        }
    }
    
    public Partyroom update(Partyroom partyroom){
        if(partyroom.getId() != null){
            Optional<Partyroom> roomAux = metodosCrud.getPartytoom(partyroom.getId());
            
            if(roomAux.isPresent()){
                
                if(partyroom.getName() != null){
                    roomAux.get().setName(partyroom.getName());
                }
                
                if(partyroom.getOwner()!= null){
                    roomAux.get().setOwner(partyroom.getOwner());
                }
                
                if(partyroom.getCapacity()!= null){
                    roomAux.get().setCapacity(partyroom.getCapacity());
                }
                
                if(partyroom.getDescription() != null){
                    roomAux.get().setDescription(partyroom.getDescription());
                }
                
                metodosCrud.save(roomAux.get());
                return roomAux.get();
            } else {
                return partyroom;
            }
        } else {
            return partyroom;
        }
    }
    
    public Boolean delRoom(Integer id){
        Boolean aBoolean = getPartyroom(id).map(partyroom -> {metodosCrud.delPartyroom(partyroom);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
