package com.dragonlandia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dragones")
public class Dragon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int intensidadFuego;
    private int resistencia;

    void exhalar(Monster enemigo){
        
    }
}
