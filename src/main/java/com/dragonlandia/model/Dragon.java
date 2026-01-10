package com.dragonlandia.model;

import jakarta.persistence.*;

/**
 * Representa un dragón en el juego Dragonlandia.
 * Los dragones pueden exhalar fuego contra los monstruos.
 * 
 */
@Entity
@Table(name = "dragones")
public class Dragon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private int intensidadFuego;
    private int resistencia;
    
    /**
     * Constructor con parámetros para crear un dragón.
     * 
     * @param nombre Nombre del dragón
     * @param intensidadFuego Daño que causa el aliento de fuego
     * @param resistencia Puntos de resistencia del dragón
     */
    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
    }
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public Dragon(){

    }
    
    /**
     * El dragón exhala fuego contra un monstruo.
     * 
     * @param enemigo Monstruo que recibe el ataque
     */
    public void exhalar(Monstruo enemigo){
        enemigo.setVida(enemigo.getVida() - this.intensidadFuego);
    }
    
    /**
     * Obtiene el identificador único del dragón.
     * 
     * @return ID del dragón
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del dragón.
     * 
     * @return Nombre del dragón
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del dragón.
     * 
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la intensidad del fuego del dragón.
     * 
     * @return Intensidad de fuego
     */
    public int getIntensidadFuego() {
        return intensidadFuego;
    }
    
    /**
     * Establece la intensidad del fuego del dragón.
     * Si el valor es negativo, se establece a 0.
     * 
     * @param intensidadFuego Nueva intensidad de fuego
     */
    public void setIntensidadFuego(int intensidadFuego) {
        if (intensidadFuego < 0) {
            this.intensidadFuego = 0;
        } else {
            this.intensidadFuego = intensidadFuego;
        }
    }
    
    /**
     * Obtiene la resistencia del dragón.
     * 
     * @return Resistencia del dragón
     */
    public int getResistencia() {
        return resistencia;
    }
    
    /**
     * Establece la resistencia del dragón.
     * Si el valor es negativo, se establece a 0.
     * 
     * @param resistencia Nueva resistencia
     */
    public void setResistencia(int resistencia) {
        if (resistencia < 0) {
            this.resistencia = 0;
        } else {
            this.resistencia = resistencia;
        }
    }
}
