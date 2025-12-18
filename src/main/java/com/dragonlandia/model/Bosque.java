package com.dragonlandia.model;
import java.util.ArrayDeque;

import jakarta.persistence.*;

@Entity
@Table(name = "bosques")
public class Bosque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int nivelPeligro;
    private Monster monstruoJefe;
    private ArrayDeque<Monster> listaMonstruos = new ArrayDeque<Monster>();

    public Bosque(int id, String nombre, int nivelPeligro, Monster monstruoJefe) {
        this.id = id;
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
    }

    public Bosque() {
    }

    void monstrarJefe(){
        System.out.println(this.monstruoJefe);
    }

    

    void cambiarJefe(Monster newBoss){
        this.setMonstruoJefe(newBoss);
    }

    void addMonstruo(Monster monstro){
        listaMonstruos.add(monstro);
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
    public int getNivelPeligro() {
        return nivelPeligro;
    }
    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }
    public Monster getMonstruoJefe() {
        return monstruoJefe;
    }
    public void setMonstruoJefe(Monster monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    
}
