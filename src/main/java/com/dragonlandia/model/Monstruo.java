package com.dragonlandia.model;

import jakarta.persistence.*;

/**
 * Representa un monstruo en el juego Dragonlandia.
 * Los monstruos tienen diferentes tipos que afectan su comportamiento en combate.
 * 
 */
@Entity
@Table(name = "Monstruos")
public class Monstruo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int vida;
    private int fuerza;
    @Enumerated(EnumType.STRING)
    private TipoMonstruo tipo;

    /**
     * Constructor con parámetros para crear un monstruo.
     * 
     * @param nombre Nombre del monstruo
     * @param vida Puntos de vida iniciales
     * @param fuerza Fuerza base del monstruo
     * @param tipo Tipo de monstruo (OGRO, ESPECTRO, TROLL)
     */
    public Monstruo(String nombre, int vida, int fuerza, TipoMonstruo tipo){
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.tipo = tipo;
    }
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public Monstruo(){
        
    }

    /**
     * Ataca a un mago consumiendo vida en función de su fuerza y tipo.
     * El daño se calcula multiplicando la fuerza base por un modificador según el tipo.
     * 
     * @param mago Mago que recibe el ataque
     */
    public void atacar(Mago mago){
        int daño = this.fuerza;
        
        // Modificador según tipo de monstruo
        if (this.tipo == TipoMonstruo.ESPECTRO) {
            daño = (int)(this.fuerza * 0.5); // Espectros hacen la mitad de daño
        } else if (this.tipo == TipoMonstruo.OGRO) {
            daño = (int)(this.fuerza * 1.5); // Ogros hacen más daño
        } else if (this.tipo == TipoMonstruo.TROLL) {
            daño = this.fuerza; // Trolls usan su fuerza directa
        }
        
        mago.setVida(mago.getVida() - daño); 
    }


    /**
     * Obtiene el identificador único del monstruo.
     * 
     * @return ID del monstruo
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del monstruo.
     * 
     * @return Nombre del monstruo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del monstruo.
     * 
     * @param nombre Nuevo nombre del monstruo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los puntos de vida actuales del monstruo.
     * 
     * @return Vida del monstruo
     */
    public int getVida() {
        return vida;
    }

    /**
     * Establece los puntos de vida del monstruo.
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
     * Obtiene la fuerza del monstruo.
     * 
     * @return Fuerza del monstruo
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Establece la fuerza del monstruo.
     * Si el valor es negativo, se establece a 0.
     * 
     * @param fuerza Nueva fuerza
     */
    public void setFuerza(int fuerza) {
        if (fuerza < 0) {
            this.fuerza = 0;
        } else {
            this.fuerza = fuerza;
        }
    }

    /**
     * Obtiene el tipo del monstruo.
     * 
     * @return Tipo del monstruo
     */
    public TipoMonstruo getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del monstruo.
     * 
     * @param tipo Nuevo tipo del monstruo
     */
    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

}
