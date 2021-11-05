/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AngelReto3.AngelReto3.interfaces;

import com.AngelReto3.AngelReto3.modelo.Mensaje;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author anpem
 */
public interface InterfaceMensaje extends CrudRepository<Mensaje,Integer>{
    
}
