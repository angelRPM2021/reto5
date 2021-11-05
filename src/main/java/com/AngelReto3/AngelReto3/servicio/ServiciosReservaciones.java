/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AngelReto3.AngelReto3.servicio;

import com.AngelReto3.AngelReto3.modelo.Reservaciones;
import com.AngelReto3.AngelReto3.repositorio.ContadorClientes;
import com.AngelReto3.AngelReto3.repositorio.RepositorioReservaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anpem
 */
@Service
public class ServiciosReservaciones {
     @Autowired
    private RepositorioReservaciones metodosCrud;

    public List<Reservaciones> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Reservaciones> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    public Reservaciones save(Reservaciones reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservaciones> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservaciones update(Reservaciones reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservaciones> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    
    public StatusReservas getReservationsStatusReport(){
        List<Reservaciones>completed=metodosCrud.getReservationByStatus("completed");
        List<Reservaciones>cancelled=metodosCrud.getReservationByStatus("cancelled");
    return new StatusReservas(completed.size(), cancelled.size());
    }
    
    
    
public List<Reservaciones> getReservationPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate= new Date();
        Date bDate= new Date();
        
       try {
           aDate = parser.parse(dateA);
           bDate = parser.parse(dateB);
       }catch(ParseException evt){
           evt.printStackTrace();
       }
       if(aDate.before(bDate)){
           return metodosCrud.getReservationPeriod(aDate, bDate);
       }else{
           return new ArrayList<>();
       } 
}
       
       
       public List<ContadorClientes> getTopClients(){
        return metodosCrud.getTopClients();
    }

       
}

