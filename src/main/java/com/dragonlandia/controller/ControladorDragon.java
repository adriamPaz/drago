package com.dragonlandia.controller;

import com.dragonlandia.model.Dragon;
import com.dragonlandia.model.Monstruo;
import com.dragonlandia.util.HibernateUtil;
import jakarta.persistence.EntityManager;

/**
 * Controlador para gestionar la persistencia de Dragon en la base de datos.
 * Implementa el patrón MVC proporcionando operaciones CRUD y lógica de negocio
 * relacionada con los ataques de dragones.
 * 
 */
public class ControladorDragon {
    
    private Dragon dragon;
    
    /**
     * Obtiene el dragón gestionado por este controlador.
     * 
     * @return Instancia del dragón
     */
    public Dragon getDragon() {
        return dragon;
    }
    
    /**
     * Establece el dragón a gestionar por este controlador.
     * 
     * @param dragon Dragón a gestionar
     */
    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
    
    /**
     * Inserta un nuevo dragón en la base de datos.
     * Persiste el objeto completo con todos sus atributos.
     */
    public void añadirDragon() {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        ent.persist(this.dragon);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Actualiza los cambios del dragón en la base de datos.
     * Sincroniza el estado del objeto Java con la base de datos.
     */
    public void actualizarDragon() {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        this.dragon = ent.merge(this.dragon);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Elimina el dragón de la base de datos.
     * Borra permanentemente el registro de la tabla.
     */
    public void eliminarDragon() {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        Dragon dragonManaged = ent.merge(this.dragon);
        ent.remove(dragonManaged);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * El dragón exhala fuego contra un monstruo.
     * Actualiza el estado del dragón en la base de datos.
     * 
     * @param monstruo Monstruo que recibe el ataque de fuego
     */
    public void exhalar(Monstruo monstruo) {
        this.dragon.exhalar(monstruo);
        actualizarDragon();
    }
    
    /**
     * Cambia la resistencia del dragón y actualiza en la base de datos.
     * Si la resistencia llega a 0, elimina el dragón de la base de datos.
     * 
     * @param cambio Cantidad a sumar o restar de la resistencia actual
     */
    public void cambiarResistencia(int cambio) {
        int resistencia = (this.dragon.getResistencia() + cambio > 0) ? 
                          (this.dragon.getResistencia() + cambio) : 0;
        this.dragon.setResistencia(resistencia);
        
        if (this.dragon.getResistencia() == 0) {
            eliminarDragon();
        } else {
            actualizarDragon();
        }
    }
}
