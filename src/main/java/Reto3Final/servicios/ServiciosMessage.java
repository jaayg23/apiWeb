
package Reto3Final.servicios;

import Reto3Final.entidades.Message;
import Reto3Final.repositorios.RepositorioMessage;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacobo
 */
@Service
public class ServiciosMessage {
    @Autowired
    private RepositorioMessage metodosCrud;
    
    public List<Message> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Message> getMessage(Integer id){
        return metodosCrud.getMessage(id);
    }
    
    public Message save(Message message){
        if(message.getIdMessage() == null){
            return metodosCrud.save(message);
        } else {
            Optional<Message> messageAux = metodosCrud.getMessage(message.getIdMessage());
            if(messageAux == null){
                return metodosCrud.save(message);
            } else {
                return message;
            }
        }
    }
    
    public Message update(Message message){
        if(message.getIdMessage() == null){
            Optional<Message> messageAux = metodosCrud.getMessage(message.getIdMessage());
            if(messageAux.isPresent()){
                if(message.getMessageText() != null){
                    messageAux.get().setMessageText(message.getMessageText());
                }
                metodosCrud.save(messageAux.get());
                return messageAux.get();
            } else {
                return message;
            }
        } else {
            return message;
        }
    }
    
    public Boolean delMessage(Integer id){
        Boolean aBoolean = getMessage(id).map(message -> {metodosCrud.delMessage(message);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
