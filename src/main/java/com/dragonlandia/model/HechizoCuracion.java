package com.dragonlandia.model;

import java.util.List;
import jakarta.persistence.*;

/**
 * Curación: Hechizo de área que restaura vida a TODOS los monstruos.
 * Suma 10 * nivelMagia puntos de vida a cada objetivo.
 * Efecto positivo para los monstruos.
 * 
 * @author Dragonlandia Team
 * @version 1.0
 */
@Entity
@DiscriminatorValue("CURACION")
public class HechizoCuracion extends Hechizo {
    
    /**
     * Constructor que inicializa el nombre del hechizo.
     */
    public HechizoCuracion() {
        setNombre("Curación");
    }
    
    /**
     * Restaura vida a todos los monstruos objetivo.
     * 
     * @param objetivos Lista de monstruos que recibirán curación
     * @param nivelMagia Nivel de magia del lanzador, multiplica la curación base (10)
     */
    @Override
    public void efecto(List<Monstruo> objetivos, int nivelMagia) {
        int vidaCurada = 10 * nivelMagia;
        for (Monstruo monstruo : objetivos) {
            monstruo.setVida(monstruo.getVida() + vidaCurada);
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
