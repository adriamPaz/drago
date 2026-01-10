package com.dragonlandia.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase utilidad que implementa el patrón Singleton para gestionar la conexión
 * a la base de datos mediante JPA/Hibernate.
 * Mantiene una única instancia de EntityManagerFactory compartida por toda la aplicación,
 * garantizando un uso eficiente de recursos.
 * 
 * @author Dragonlandia Team
 * @version 1.0
 */
public class HibernateUtil {

    /**
     * Instancia única y estática del EntityManagerFactory.
     * Se inicializa al cargar la clase y se mantiene durante toda la ejecución.
     * Usa la unidad de persistencia "dragolandiaServizo" definida en persistence.xml.
     */
    private static final EntityManagerFactory xestorEntidades = Persistence.createEntityManagerFactory("dragolandiaServizo");

    /**
     * Obtiene una nueva instancia de EntityManager.
     * Cada llamada crea un nuevo EntityManager que debe ser cerrado después de su uso.
     * 
     * @return Nueva instancia de EntityManager
     */
    public static EntityManager getEntityManager() {
        return xestorEntidades.createEntityManager();
    }
   
    /**
     * Cierra el EntityManagerFactory si está abierto.
     * Debe ser llamado al finalizar la aplicación para liberar recursos.
     */
    public static void close() {
        if (xestorEntidades.isOpen()) {
            xestorEntidades.close();
        }
    }

}