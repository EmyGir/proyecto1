/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emily
 */
public class Tablero {

    private Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[8][8];

    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public void dibujar(Graphics g) {
        int anchoCasilla = 50;
        int altoCasilla = 50;
        int margenX = 50;  // Margen izquierdo
        int margenY = 50;  // Margen superior

        int filaInicio = 0;
        int filaFin = this.casillas.length - 1;
        int columnaInicio = 0;
        int columnaFin = this.casillas[0].length - 1;

        int x = margenX;
        int y = margenY;

        int contador = 0;

        while (filaInicio <= filaFin && columnaInicio <= columnaFin) {
            // ⃣ Recorrer izquierda → derecha (fila superior)

            for (int j = columnaInicio; j <= columnaFin; j++) {
                if (casillas[filaInicio][j] != null) {

                    casillas[filaInicio][j].dibujar(g, x, y, anchoCasilla, altoCasilla);

                    casillas[filaInicio][j].setPosX(x);
                    casillas[filaInicio][j].setPosY(y);

                    // Dibujar número
                    g.setColor(Color.BLACK);
                    casillas[filaInicio][j].setId(contador);
                    g.drawString(String.valueOf(contador),
                            x + anchoCasilla / 2 - 5, y + altoCasilla / 2 + 5);

                    contador++;
                }
                x += anchoCasilla; // Mover a la derecha

            }//for ⃣ Recorrer izquierda → derecha (fila superior)
            filaInicio++;
            x -= anchoCasilla; // Ajustar posición X
            y += altoCasilla;  // Bajar a la siguiente fila

            // 2️⃣ Recorrer arriba → abajo (columna derecha)
            for (int i = filaInicio; i <= filaFin; i++) {
                if (casillas[i][columnaFin] != null) {
                    casillas[i][columnaFin].dibujar(g, x, y, anchoCasilla, altoCasilla);
                    casillas[i][columnaFin].setPosX(x);
                    casillas[i][columnaFin].setPosY(y);

                    // Dibujar número
                    g.setColor(Color.BLACK);
                    casillas[i][columnaFin].setId(contador);

                    g.drawString(String.valueOf(contador),
                            x + anchoCasilla / 2 - 5, y + altoCasilla / 2 + 5);
                }
                y += altoCasilla; // Mover hacia abajo
                contador++;
            }
            columnaFin--;
            y -= altoCasilla; // Ajustar posición Y
            x -= anchoCasilla; // Mover a la izquierda

            // 3️⃣ Recorrer derecha → izquierda (fila inferior)
            if (filaInicio <= filaFin) {
                for (int j = columnaFin; j >= columnaInicio; j--) {
                    if (casillas[filaFin][j] != null) {
                        casillas[filaFin][j].dibujar(g, x, y, anchoCasilla, altoCasilla);
                        casillas[filaInicio][j].setPosX(x);
                        casillas[filaInicio][j].setPosY(y);

                        // Dibujar número
                        g.setColor(Color.BLACK);
                        casillas[filaFin][j].setId(contador);
                        g.drawString(String.valueOf(contador),
                                x + anchoCasilla / 2 - 5, y + altoCasilla / 2 + 5);
                        contador++;
                    }
                    x -= anchoCasilla; // Mover a la izquierda

                }
                filaFin--;
                x += anchoCasilla; // Ajustar posición X
                y -= altoCasilla;  // Subir a la fila anterior

            }

            // 4️⃣ Recorrer abajo → arriba (columna izquierda)
            if (columnaInicio <= columnaFin) {
                for (int i = filaFin; i >= filaInicio; i--) {
                    if (casillas[i][columnaInicio] != null) {
                        casillas[i][columnaInicio].dibujar(g, x, y, anchoCasilla, altoCasilla);
                        casillas[i][columnaInicio].setPosX(x);
                        casillas[i][columnaInicio].setPosY(y);

                        // Dibujar número
                        g.setColor(Color.BLACK);
                        casillas[i][columnaInicio].setId(contador);
                        g.drawString(String.valueOf(contador),
                                x + anchoCasilla / 2 - 5, y + altoCasilla / 2 + 5);

                        contador++;
                    }
                    y -= altoCasilla; // Mover hacia arriba
                }
                columnaInicio++;
                y += altoCasilla; // Ajustar posición Y
                x += anchoCasilla; // Mover a la derecha

            }

        }

    }//dibujar

}//clase

