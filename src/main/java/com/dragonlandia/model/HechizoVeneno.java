package com.dragonlandia.model;

import java.util.List;
import jakarta.persistence.*;

/**
 * Veneno: Hechizo de área que envenena a TODOS los monstruos presentes.
 * Causa 8 * nivelMagia puntos de daño a cada objetivo.
 * 
 * @author Dragonlandia Team
 * @version 1.0
 */
@Entity
@DiscriminatorValue("VENENO")
public class HechizoVeneno extends Hechizo {
    
    /**
     * Constructor que inicializa el nombre del hechizo.
     */
    public HechizoVeneno() {
        setNombre("Veneno");
    }
    
    /**
     * Aplica daño por veneno a todos los monstruos objetivo.
     * 
     * @param objetivos Lista de monstruos que recibirán daño
     * @param nivelMagia Nivel de magia del lanzador, multiplica el daño base (8)
     */
    @Override
    public void efecto(List<Monstruo> objetivos, int nivelMagia) {
        int daño = 8 * nivelMagia;
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
