package repository;
import Domain.Identifiable;

import java.util.*;

public abstract class Abstract_Repository <ID, T extends Identifiable<ID>> implements Repository <ID, T>{
    protected Map<ID,T> map;
    public Abstract_Repository(){
        map = new HashMap<>();
    }
    public T add(T el){
        if(map.containsKey(el.getId())){
            throw new RuntimeException("Element already in repository");
        }
        else
            map.put(el.getId() , el);
        return el;
    }

    public T findByID(ID id){
        if (map.containsKey(id))
            return map.get(id);
        else
            return null;
    }

    public Iterable<T> findAll(){
        return map.values();
    }

    public Collection<T> getAll() { return map.values();}
}
