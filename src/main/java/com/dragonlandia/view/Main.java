package com.dragonlandia.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dragonlandia.controller.*;
import com.dragonlandia.model.*;
import com.dragonlandia.util.HibernateUtil;

/**
 * Clase principal del juego Dragonlandia.
 * Implementa la Vista del patrón MVC y gestiona el flujo del juego:
 * - Inicialización de entidades mediante entrada del usuario (magos, monstruos, dragón, bosque)
 * - Bucle principal de combate con 3 fases por ronda:
 *   1. Magos lanzan hechizos seleccionados por el usuario
 *   2. Monstruos atacan a magos seleccionados por el usuario
 *   3. Dragón ataca al monstruo jefe
 * - Condiciones de victoria: todos los monstruos muertos (magos ganan) o todos los magos muertos (monstruos ganan)
 * 
 * @author Dragonlandia Team
 * @version 1.0
 */
public class Main {
    
    /**
     * Scanner para leer entrada del usuario.
     */
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Método principal que ejecuta el juego completo.
     * 
     * Flujo de ejecución:
     * 1. Inicialización: solicita datos al usuario para crear bosque, monstruos, jefe, dragón y magos con hechizos
     * 2. Bucle principal: ejecuta rondas hasta que un bando sea eliminado
     * 3. Finalización: muestra ganador y cierra conexión a BD
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        
        System.out.println("==============================================");
        System.out.println("    BIENVENIDO A DRAGONLANDIA");
        System.out.println("==============================================\n");
        
        // ==================== INICIO DEL JUEGO ====================
        
        // 1. Crear un bosque
        System.out.println("--- CREAR BOSQUE ---");
        System.out.print("Nombre del bosque: ");
        String nombreBosque = scanner.nextLine();
        System.out.print("Nivel de peligro (1-10): ");
        int nivelPeligro = leerEntero();
        
        Bosque bosque = new Bosque(nombreBosque, nivelPeligro);
        ControladorBosque ctrlBosque = new ControladorBosque();
        ctrlBosque.setBosque(bosque);
        ctrlBosque.añadirBosque();
        System.out.println("Bosque creado: " + bosque.getNombre() + "\n");
        
        // 2. Crear mínimo 3 monstruos
        System.out.print("¿Cuántos monstruos quieres crear? (mínimo 3): ");
        int numMonstruos = leerEntero();
        if (numMonstruos < 3) numMonstruos = 3;
        
        List<ControladorMonstruo> controladorMonstruos = new ArrayList<>();
        
        for (int i = 1; i <= numMonstruos; i++) {
            System.out.println("\n--- MONSTRUO " + i + " ---");
            System.out.print("Nombre: ");
            String nombreM = scanner.nextLine();
            System.out.print("Vida: ");
            int vidaM = leerEntero();
            System.out.print("Fuerza: ");
            int fuerzaM = leerEntero();
            System.out.println("Tipo (1=OGRO, 2=ESPECTRO, 3=TROLL): ");
            int tipoOpcion = leerEntero();
            TipoMonstruo tipo = TipoMonstruo.TROLL;
            switch(tipoOpcion) {
                case 1: tipo = TipoMonstruo.OGRO; break;
                case 2: tipo = TipoMonstruo.ESPECTRO; break;
                case 3: tipo = TipoMonstruo.TROLL; break;
            }
            
            Monstruo m = new Monstruo(nombreM, vidaM, fuerzaM, tipo);
            ControladorMonstruo ctrlM = new ControladorMonstruo();
            ctrlM.setMonstruo(m);
            ctrlM.añadirMonstruo();
            controladorMonstruos.add(ctrlM);
        }
        
        // Añadir monstruos al bosque después de crearlos
        for (ControladorMonstruo ctrlM : controladorMonstruos) {
            bosque.getMonstruos().add(ctrlM.getMonstruo());
        }
        
        System.out.println("\n" + numMonstruos + " Monstruos creados y asignados al bosque");
        
        // 3. Asignar monstruo jefe
        System.out.println("\n--- ASIGNAR JEFE DEL BOSQUE ---");
        for (int i = 0; i < controladorMonstruos.size(); i++) {
            System.out.println((i+1) + ". " + controladorMonstruos.get(i).getMonstruo().getNombre());
        }
        System.out.print("Elige el número del jefe: ");
        int jefeIndex = leerEntero() - 1;
        if (jefeIndex < 0 || jefeIndex >= controladorMonstruos.size()) jefeIndex = 0;
        
        bosque.setMonstruoJefe(controladorMonstruos.get(jefeIndex).getMonstruo());
        ctrlBosque.actualizarBosque();
        System.out.println("Jefe del bosque asignado: " + controladorMonstruos.get(jefeIndex).getMonstruo().getNombre());
        
        // 4. Crear un dragón
        System.out.println("\n--- CREAR DRAGÓN ---");
        System.out.print("Nombre del dragón: ");
        String nombreDragon = scanner.nextLine();
        System.out.print("Intensidad de fuego: ");
        int intensidadFuego = leerEntero();
        System.out.print("Resistencia: ");
        int resistenciaDragon = leerEntero();
        
        Dragon dragon = new Dragon(nombreDragon, intensidadFuego, resistenciaDragon);
        ControladorDragon ctrlDragon = new ControladorDragon();
        ctrlDragon.setDragon(dragon);
        ctrlDragon.añadirDragon();
        System.out.println("Dragón creado: " + dragon.getNombre());
        
        // 5. Crear mínimo 2 magos y asignarles mínimo 2 conjuros
        System.out.print("\n¿Cuántos magos quieres crear? (mínimo 2): ");
        int numMagos = leerEntero();
        if (numMagos < 2) numMagos = 2;
        
        List<ControladorMago> controladorMagos = new ArrayList<>();
        
        // Lista de todos los hechizos disponibles
        List<Hechizo> hechizosDisponibles = new ArrayList<>();
        hechizosDisponibles.add(new HechizoBolaFuego());
        hechizosDisponibles.add(new HechizoBolaHielo());
        hechizosDisponibles.add(new HechizoRayo());
        hechizosDisponibles.add(new HechizoCuracion());
        hechizosDisponibles.add(new HechizoVeneno());
        
        for (int i = 1; i <= numMagos; i++) {
            System.out.println("\n--- MAGO " + i + " ---");
            System.out.print("Nombre: ");
            String nombreMago = scanner.nextLine();
            System.out.print("Vida: ");
            int vidaMago = leerEntero();
            System.out.print("Nivel de magia: ");
            int nivelMagia = leerEntero();
            
            Mago mago = new Mago(nombreMago, vidaMago, nivelMagia);
            ControladorMago ctrlMago = new ControladorMago();
            ctrlMago.setMago(mago);
            ctrlMago.añadirMago();
            
            // Asignar hechizos
            System.out.print("¿Cuántos hechizos aprenderá? (mínimo 2): ");
            int numHechizos = leerEntero();
            if (numHechizos < 2) numHechizos = 2;
            
            for (int j = 0; j < numHechizos; j++) {
                System.out.println("\nHechizos disponibles:");
                System.out.println("1. Bola de Fuego (AoE, 12*nivelMagia)");
                System.out.println("2. Bola de Hielo (1 objetivo, 15*nivelMagia)");
                System.out.println("3. Rayo (1 objetivo, 18*nivelMagia)");
                System.out.println("4. Curación (AoE, +10*nivelMagia)");
                System.out.println("5. Veneno (AoE, 8*nivelMagia)");
                System.out.print("Elige hechizo " + (j+1) + ": ");
                int hechizoOpcion = leerEntero();
                
                Hechizo hechizoSeleccionado = null;
                switch(hechizoOpcion) {
                    case 1: hechizoSeleccionado = new HechizoBolaFuego(); break;
                    case 2: hechizoSeleccionado = new HechizoBolaHielo(); break;
                    case 3: hechizoSeleccionado = new HechizoRayo(); break;
                    case 4: hechizoSeleccionado = new HechizoCuracion(); break;
                    case 5: hechizoSeleccionado = new HechizoVeneno(); break;
                    default: hechizoSeleccionado = new HechizoBolaFuego();
                }
                
                ctrlMago.aprenderHechizo(hechizoSeleccionado);
            }
            
            controladorMagos.add(ctrlMago);
        }
        
        System.out.println("\n" + numMagos + " Magos creados con conjuros asignados\n");
        
        // ==================== BUCLE PRINCIPAL ====================
        
        int ronda = 1;
        
        while (!controladorMagos.isEmpty() && !controladorMonstruos.isEmpty()) {
            
            System.out.println("\n==============================================");
            System.out.println("           RONDA " + ronda);
            System.out.println("==============================================\n");
            
            // a) Cada mago lanza un conjuro
            System.out.println("--- FASE 1: MAGOS LANZAN HECHIZOS ---");
            for (int i = controladorMagos.size() - 1; i >= 0; i--) {
                ControladorMago ctrlMago = controladorMagos.get(i);
                Mago mago = ctrlMago.getMago();
                
                if (mago.getVida() > 0) {
                    System.out.println("\nTurno de " + mago.getNombre() + " (Vida: " + mago.getVida() + ")");
                    System.out.println("Hechizos conocidos:");
                    List<Hechizo> conjuros = mago.getConjuros();
                    for (int j = 0; j < conjuros.size(); j++) {
                        System.out.println((j+1) + ". " + conjuros.get(j).getNombre());
                    }
                    System.out.print("Elige un hechizo (1-" + conjuros.size() + "): ");
                    int hechizoIndex = leerEntero() - 1;
                    if (hechizoIndex < 0 || hechizoIndex >= conjuros.size()) hechizoIndex = 0;
                    
                    Hechizo hechizoSeleccionado = conjuros.get(hechizoIndex);
                    
                    // Lanzar contra todos los monstruos vivos
                    List<Monstruo> monstruosVivos = new ArrayList<>();
                    for (ControladorMonstruo ctrlM : controladorMonstruos) {
                        monstruosVivos.add(ctrlM.getMonstruo());
                    }
                    
                    ctrlMago.lanzarHechizo(monstruosVivos, hechizoSeleccionado);
                    System.out.println(mago.getNombre() + " lanza " + hechizoSeleccionado.getNombre() + "!");
                    
                    // Verificar si murió
                    if (mago.getVida() == 0) {
                        System.out.println("   [X] " + mago.getNombre() + " ha muerto!");
                        controladorMagos.remove(i);
                    }
                }
            }
            
            // Actualizar controladores de monstruos (eliminar muertos)
            for (int i = controladorMonstruos.size() - 1; i >= 0; i--) {
                ControladorMonstruo ctrlM = controladorMonstruos.get(i);
                if (ctrlM.getMonstruo().getVida() <= 0) {
                    Monstruo monstruoMuerto = ctrlM.getMonstruo();
                    System.out.println("   [X] " + monstruoMuerto.getNombre() + " ha muerto!");
                    
                    // Si el monstruo es el jefe, usar el método seguro del controlador
                    if (bosque.getMonstruoJefe() != null && bosque.getMonstruoJefe().getId() == monstruoMuerto.getId()) {
                        ctrlBosque.eliminarJefe(monstruoMuerto);
                    } else {
                        ctrlM.eliminarMonstruo();
                    }
                    
                    controladorMonstruos.remove(i);
                }
            }
            
            if (controladorMonstruos.isEmpty()) break;
            
            // b) Cada monstruo ataca a un mago
            System.out.println("\n--- FASE 2: MONSTRUOS ATACAN ---");
            for (ControladorMonstruo ctrlM : controladorMonstruos) {
                Monstruo monstruo = ctrlM.getMonstruo();
                
                if (!controladorMagos.isEmpty()) {
                    System.out.println("\n" + monstruo.getNombre() + " ataca:");
                    for (int j = 0; j < controladorMagos.size(); j++) {
                        System.out.println((j+1) + ". " + controladorMagos.get(j).getMago().getNombre() + 
                                         " (Vida: " + controladorMagos.get(j).getMago().getVida() + ")");
                    }
                    System.out.print("Elige objetivo (1-" + controladorMagos.size() + "): ");
                    int objetivoIndex = leerEntero() - 1;
                    if (objetivoIndex < 0 || objetivoIndex >= controladorMagos.size()) objetivoIndex = 0;
                    
                    ControladorMago ctrlMagoObjetivo = controladorMagos.get(objetivoIndex);
                    Mago magoObjetivo = ctrlMagoObjetivo.getMago();
                    
                    System.out.println(monstruo.getNombre() + " ataca a " + magoObjetivo.getNombre());
                    ctrlM.atacar(magoObjetivo);
                    ctrlMagoObjetivo.actualizarMago();
                    
                    if (magoObjetivo.getVida() <= 0) {
                        System.out.println("   [X] " + magoObjetivo.getNombre() + " ha muerto!");
                        controladorMagos.remove(ctrlMagoObjetivo);
                    }
                }
            }
            
            if (controladorMagos.isEmpty()) break;
            
            // c) El dragón ataca al monstruo jefe
            System.out.println("\n--- FASE 3: DRAGON ATACA AL JEFE ---");
            if (dragon.getResistencia() > 0 && bosque.getMonstruoJefe() != null) {
                Monstruo jefe = bosque.getMonstruoJefe();
                System.out.println(dragon.getNombre() + " exhala fuego contra " + jefe.getNombre());
                ctrlDragon.exhalar(jefe);
                
                // Buscar controlador del jefe
                ControladorMonstruo ctrlJefe = null;
                for (ControladorMonstruo ctrlM : controladorMonstruos) {
                    if (ctrlM.getMonstruo().getId() == jefe.getId()) {
                        ctrlJefe = ctrlM;
                        break;
                    }
                }
                
                if (ctrlJefe != null) {
                    ctrlJefe.actualizarMonstruo();
                    
                    if (jefe.getVida() <= 0) {
                        System.out.println("   [X] El jefe " + jefe.getNombre() + " ha muerto!");
                        
                        // Usar el método seguro del controlador para eliminar al jefe
                        ctrlBosque.eliminarJefe(jefe);
                        controladorMonstruos.remove(ctrlJefe);
                        
                        // Asignar nuevo jefe si quedan monstruos
                        if (!controladorMonstruos.isEmpty()) {
                            Monstruo nuevoJefe = controladorMonstruos.get(0).getMonstruo();
                            bosque.setMonstruoJefe(nuevoJefe);
                            ctrlBosque.actualizarBosque();
                            System.out.println("   [!] Nuevo jefe asignado: " + nuevoJefe.getNombre());
                        }
                    }
                }
            }
            
            // Mostrar estado después de la ronda
            mostrarEstado(controladorMagos, controladorMonstruos, ctrlDragon, ctrlBosque);
            
            ronda++;
            
            System.out.print("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
        
        // ==================== FIN DEL JUEGO ====================
        
        System.out.println("\n==============================================");
        System.out.println("           FIN DEL JUEGO");
        System.out.println("==============================================\n");
        
        if (controladorMagos.isEmpty()) {
            System.out.println("Todos los magos han muerto. Los monstruos ganan!");
        } else {
            System.out.println("Todos los monstruos han sido derrotados. Los magos ganan!");
        }
        
        // Cerrar HibernateUtil y Scanner
        scanner.close();
        HibernateUtil.close();
    }
    
    /**
     * Lee un número entero del scanner, validando la entrada.
     * 
     * @return Número entero válido ingresado por el usuario
     */
    private static int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor ingresa un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        return valor;
    }
    
    /**
     * Muestra el estado actual de todos los personajes del juego.
     * Imprime información de magos vivos, monstruos vivos (indicando cuál es el jefe),
     * estado del dragón y el jefe actual del bosque.
     * 
     * @param magos Lista de controladores de magos vivos
     * @param monstruos Lista de controladores de monstruos vivos
     * @param dragon Controlador del dragón
     * @param bosque Controlador del bosque
     */
    private static void mostrarEstado(List<ControladorMago> magos, List<ControladorMonstruo> monstruos, 
                                      ControladorDragon dragon, ControladorBosque bosque) {
        System.out.println("\n==============================================");
        System.out.println("        ESTADO ACTUAL DEL JUEGO");
        System.out.println("==============================================");
        
        System.out.println("\nMAGOS VIVOS:");
        if (magos.isEmpty()) {
            System.out.println("  (Ninguno)");
        } else {
            for (ControladorMago ctrlM : magos) {
                Mago m = ctrlM.getMago();
                System.out.println("  - " + m.getNombre() + " | Vida: " + m.getVida() + " | Magia: " + m.getNivelMagia());
            }
        }
        
        System.out.println("\nMONSTRUOS VIVOS:");
        if (monstruos.isEmpty()) {
            System.out.println("  (Ninguno)");
        } else {
            for (ControladorMonstruo ctrlM : monstruos) {
                Monstruo m = ctrlM.getMonstruo();
                String esJefe = (bosque.getBosque().getMonstruoJefe() != null && 
                                 bosque.getBosque().getMonstruoJefe().getId() == m.getId()) ? " [JEFE]" : "";
                System.out.println("  - " + m.getNombre() + " | Vida: " + m.getVida() + 
                                 " | Tipo: " + m.getTipo() + esJefe);
            }
        }
        
        System.out.println("\nDRAGON:");
        if (dragon.getDragon().getResistencia() > 0) {
            System.out.println("  - " + dragon.getDragon().getNombre() + " | Resistencia: " + 
                             dragon.getDragon().getResistencia());
        } else {
            System.out.println("  [X] (Muerto)");
        }
        
        if (bosque.getBosque().getMonstruoJefe() != null) {
            System.out.println("\nJEFE DEL BOSQUE: " + bosque.getBosque().getMonstruoJefe().getNombre());
        }
        
        System.out.println("==============================================\n");
    }
}
