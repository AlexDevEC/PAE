package com.mycompany.clase_1;

import Juego.Jugador;
import Juego.Jueg_o;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Entrada del proyecto Maven para ejecutar desde NetBeans. */
public class Clase_1 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("=== TORNEO DE REVOLVER ===");
        int cantidad = leerEntero(entrada, "Cantidad de jugadores (2-6): ", 2, 6);
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            String nombre;
            do {
                System.out.print("Nombre del jugador " + i + ": ");
                nombre = entrada.nextLine().trim();
                if (nombre.isEmpty() || nombreRepetido(jugadores, nombre))
                    System.out.println("Ingrese un nombre no vacio y diferente.");
            } while (nombre.isEmpty() || nombreRepetido(jugadores, nombre));
            jugadores.add(new Jugador(i, nombre));
        }
        int rondas = leerEntero(entrada, "Numero de rondas (1-100): ", 1, 100);
        new Jueg_o(jugadores, System.out).jugarTorneo(rondas);
    }

    private static boolean nombreRepetido(List<Jugador> jugadores, String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) return true;
        }
        return false;
    }

    private static int leerEntero(Scanner entrada, String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.nextLine().trim();
            try {
                int numero = Integer.parseInt(texto);
                if (numero >= minimo && numero <= maximo) return numero;
            } catch (NumberFormatException ex) {
                // La validacion se muestra en un unico lugar.
            }
            System.out.println("Error: ingrese un numero entre " + minimo + " y " + maximo + ".");
        }
    }
}
