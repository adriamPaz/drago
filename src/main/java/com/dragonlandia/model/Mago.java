package com.dragonlandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Representa un mago en el juego Dragonlandia.
 * Los magos pueden aprender y lanzar hechizos contra monstruos.
 * 
 */
@Entity
@Table(name = "magos")
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int vida;
    private int nivelMagia;
    
    // Colección de hechizos persistida directamente en BD
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "mago_hechizos",
        joinColumns = @JoinColumn(name = "mago_id"),
        inverseJoinColumns = @JoinColumn(name = "hechizo_id")
    )
    private List<Hechizo> conjuros = new ArrayList<>();

    /**
     * Constructor con parámetros para crear un mago.
     * 
     * @param nombre Nombre del mago
     * @param vida Puntos de vida iniciales
     * @param nivelMagia Nivel de poder mágico
     */
    public Mago(String nombre, int vida, int nivelMagia){
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Mago(){

    }
    
    /**
     * Lanza un hechizo genérico contra un monstruo.
     * Resta puntos de vida en función del nivel de magia.
     * 
     * @param monstruo Monstruo objetivo
     */
    public void lanzarHechizo(Monstruo monstruo) {
        int daño = this.nivelMagia * this.nivelMagia;
        monstruo.setVida(monstruo.getVida() - daño);
    }
    
    /**
     * Lanza un hechizo específico contra un monstruo.
     * Si el mago conoce el hechizo, aplica el efecto.
     * Si no lo conoce, pierde 1 punto de vida.
     * 
     * @param monstruo Monstruo objetivo
     * @param hechizo Hechizo a lanzar
     */
    public void lanzarHechizo(Monstruo monstruo, Hechizo hechizo) {
        if (this.conjuros.contains(hechizo)) {
            List<Monstruo> objetivos = new ArrayList<>();
            objetivos.add(monstruo);
            hechizo.efecto(objetivos, this.nivelMagia);
        } else {
            this.setVida(this.getVida() - 1);
        }
    }
    
    /**
     * Lanza un hechizo contra varios monstruos.
     * Si el mago conoce el hechizo, aplica el efecto.
     * Si no lo conoce, pierde 1 punto de vida.
     * 
     * @param monstruos Lista de monstruos afectados
     * @param hechizo Hechizo a lanzar
     */
    public void lanzarHechizo(List<Monstruo> monstruos, Hechizo hechizo) {
        if (this.conjuros.contains(hechizo)) {
            hechizo.efecto(monstruos, this.nivelMagia);
        } else {
            this.setVida(this.getVida() - 1);
        }
    }
    
    /**
     * Aprende un nuevo hechizo.
     * 
     * @param hechizo Hechizo a aprender
     */
    public void aprenderHechizo(Hechizo hechizo) {
        if (!this.conjuros.contains(hechizo)) {
            this.conjuros.add(hechizo);
        }
    }
    
    /**
     * Obtiene la lista de hechizos conocidos por el mago.
     * 
     * @return Lista de conjuros
     */
    public List<Hechizo> getConjuros() {
        return conjuros;
    }
    
    /**
     * Establece la lista de hechizos del mago.
     * 
     * @param conjuros Nueva lista de conjuros
     */
    public void setConjuros(List<Hechizo> conjuros) {
        this.conjuros = conjuros;
    }
    
    /**
     * Añade un conjuro a la lista de hechizos del mago.
     * 
     * @param hechizo Hechizo a añadir
     */
    public void addConjuro(Hechizo hechizo) {
        aprenderHechizo(hechizo);
    }
    
    /**
     * Obtiene el identificador único del mago.
     * 
     * @return ID del mago
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del mago.
     * 
     * @return Nombre del mago
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del mago.
     * 
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene los puntos de vida actuales del mago.
     * 
     * @return Vida del mago
     */
    public int getVida() {
        return vida;
    }
    
    /**
     * Establece los puntos de vida del mago.
     * Si el valor es negativo, se establece a 0.
     * 
     * @param vida Nuevos puntos de vida
     */
    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }
    
    /**
     * Obtiene el nivel de magia del mago.
     * 
     * @return Nivel de magia
     */
    public int getNivelMagia() {
        return nivelMagia;
    }
    
    /**
     * Establece el nivel de magia del mago.
     * Si el valor es negativo, se establece a 0.
     * 
     * @param nivelMagia Nuevo nivel de magia
     */
    public void setNivelMagia(int nivelMagia) {
        if (nivelMagia < 0) {
            this.nivelMagia = 0;
        } else {
            this.nivelMagia = nivelMagia;
        }
    }
}

