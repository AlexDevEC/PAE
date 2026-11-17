import Juego.*;
import java.io.*;
import java.util.*;

public class Pruebas {
    static class RandomFijo extends Random {
        private int indice = 0;
        @Override public int nextInt(int limite) { return indice++ == 0 ? 0 : 5; }
    }
    public static void main(String[] args) {
        Revolver revolver = new Revolver(new RandomFijo());
        for (int i = 0; i < 5; i++) {
            if (revolver.disparar()) throw new AssertionError("Disparo prematuro");
        }
        if (!revolver.disparar()) throw new AssertionError("Fallo del ciclo de seis posiciones");
        Jugador ana = new Jugador(1, "Ana");
        Jugador luis = new Jugador(2, "Luis");
        ana.registrarVictoria();
        if (ana.getPuntos() != 3 || ana.getVictorias() != 1) throw new AssertionError("Puntuacion");
        Jueg_o juego = new Jueg_o(Arrays.asList(ana, luis), new PrintStream(new ByteArrayOutputStream()));
        juego.jugarTorneo(4);
        if (juego.getPartidasJugadas() != 4 || juego.getHistorial().size() != 4)
            throw new AssertionError("Historial");
        System.out.println("OK: ciclo de 6 posiciones, puntos, 4 partidas e historial");
    }
}
