
package Reto3Final.repositorios;

import Reto3Final.entidades.Admin;
import Reto3Final.interfaces.InterfaceAdmin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jacobo
 */
@Repository
public class RepositorioAdmin {
    
    @Autowired
    private InterfaceAdmin interfaceAdmin;
    
    public List<Admin> getAll(){
        return (List<Admin>) interfaceAdmin.findAll();
    }
    
    public Optional<Admin> getAdmin(Integer id){
        return interfaceAdmin.findById(id);
    }
    
    public Admin save(Admin admin){
        return interfaceAdmin.save(admin);
    }
    
    public void delAdmin(Admin admin){
        interfaceAdmin.delete(admin);
    }
}
