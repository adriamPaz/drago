package com.dragonlandia.controller;

import java.util.List;

import com.dragonlandia.model.Hechizo;
import com.dragonlandia.model.Mago;
import com.dragonlandia.model.Monstruo;
import com.dragonlandia.util.HibernateUtil;
import jakarta.persistence.EntityManager;

/**
 * Controlador para gestionar la persistencia de Mago en la base de datos.
 * Implementa el patrón MVC proporcionando operaciones CRUD y lógica de negocio
 * relacionada con el lanzamiento de hechizos.
 * 
 */
public class ControladorMago {
    private Mago mago;

    /**
     * Obtiene el mago gestionado por este controlador.
     * 
     * @return Instancia del mago
     */
    public Mago getMago() {
        return mago;
    }
    
    /**
     * Establece el mago a gestionar por este controlador.
     * 
     * @param mago Mago a gestionar
     */
    public void setMago(Mago mago) {
        this.mago = mago;
    }

    /**
     * Cambia la vida del mago y actualiza automáticamente en la base de datos.
     * Si la vida llega a 0, elimina el mago de la base de datos.
     * La vida nunca será negativa.
     * 
     * @param cambio Cantidad a sumar o restar de la vida actual
     */
    public void cambiarVida(int cambio){
        int vida = (this.mago.getVida() + cambio > 0) ? (this.mago.getVida() + cambio) : 0;
        this.mago.setVida(vida);
        
        // Sincronizar cambios con la BD
        if (this.mago.getVida() == 0) {
            eliminarMago();
        } else {
            actualizarMago();
        }
    }

    /**
     * El mago lanza un hechizo genérico contra un monstruo.
     * Actualiza el estado del mago en la base de datos.
     * Si el mago muere durante el lanzamiento, se elimina de la BD.
     * 
     * @param monstruo Monstruo objetivo del hechizo
     */
    public void lanzarHechizo(Monstruo monstruo) {
        this.mago.lanzarHechizo(monstruo);
        
        if (this.mago.getVida() == 0) {
            eliminarMago();
        } else {
            actualizarMago();
        }
    }
    
    /**
     * El mago lanza un hechizo específico contra un monstruo.
     * Actualiza el estado del mago en la base de datos.
     * Si el mago no conoce el hechizo, pierde 1 punto de vida.
     * 
     * @param monstruo Monstruo objetivo del hechizo
     * @param hechizo Hechizo específico a lanzar
     */
    public void lanzarHechizo(Monstruo monstruo, Hechizo hechizo) {
        this.mago.lanzarHechizo(monstruo, hechizo);
        
        // Si el mago no conoce el hechizo, pierde 1 vida
        if (this.mago.getVida() == 0) {
            eliminarMago();
        } else {
            actualizarMago();
        }
    }
    
    /**
     * El mago lanza un hechizo de área contra varios monstruos.
     * Actualiza el estado del mago en la base de datos.
     * Si el mago no conoce el hechizo, pierde 1 punto de vida.
     * 
     * @param monstruos Lista de monstruos afectados por el hechizo
     * @param hechizo Hechizo de área a lanzar
     */
    public void lanzarHechizo(List<Monstruo> monstruos, Hechizo hechizo) {
        this.mago.lanzarHechizo(monstruos, hechizo);
        
        // Si el mago no conoce el hechizo, pierde 1 vida
        if (this.mago.getVida() == 0) {
            eliminarMago();
        } else {
            actualizarMago();
        }
    }
    
    /**
     * El mago aprende un nuevo hechizo y lo añade a su repertorio.
     * Actualiza la base de datos para persistir el cambio.
     * 
     * @param hechizo Hechizo nuevo a aprender
     */
    public void aprenderHechizo(Hechizo hechizo) {
        this.mago.aprenderHechizo(hechizo);
        actualizarMago();
    }

    /**
     * Inserta un nuevo mago en la base de datos.
     * Persiste el objeto completo incluyendo sus relaciones con hechizos.
     */
    public void añadirMago(){
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        ent.persist(this.mago);
        ent.getTransaction().commit();
        ent.close();
    }

    /**
     * Actualiza los cambios del mago en la base de datos.
     * Sincroniza el estado del objeto Java con la base de datos.
     */
    public void actualizarMago(){
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        this.mago = ent.merge(this.mago);
        ent.getTransaction().commit();
        ent.close();
    }

    /**
     * Elimina el mago de la base de datos.
     * Borra permanentemente el registro y sus relaciones.
     */
    public void eliminarMago(){
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        Mago magoManaged = ent.merge(this.mago);
        ent.remove(magoManaged);
        ent.getTransaction().commit();
        ent.close();
    }

}
