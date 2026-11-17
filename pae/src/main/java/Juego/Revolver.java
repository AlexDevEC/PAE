package Juego;

import java.util.Random;

/** Simulacion de un tambor de seis posiciones y una sola bala. */
public class Revolver {
    private static final int CAPACIDAD = 6;
    private final Random random;
    private int posicionActual;
    private int posicionBala;

    public Revolver() { this(new Random()); }

    /** Permite inyectar Random para hacer pruebas reproducibles. */
    public Revolver(Random random) {
        if (random == null) throw new IllegalArgumentException("Random no puede ser nulo");
        this.random = random;
        recargar();
    }

    public final void recargar() {
        posicionActual = random.nextInt(CAPACIDAD);
        posicionBala = random.nextInt(CAPACIDAD);
    }

    /** Si la bala no sale, el tambor avanza una posicion. */
    public boolean disparar() {
        boolean hayBala = posicionActual == posicionBala;
        if (!hayBala) siguienteBala();
        return hayBala;
    }

    private void siguienteBala() {
        posicionActual = (posicionActual + 1) % CAPACIDAD;
    }
}
