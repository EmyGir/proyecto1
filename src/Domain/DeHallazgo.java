/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import GUI.JPPartida;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author emily
 */
public class DeHallazgo extends Casilla {

    private Mazo mazo;

    public DeHallazgo(int id, int posY, int posX) {
        super(id, posY, posX);
      
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    @Override
    
    public void efecto(Jugador jugadorEnTurno) {
        if (mazo == null) {
            
            throw new IllegalStateException("La casilla DeHallazgo no tiene mazo asignado (setMazo).");
        }
        JOptionPane.showMessageDialog(null, "¡Hallazgo! Toma una carta del mazo.");
        
        mazo.escogerCarta(jugadorEnTurno); 
    
         
    }//efecto
    
   

    @Override
    public void dibujar(Graphics g, int x, int y, int ancho, int alto) {
        // Dibujar rectángulo base
    g.setColor(Color.YELLOW);
    g.fillRect(x, y, ancho, alto);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, ancho, alto);

    }

  

}//clase
