package Juego;

/** Representa a un jugador y su estado durante una partida. */
public class Jugador extends Participante {
    private boolean vivo;

    public Jugador(int id, String nombre) {
        super(id, nombre);
        reiniciar();
    }

    public boolean isVivo() { return vivo; }
    public void reiniciar() { vivo = true; }

    /** Devuelve true si sobrevive al intento. */
    public boolean disparar(Revolver revolver) {
        if (!vivo) {
            throw new IllegalStateException("El jugador ya esta eliminado");
        }
        if (revolver.disparar()) {
            vivo = false;
        }
        return vivo;
    }

    @Override
    public String getEstado() { return vivo ? "Activo" : "Eliminado"; }

    @Override
    public String toString() {
        return getNombre() + " | Puntos: " + getPuntos()
                + " | Victorias: " + getVictorias();
    }
}
