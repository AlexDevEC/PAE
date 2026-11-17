package Juego;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Coordina las partidas, registra sus resultados y calcula la tabla final. */
public class Jueg_o {
    private final List<Jugador> jugadores;
    private final List<String> historial = new ArrayList<>();
    private final PrintStream salida;
    private int partidasJugadas;

    public Jueg_o(List<Jugador> jugadores, PrintStream salida) {
        if (jugadores == null || jugadores.size() < 2 || jugadores.size() > 6)
            throw new IllegalArgumentException("Se requieren entre 2 y 6 jugadores");
        this.jugadores = new ArrayList<>(jugadores);
        this.salida = salida;
    }

    public void jugarTorneo(int cantidadRondas) {
        if (cantidadRondas < 1 || cantidadRondas > 100)
            throw new IllegalArgumentException("Las rondas deben estar entre 1 y 100");
        for (int i = 1; i <= cantidadRondas; i++) {
            jugarPartida(i, new Revolver());
        }
        mostrarResultados();
    }

    /** Partida independiente: todos reviven y el tambor se reinicia. */
    public void jugarPartida(int ronda, Revolver revolver) {
        for (Jugador jugador : jugadores) jugador.reiniciar();
        salida.println("\n=== RONDA " + ronda + " ===");
        int turno = 1;
        while (contarVivos() > 1) {
            for (Jugador jugador : jugadores) {
                if (!jugador.isVivo()) continue;
                boolean sobrevivio = jugador.disparar(revolver);
                salida.println("Turno " + turno + " - " + jugador.getNombre()
                        + ": " + (sobrevivio ? "sobrevive" : "eliminado"));
                if (contarVivos() == 1) break;
            }
            turno++;
        }
        Jugador ganador = buscarGanador();
        ganador.registrarVictoria();
        partidasJugadas++;
        String resultado = "Ronda " + ronda + ": " + ganador.getNombre() + " (+3 puntos)";
        historial.add(resultado);
        salida.println("Ganador de la ronda: " + ganador.getNombre());
    }

    private int contarVivos() {
        int vivos = 0;
        for (Jugador jugador : jugadores) if (jugador.isVivo()) vivos++;
        return vivos;
    }

    private Jugador buscarGanador() {
        for (Jugador jugador : jugadores) if (jugador.isVivo()) return jugador;
        throw new IllegalStateException("No hay ganador");
    }

    public List<Jugador> clasificacion() {
        List<Jugador> ranking = new ArrayList<>(jugadores);
        ranking.sort(Comparator.comparingInt(Jugador::getPuntos).reversed()
                .thenComparing(Jugador::getNombre, String.CASE_INSENSITIVE_ORDER));
        return ranking;
    }

    public List<String> getHistorial() { return Collections.unmodifiableList(historial); }
    public int getPartidasJugadas() { return partidasJugadas; }

    public void mostrarResultados() {
        salida.println("\n=== CLASIFICACION FINAL ===");
        int puesto = 1;
        for (Jugador jugador : clasificacion()) {
            salida.println(puesto++ + ". " + jugador);
        }
        int maximo = clasificacion().get(0).getPuntos();
        List<String> campeones = new ArrayList<>();
        for (Jugador j : clasificacion()) {
            if (j.getPuntos() == maximo) campeones.add(j.getNombre());
        }
        salida.println("Campeon(es): " + String.join(", ", campeones));
        salida.println("Partidas jugadas: " + partidasJugadas);
        salida.println("Historial:");
        for (String registro : historial) salida.println("- " + registro);
    }
}
