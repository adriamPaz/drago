package com.dragonlandia.controller;

import javax.swing.text.html.parser.Entity;

import com.dragonlandia.model.Mago;
import com.dragonlandia.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ControladorMago {
    private Mago mago;
    
    public Mago getMago() {
        return mago;
    }
    public void setMago(Mago mago) {
        this.mago = mago;
    }

    public void cambiarVida(int cambio){
        var vida = (this.mago.getVida()+cambio>0)?(this.mago.getVida()+cambio):0;
        this.mago.setVida(vida);
    }

    public void añadirMago(){
        
    }

}
