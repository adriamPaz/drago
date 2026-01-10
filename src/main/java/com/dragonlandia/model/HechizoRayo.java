package com.dragonlandia.model;

import java.util.List;
import jakarta.persistence.*;

/**
 * Rayo: Hechizo de objetivo único con el mayor daño individual.
 * Causa 18 * nivelMagia puntos de daño al objetivo seleccionado.
 * 
 */
@Entity
@DiscriminatorValue("RAYO")
public class HechizoRayo extends Hechizo {
    
    /**
     * Constructor que inicializa el nombre del hechizo.
     */
    public HechizoRayo() {
        setNombre("Rayo");
    }
    
    /**
     * Aplica daño eléctrico al primer monstruo de la lista.
     * 
     * @param objetivos Lista de monstruos, solo el primero recibirá daño
     * @param nivelMagia Nivel de magia del lanzador, multiplica el daño base (18)
     */
    @Override
    public void efecto(List<Monstruo> objetivos, int nivelMagia) {
        if (!objetivos.isEmpty()) {
            int daño = 18 * nivelMagia;
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
