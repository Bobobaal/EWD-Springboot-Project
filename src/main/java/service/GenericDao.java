package service;

import java.util.List;

import domain.Stadium;

public interface GenericDao<T> {
   
    public List<T> findAll();

    public T update(T object);

    public T get(int id);

    public void delete(T object);

    public void insert(T object);
    
    public boolean exists(int id) ;
    
    public Stadium geefStadium(String naam);
}