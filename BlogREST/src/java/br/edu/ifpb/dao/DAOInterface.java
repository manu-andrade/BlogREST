/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dao;

/**
 *
 * @author Manu
 */
public interface DAOInterface<T> {
	public void persist(T obj);
	public void remove(T obj) ;
	public T merge(T obj);
	public void refresh(T obj);
}
