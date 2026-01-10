package com.dragonlandia.controller;

import com.dragonlandia.model.Bosque;
import com.dragonlandia.model.Monstruo;
import com.dragonlandia.util.HibernateUtil;
import jakarta.persistence.EntityManager;

/**
 * Controlador para gestionar la persistencia de Bosque en la base de datos.
 * Implementa el patrón MVC proporcionando operaciones CRUD y lógica de negocio
 * relacionada con la gestión de bosques, sus jefes y monstruos.
 * 
 * 
 */
public class ControladorBosque {
    
    private Bosque bosque;
    
    /**
     * Obtiene el bosque gestionado por este controlador.
     * 
     * @return Instancia del bosque
     */
    public Bosque getBosque() {
        return bosque;
    }
    
    /**
     * Establece el bosque a gestionar por este controlador.
     * 
     * @param bosque Bosque a gestionar
     */
    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }
    
    /**
     * Inserta un nuevo bosque en la base de datos.
     * Persiste el objeto completo con sus relaciones.
     */
    public void añadirBosque() {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        ent.persist(this.bosque);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Actualiza los cambios del bosque en la base de datos.
     * Sincroniza el estado del objeto Java con la base de datos.
     */
    public void actualizarBosque() {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        this.bosque = ent.merge(this.bosque);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Elimina el bosque de la base de datos.
     * Borra permanentemente el registro y sus relaciones.
     */
    public void eliminarBosque() {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        Bosque bosqueManaged = ent.merge(this.bosque);
        ent.remove(bosqueManaged);
        ent.getTransaction().commit();
        ent.close();
    }
    
    /**
     * Cambia el monstruo jefe del bosque y actualiza en la base de datos.
     * 
     * @param nuevoJefe Nuevo monstruo que será el jefe
     */
    public void cambiarJefe(Monstruo nuevoJefe) {
        this.bosque.cambiarJefe(nuevoJefe);
        actualizarBosque();
    }
    
    /**
     * Añade un monstruo a la lista de monstruos del bosque.
     * Actualiza en la base de datos.
     * 
     * @param monstruo Monstruo a añadir al bosque
     */
    public void añadirMonstruo(Monstruo monstruo) {
        this.bosque.addMonstruo(monstruo);
        actualizarBosque();
    }
    
    /**
     * Muestra los datos del monstruo jefe del bosque en consola.
     */
    public void mostrarJefe() {
        this.bosque.mostrarJefe();
    }
    
    /**
     * Cambia el nivel de peligro del bosque y actualiza en la base de datos.
     * 
     * @param nuevoNivel Nuevo nivel de peligrosidad
     */
    public void cambiarNivelPeligro(int nuevoNivel) {
        this.bosque.setNivelPeligro(nuevoNivel);
        actualizarBosque();
    }
    
    /**
     * Elimina el jefe actual del bosque de manera segura.
     * Primero quita la referencia del jefe del bosque, luego elimina el monstruo.
     * Esto previene violaciones de foreign key constraints.
     * 
     * @param monstruoJefe Monstruo jefe a eliminar
     */
    public void eliminarJefe(Monstruo monstruoJefe) {
        EntityManager ent = HibernateUtil.getEntityManager();
        ent.getTransaction().begin();
        
        // Obtener el bosque managed en esta transacción
        Bosque bosqueManaged = ent.merge(this.bosque);
        
        // Quitar la referencia del jefe
        bosqueManaged.setMonstruoJefe(null);
        ent.merge(bosqueManaged);
        
        // Obtener el monstruo managed en esta transacción
        Monstruo monstruoManaged = ent.merge(monstruoJefe);
        
        // Eliminar el monstruo
        ent.remove(monstruoManaged);
        
        ent.getTransaction().commit();
        ent.close();
        
        // Actualizar el bosque en memoria
        this.bosque = bosqueManaged;
    }
}
