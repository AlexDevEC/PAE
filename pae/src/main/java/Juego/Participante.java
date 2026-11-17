package Juego;

/** Clase base: concentra los datos y reglas comunes de puntuacion. */
public abstract class Participante {
    private final int id;
    private final String nombre;
    private int puntos;
    private int victorias;

    protected Participante(int id, String nombre) {
        if (id <= 0 || nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Identificador o nombre invalido");
        }
        this.id = id;
        this.nombre = nombre.trim();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public int getVictorias() { return victorias; }

    public void registrarVictoria() {
        victorias++;
        puntos += 3;
    }

    public abstract String getEstado();
}
