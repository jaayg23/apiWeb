
package Reto3Final.servicios;

import Reto3Final.entidades.Admin;
import Reto3Final.repositorios.RepositorioAdmin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacobo
 */
@Service
public class ServiciosAdmin {
    
    @Autowired
    private RepositorioAdmin repositorioAdmin;
    
    public List<Admin> getAll(){
        return repositorioAdmin.getAll();
    }
    
    public Optional<Admin> getAdmin(Integer id){
        return repositorioAdmin.getAdmin(id);
    }
    
    public Admin save(Admin admin){
        if(admin.getIdAdmin() == null){
            return repositorioAdmin.save(admin);
        } else {
            Optional<Admin> adminAux = repositorioAdmin.getAdmin(admin.getIdAdmin());
            if(adminAux == null){
                return repositorioAdmin.save(admin);
            } else {
                return admin;
            }
        }
    }
    
    public Admin update(Admin admin){
        if(admin.getIdAdmin() != null){
            Optional<Admin> auxAdmin = repositorioAdmin.getAdmin(admin.getIdAdmin());
            if(!auxAdmin.isPresent()){
                if(admin.getName() != null){
                    auxAdmin.get().setName(admin.getName());
                }
                
                if(admin.getEmail() != null){
                    auxAdmin.get().setEmail(admin.getEmail());
                }
                
                if(admin.getPassword() != null){
                    auxAdmin.get().setPassword(admin.getPassword());
                }
                
                repositorioAdmin.save(auxAdmin.get());
                return auxAdmin.get();
            } else {
                return admin;
            }
        } else {
            return admin;
        }
    }
    
    public Boolean delAdmin(Integer id){
        Boolean aBoolean = getAdmin(id).map(admin -> {repositorioAdmin.delAdmin(admin);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
