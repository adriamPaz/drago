# Dragolandia
## Introducción
Proyecto implementado en Java que gestiona la interacción entre las clases mago, monstruo y bosque para simular peleas en un videojuego. 
## Análisis
#### Diagrama de clases
```mermaid
classDiagram
   Hechizo <|-- BolaFuego
   Monstruo --> Main
   Mago --> Main
   Bosque --> Main 
   Dragon --> Main
   Hechizo --> Main
   
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
    class Dragon{
      -int id
      -String nombre
      -int intensidadFuego
      -int resistencia
      +exhalar(Monster enemigo)
    }
    class Hechizo{
      +efecto()
    }
    class BolaFuego{
      +efecto()
    }
    
```
## Diseño
#### Diagrama entidad relación
```mermaid
erDiagram
DRAGON{
  int id
  String nombre
  int intensidadFuego
  int resistencia
}
MAGO{
  int id
  String nombre
  int vida
  int nivelMagia
}
HECHIZO{
  int id
  String nombre
}
MONSTRUO{
  int id
  String nombre
  int vida
  int fuerza
}
BOSQUE{
  int id
  String nombre
  int nivelPeligro
}
```
###  