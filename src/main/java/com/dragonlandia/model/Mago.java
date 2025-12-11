package com.dragonlandia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "magos")
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int vida;
    private int nivelMagia;

    public Mago(String nombre, int vida, int nivelMagia){
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
    }

    public Mago(){

    }
    void lanzarHechizo(Monster monstruo){
        monstruo.setVida(monstruo.getVida()-this.nivelMagia);
    }

    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        if (vida<0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }
    public int getNivelMagia() {
        return nivelMagia;
    }
    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }


}
