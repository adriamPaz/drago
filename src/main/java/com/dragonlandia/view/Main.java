package com.dragonlandia.view;

import java.util.Scanner;

import com.dragonlandia.model.Mago;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        Scanner sc = new Scanner(System.in);

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();

            System.out.println("Bienvenido a Dragolandia");
            System.out.println("Cree un mago");
            System.out.println("Introduzca el nombre de su mago: ");
            String name = sc.nextLine();
            System.out.println("Introduzca sus puntos de vida: ");
            String stringVida = sc.nextLine();
            System.out.println("Introduzca su nivel de magia: ");
            String stringMagia = sc.nextLine();
            Mago maguito = new Mago(name,Integer.parseInt(stringVida),Integer.parseInt(stringMagia));
            
            session.persist(maguito);

            tx.commit();
            session.close();

        
        } catch (Exception e) {

        }

        
        






        sc.close();
    }
}