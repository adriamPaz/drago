# Dragolandia
## Introducción
Proyecto implementado en Java que gestiona la interacción entre las clases mago, monstruo y bosque para simular peleas en un videojuego. 
## Análisis
#### Diagrama de clases
```mermaid
classDiagram
    class Mago{
      -int id
      -String nombre
      -int vida
      -int nivelDeMagia
      +lanzarHechizo(Monster enemigo)
    }
    class Monstruo{
      -int id
      -String nombre
      -int vida
      -List-
      -int fuerza
      +atacar(Mago enemigo)
    }
    class Bosque{
      -int id
      -String nombre
      -int nivelDePeligro
      -Monstuo monstruoJefe
      +mostrarJefe()
      +cambiarJefe(Monster jefeNuevo)
    }
```
## Diseño
#### Diagrama entidad relación
### 