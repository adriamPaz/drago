package com.dragonlandia.model;

import java.util.List;
import jakarta.persistence.*;

/**
 * Bola de Hielo: Hechizo de objetivo único que afecta solo al primer enemigo.
 * Causa 15 * nivelMagia puntos de daño al objetivo seleccionado.
 * 
 * @author Dragonlandia Team
 * @version 1.0
 */
@Entity
@DiscriminatorValue("BOLA_HIELO")
public class HechizoBolaHielo extends Hechizo {
    
    /**
     * Constructor que inicializa el nombre del hechizo.
     */
    public HechizoBolaHielo() {
        setNombre("Bola de Hielo");
    }
    
    /**
     * Aplica daño de hielo al primer monstruo de la lista.
     * 
     * @param objetivos Lista de monstruos, solo el primero recibirá daño
     * @param nivelMagia Nivel de magia del lanzador, multiplica el daño base (15)
     */
    @Override
    public void efecto(List<Monstruo> objetivos, int nivelMagia) {
        if (!objetivos.isEmpty()) {
            int daño = 15 * nivelMagia;
            objetivos.get(0).setVida(objetivos.get(0).getVida() - daño);
        }
    }
    
    /**
     * Indica si este hechizo es de área (AoE).
     * 
     * @return false, ya que solo afecta a un objetivo
     */
    @Override
    public boolean esAoE() {
        return false;
    }
}
