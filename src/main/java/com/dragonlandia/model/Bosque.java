package com.dragonlandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Clase que representa un bosque en Dragonlandia.
 * Cada bosque tiene un nivel de peligro, un monstruo jefe y una lista de monstruos.
 * 
 */
@Entity
@Table(name = "bosques")
public class Bosque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    private int nivelPeligro;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monstruo_jefe_id")
    private Monstruo monstruoJefe;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bosque_id")
    private List<Monstruo> monstruos = new ArrayList<>();
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public Bosque() {
    }
    
    /**
     * Constructor con parámetros para crear un bosque.
     * 
     * @param nombre Nombre del bosque
     * @param nivelPeligro Nivel de peligrosidad del bosque
     */
    public Bosque(String nombre, int nivelPeligro) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
    }
    
    /**
     * Muestra los datos del monstruo jefe.
     */
    public void mostrarJefe() {
        if (this.monstruoJefe != null) {
            System.out.println("=== JEFE DEL BOSQUE ===");
            System.out.println("Nombre: " + this.monstruoJefe.getNombre());
            System.out.println("Vida: " + this.monstruoJefe.getVida());
            System.out.println("Fuerza: " + this.monstruoJefe.getFuerza());
            System.out.println("Tipo: " + this.monstruoJefe.getTipo());
        } else {
            System.out.println("Este bosque no tiene jefe asignado.");
        }
    }
    
    /**
     * Permite asignar un nuevo monstruo jefe al bosque.
     * 
     * @param nuevoJefe Monstruo que será el nuevo jefe
     */
    public void cambiarJefe(Monstruo nuevoJefe) {
        this.monstruoJefe = nuevoJefe;
    }
    
    /**
     * Asigna un monstruo al bosque.
     * 
     * @param monstruo Monstruo a añadir al bosque
     */
    public void addMonstruo(Monstruo monstruo) {
        if (!this.monstruos.contains(monstruo)) {
            this.monstruos.add(monstruo);
        }
    }
    
    // Getters y Setters
    
    /**
     * Obtiene el identificador único del bosque.
     * 
     * @return ID del bosque
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del bosque.
     * 
     * @return Nombre del bosque
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del bosque.
     * 
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el nivel de peligro del bosque.
     * 
     * @return Nivel de peligrosidad
     */
    public int getNivelPeligro() {
        return nivelPeligro;
    }
    
    /**
     * Establece el nivel de peligro del bosque.
     * 
     * @param nivelPeligro Nuevo nivel de peligro
     */
    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }
    
    /**
     * Obtiene el monstruo jefe del bosque.
     * 
     * @return Monstruo jefe
     */
    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }
    
    /**
     * Establece el monstruo jefe del bosque.
     * 
     * @param monstruoJefe Nuevo monstruo jefe
     */
    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }
    
    /**
     * Obtiene la lista de monstruos del bosque.
     * 
     * @return Lista de monstruos
     */
    public List<Monstruo> getMonstruos() {
        return monstruos;
    }
    
    /**
     * Establece la lista de monstruos del bosque.
     * 
     * @param monstruos Nueva lista de monstruos
     */
    public void setMonstruos(List<Monstruo> monstruos) {
        this.monstruos = monstruos;
    }
}
