package com.dragonlandia.model;

import java.util.List;

import jakarta.persistence.*;

/**
 * Clase abstracta que define el comportamiento de un hechizo.
 * Todo hechizo tiene un efecto que puede afectar a uno o varios monstruos.
 * Usa herencia SINGLE_TABLE para persistencia en base de datos.
 * 
 */
@Entity
@Table(name = "hechizos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_hechizo", discriminatorType = DiscriminatorType.STRING)
public abstract class Hechizo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    /**
     * Ejecuta el efecto del hechizo en los objetivos especificados.
     * 
     * @param objetivos Lista de monstruos afectados
     * @param nivelMagia Nivel de magia del mago que lanza el hechizo
     */
    public abstract void efecto(List<Monstruo> objetivos, int nivelMagia);
    
    /**
     * Indica si el hechizo es de área de efecto (AoE).
     * 
     * @return true si afecta a múltiples objetivos, false si es de objetivo único
     */
    public abstract boolean esAoE();
    
    /**
     * Obtiene el identificador único del hechizo.
     * 
     * @return ID del hechizo
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del hechizo.
     * 
     * @return Nombre del hechizo
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del hechizo.
     * 
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

