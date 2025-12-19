package com.dragonlandia.model;

import jakarta.persistence.*;


@Entity
@Table(name="Hechizos")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public class Hechizo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    String nombre;
    Hechizo(){

    }
    void efecto(){

    }
}
