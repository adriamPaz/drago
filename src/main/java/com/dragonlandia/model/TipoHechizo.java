package com.dragonlandia.model;

/**
 * Enumeración que define los tipos de hechizos disponibles.
 * Se utiliza para identificar hechizos en la persistencia.
 * 
 */
public enum TipoHechizo {
    /** Bola de fuego: ataque en área */
    BOLA_FUEGO,
    
    /** Bola de hielo: ataque a un objetivo */
    BOLA_HIELO,
    
    /** Rayo: ataque potente a un objetivo */
    RAYO,
    
    /** Curación: restaura vida en área */
    CURACION,
    
    /** Veneno: daño en área */
    VENENO
}
