# Juego de revolver mejorado - Java POO

Proyecto Maven para abrir directamente en Apache NetBeans.

## Como ejecutar
1. Abrir NetBeans > File > Open Project > seleccionar la carpeta `clase_1`.
2. Elegir Run Project (clase principal: `com.mycompany.clase_1.Clase_1`).
3. Introducir de 2 a 6 jugadores, sus nombres, y de 1 a 100 rondas.

O con terminal (JDK 17 o superior) desde `clase_1`:
```
mkdir -p target/classes
javac -encoding UTF-8 -d target/classes src/main/java/Juego/*.java src/main/java/com/mycompany/clase_1/*.java
java -cp target/classes com.mycompany.clase_1.Clase_1
```

## Reglas
- Cada ronda es una partida nueva y todos comienzan activos.
- El revólver tiene seis posiciones y una bala, colocados aleatoriamente.
- En cada turno un jugador activo dispara. Si coincide con la bala, queda eliminado.
- El último jugador activo gana 3 puntos y una victoria.
- En el cierre se muestran clasificación, posibles empates e historial.

## Arquitectura
- `Participante`: clase abstracta, encapsula identidad y estadísticas.
- `Jugador`: hereda de `Participante`; estado en partida y disparo.
- `Revolver`: gestiona los seis espacios y el disparo aleatorio.
- `Jueg_o`: coordina partidas y clasificación; conserva el nombre de clase original.
- `Clase_1`: entrada, validación y registro de jugadores.

## Entrega del enlace al código fuente
El archivo ZIP suministrado contiene el código completo. Para entregar una URL pública, crea un repositorio en GitHub, sube esta carpeta y pega la URL en tu plataforma académica. No se ha creado un repositorio externo automáticamente.
