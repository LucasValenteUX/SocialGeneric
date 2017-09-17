package com.br.lp3.model.dao;

import java.util.List;
public interface GenericDAO<E> {
    
    public boolean create(E e); //CREATE
    public List<E> read();      //READ
    public E readById(long id);
    public boolean update(E e); //UPDATE
    public boolean delete(E e); //DELETE
    
}
