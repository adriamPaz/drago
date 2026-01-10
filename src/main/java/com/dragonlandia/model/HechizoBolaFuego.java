package com.dragonlandia.model;

import java.util.List;
import jakarta.persistence.*;

/**
 * Bola de Fuego: Hechizo de área que afecta a TODOS los monstruos presentes.
 * Causa 12 * nivelMagia puntos de daño a cada objetivo.
 * 
 */
@Entity
@DiscriminatorValue("BOLA_FUEGO")
public class HechizoBolaFuego extends Hechizo {
    
    /**
     * Constructor que inicializa el nombre del hechizo.
     */
    public HechizoBolaFuego() {
        setNombre("Bola de Fuego");
    }
    
    /**
     * Aplica daño de fuego a todos los monstruos objetivo.
     * 
     * @param objetivos Lista de monstruos que recibirán el daño
     * @param nivelMagia Nivel de magia del lanzador, multiplica el daño base (12)
     */
    @Override
    public void efecto(List<Monstruo> objetivos, int nivelMagia) {
        int daño = 12 * nivelMagia;
        for (Monstruo monstruo : objetivos) {
            monstruo.setVida(monstruo.getVida() - daño);
        }
    }
    
    /**
     * Indica si este hechizo es de área (AoE).
     * 
     * @return true siempre, ya que afecta a todos los objetivos
     */
    @Override
    public boolean esAoE() {
        return true;
    }
}
