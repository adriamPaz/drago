package com.dragonlandia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "monstruos")
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int vida;
    private TipoMonstruo tipoMonstruo;
    private int fuerza;

    Monster(int id, String nombre, int vida, TipoMonstruo tipoMonstruo, int fuerza){
        this.id = id;
        this.nombre = nombre;
        this.vida = vida;
        this.tipoMonstruo = tipoMonstruo;
        this.fuerza = fuerza;
    }

    public Monster(){

    }

    void atacar(Mago mago){
        mago.setVida(mago.getVida()-this.fuerza);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public TipoMonstruo getTipoMonstruo() {
        return tipoMonstruo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setTipoMonstruo(TipoMonstruo tipoMonstruo) {
        this.tipoMonstruo = tipoMonstruo;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    @Override
    public String toString() {
        String retorno = "Id: "+this.id+"\nNombre: "+this.nombre+"\nVida: "+this.vida+"\nTipo de monstruo: "+this.tipoMonstruo.name()+"\nFuerza: "+this.fuerza;
        return retorno;
    }
}