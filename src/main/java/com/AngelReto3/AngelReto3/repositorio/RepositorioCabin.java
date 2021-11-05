/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AngelReto3.AngelReto3.repositorio;

import com.AngelReto3.AngelReto3.interfaces.InterfaceCabin;
import com.AngelReto3.AngelReto3.modelo.Cabin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anpem
 */

 @Repository
public class RepositorioCabin {
    
    @Autowired
    private InterfaceCabin crud;

    public List<Cabin> getAll(){
        return (List<Cabin>) crud.findAll();
    }

    public Optional<Cabin> getCabin(int id){
        return crud.findById(id);
    }

    public Cabin save(Cabin cabin){
        return crud.save(cabin);
    }
    public void delete(Cabin cabin){
        crud.delete(cabin);
    }
    
}

