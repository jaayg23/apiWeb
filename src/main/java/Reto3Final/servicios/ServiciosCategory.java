
package Reto3Final.servicios;

import Reto3Final.entidades.Category;
import Reto3Final.repositorios.RepositorioCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacobo
 */
@Service
public class ServiciosCategory {
    
    @Autowired
    private RepositorioCategory metodosCrud;
    
    public List<Category> getALL(){
        return metodosCrud.getALL();
    }
    
    public Optional<Category> getCategory(int id){
        return metodosCrud.getCategory(id);
    }
    
    public Category save(Category category){
        if(category.getId()==null){
            return metodosCrud.save(category);
        }else{
            Optional<Category> evt=metodosCrud.getCategory(category.getId());
            if(evt == null){
                return metodosCrud.save(category);
            }else{
                return category;
            }
        }
    }
    
    public Category update(Category category){
        if(category.getId() != null){ //Verifica si el id no está vacío
            Optional<Category> categoryAux = metodosCrud.getCategory(category.getId()); //Crea un auxiliar en el que se guarda el id del elemento
            if(!categoryAux.isPresent()){ //Verifica que el id no sea vacío
                if(category.getName() != null){ //Verifica que el nombre no sea vacío
                    categoryAux.get().setName(category.getName()); //busca el nombre y sobreescribe el nombre actual que se consigue con .get()
                }
                
                if(category.getDescription() != null){//Verifica que la descripción no sea vacía
                    categoryAux.get().setDescription(category.getDescription());//Busca la desc actual y la sobreescribe
                }
                
                metodosCrud.save(categoryAux.get()); //Guarda el valor actual de categoryAux
                return categoryAux.get(); //Retorna el valor de categoryAux
            } else {
                return category; //Si name o description son vacios retorna el objeto original
            }
        } else {
            return category; //Si el id es null retorna el objeto original
        }
    }
    
    public Boolean delCategory(Integer id){
        Boolean aBoolean = getCategory(id).map(category -> {metodosCrud.delCategory(category);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
