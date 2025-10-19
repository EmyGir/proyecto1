/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;
import java.util.Timer;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

/**
 *
 * @author emily
 */
public class Reto extends Carta{
    private String[] problemas = {
        "Resuelve: 5 + 3 × 2",
        "¿Cuál es la capital de Francia?",
        "¿Qué animal es el rey de la selva?"
    };
    private String[] respuestas = {"11", "París", "León"};

    public Reto(int id) {
        super(id);
    }

    @Override
    public void efecto(Jugador jugador) {
        int indice = (int) (Math.random() * problemas.length);
        String respuesta = JOptionPane.showInputDialog(null, 
            "Reto: " + problemas[indice] + "\nResponde:");
        
        if(respuesta != null && respuesta.equalsIgnoreCase(respuestas[indice])) {
            JOptionPane.showMessageDialog(null, "¡Correcto! Avanzas 2 casillas.");
            jugador.setPaso(jugador.getPaso() + 2);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrecto. Retrocedes 1 casilla.");
            jugador.setPaso(Math.max(0, jugador.getPaso() - 1));
        }
    }
}
