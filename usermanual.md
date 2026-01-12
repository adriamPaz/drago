# Manual de Usuario - Dragolandia

## Requisitos Técnicos

- **Java** instalado en el sistema
- **Docker** y **docker compose** instalado
- Ejecuta el comando: `docker compose up -d` antes de iniciar el juego
- Inicia el juego ejecutando la clase `Main.java`

## Inicio del Juego

### 1. Configuración Inicial

#### **Crear el Bosque**
- Introduce el **nombre del bosque** 
- Define el **nivel de peligro** (1-10): cuanto mayor, más peligroso

#### **Crear Monstruos (mínimo 3)**
Para cada monstruo necesitas:
- **Nombre**: identifica al monstruo
- **Vida**: puntos de vida iniciales
- **Fuerza**: daño que inflige en cada ataque
- **Tipo**: elige entre:
  - `1` - OGRO
  - `2` - ESPECTRO
  - `3` - TROLL

#### **Asignar Jefe del Bosque**
- Selecciona uno de los monstruos creados como **jefe del bosque**
- El jefe será el objetivo prioritario del dragón

#### **Crear el Dragón**
- **Nombre**: nombre de tu dragón aliado
- **Intensidad de fuego**: poder de ataque del dragón
- **Resistencia**: vida del dragón

#### **Crear Magos (mínimo 2)**
Para cada mago:
- **Nombre**: identifica al mago
- **Vida**: puntos de vida iniciales
- **Nivel de magia**: multiplica el daño/curación de los hechizos
- **Hechizos** (mínimo 2): elige entre:
  - `1` **Bola de Fuego**: daño en área (12 × nivel de magia)
  - `2` **Bola de Hielo**: daño individual (15 × nivel de magia)
  - `3` **Rayo**: daño individual potente (18 × nivel de magia)
  - `4` **Curación**: restaura vida en área (+10 × nivel de magia)
  - `5` **Veneno**: daño en área (8 × nivel de magia)

## Mecánica de Combate

### Estructura de Rondas
Cada ronda tiene **3 fases**:

#### **Fase 1: Magos Lanzan Hechizos**
- Por cada mago vivo:
  1. Se muestra su vida actual y hechizos disponibles
  2. Selecciona un hechizo (introduce el número)
  3. El hechizo afecta a **todos los monstruos vivos**
  4. Se actualiza el estado (monstruos muertos se eliminan)

#### **Fase 2: Monstruos Atacan**
- Por cada monstruo vivo:
  1. Se muestra la lista de magos disponibles
  2. Selecciona el objetivo (introduce el número del mago)
  3. El monstruo ataca al mago seleccionado
  4. Si un mago muere, se elimina del juego

#### **Fase 3: Dragón Ataca al Jefe**
- El dragón automáticamente ataca al **monstruo jefe** del bosque
- Si el jefe muere, se asigna automáticamente un **nuevo jefe** entre los monstruos restantes
- Si el dragón muere, ya no participará en el combate

### Visualización del Estado
Al finalizar cada ronda, se muestra:
- **Magos vivos** con su vida y nivel de magia
- **Monstruos vivos** con su vida y tipo (se marca al jefe)
- **Estado del dragón** con su resistencia

## Condiciones de Victoria

### Victoria de los Magos
Si **todos los monstruos** son eliminados.

### Victoria de los Monstruos
Si **todos los magos** mueren.

## Consejos Estratégicos

1. **Hechizos de área** (Bola de Fuego, Veneno): efectivos contra múltiples enemigos
2. **Hechizos individuales** (Rayo, Bola de Hielo): elimina enemigos específicos rápidamente
3. **Curación**: mantiene vivos a tus magos más tiempo
4. **Protege a tus magos**: distribuye los ataques de monstruos entre varios magos
5. **El dragón es clave**: ayuda a eliminar al jefe automáticamente cada ronda

## Controles

- Introduce **números** según las opciones mostradas
- Presiona **Enter** para continuar entre rondas
- El juego valida automáticamente las entradas incorrectas


