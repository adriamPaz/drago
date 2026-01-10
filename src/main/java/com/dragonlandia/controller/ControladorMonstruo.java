package com.dragonlandia.controller;

import com.dragonlandia.model.Mago;
import com.dragonlandia.model.Monstruo;
import com.dragonlandia.util.HibernateUtil;

import jakarta.persistence.EntityManager;

/**
 * Controlador para gestionar la persistencia de Monstruo en la base de datos.
 * Implementa el patrón MVC proporcionando operaciones CRUD y lógica de negocio
 * relacionada con los ataques de monstruos.
 * 
 */
public class ControladorMonstruo {
    private Monstruo monstruo;

    /**
     * Obtiene el monstruo gestionado por este controlador.
     * 
     * @return Instancia del monstruo
     */
    public Monstruo getMonstruo() {
        return monstruo;
    }
    
    /**
     * Establece el monstruo a gestionar por este controlador.
     * 
     * @param monstruo Monstruo a gestionar
     */
    public void setMonstruo(Monstruo monstruo) {
        this.monstruo = monstruo;
    }

    /**
     * Cambia la vida del monstruo y actualiza en la base de datos.
     * Si la vida llega a 0, elimina el monstruo de la base de datos.
     * La vida nunca será negativa.
     * 
     * @param cambio Cantidad a sumar o restar de la vida actual
     */
    public void cambiarVida(int cambio){
        int vida = (this.monstruo.getVida() + cambio > 0) ? (this.monstruo.getVida() + cambio) : 0;
        this.monstruo.setVida(vida);
        
        if (this.monstruo.getVida() == 0) {
            eliminarMonstruo();
        } else {
            actualizarMonstruo();
        }
    }
    
    /**
     * El monstruo ataca a un mago aplicando su fuerza modificada por tipo.
     * Actualiza el estado del monstruo en la base de datos.
     * 
     * @param mago Mago que recibe el ataque
     */
    public void atacar(Mago mago) {
        this.monstruo.atacar(mago);
        actualizarMonstruo();
    }

    /**
     * Inserta un nuevo monstruo en la base de datos.
     * Persiste el objeto completo con todos sus atributos.
     */
    public void añadirMonstruo(){
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        ent.persist(this.monstruo);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Actualiza los cambios del monstruo en la base de datos.
     * Sincroniza el estado del objeto Java con la base de datos.
     */
    public void actualizarMonstruo(){
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        this.monstruo = ent.merge(this.monstruo);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Elimina el monstruo de la base de datos.
     * Borra permanentemente el registro de la tabla.
     */
    public void eliminarMonstruo(){
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        Monstruo monstruoManaged = ent.merge(this.monstruo);
        ent.remove(monstruoManaged);
        ent.getTransaction().commit();
        ent.close();
    }


    

}
