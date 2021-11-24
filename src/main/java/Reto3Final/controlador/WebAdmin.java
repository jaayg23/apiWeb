
package Reto3Final.controlador;

import Reto3Final.entidades.Admin;
import Reto3Final.servicios.ServiciosAdmin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jacobo
 */
@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class WebAdmin {
    
    @Autowired
    private ServiciosAdmin serviciosAdmin;
    
    @GetMapping("/all")
    public List<Admin> getAdmin(){
        return serviciosAdmin.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Admin> getAll(@PathVariable Integer id){
        return serviciosAdmin.getAdmin(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin save(Admin admin){
        return serviciosAdmin.save(admin);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin update(@RequestBody Admin admin){
        return serviciosAdmin.update(admin);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delAdmin(@PathVariable Integer id){
        return serviciosAdmin.delAdmin(id);
    }
}
