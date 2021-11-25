
package Reto3Final.entidades.especificas;

import Reto3Final.entidades.Client;

/**
 *
 * @author Jacobo
 */
public class CountClient {
    
    private Long total;
    private Client client;
    
    public CountClient(Long total, Client client){
        this.total = total;
        this.client = client;
    }
    
    public Long getTotal(){
        return total;
    }
    
    public void setTotal(Long total){
        this.total = total;
    }
    
    public Client getClient(){
        return client;
    }
    
    public void setClient(Client client){
        this.client = client;
    }
}
