/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author emily
 */
public class DePaso extends Casilla{

    public DePaso(int id, int posY, int posX) {
        super(id, posY, posX);
    }
    
  
    
    public void efectoX(Jugador jugadorEnTurno, int numeroDado) {
        
        jugadorEnTurno.setPosX(jugadorEnTurno.getPosX()+(50*numeroDado));
        
        jugadorEnTurno.setPaso(jugadorEnTurno.getPaso()+numeroDado);
    }//efecto x
    
     public void efectoY(Jugador jugadorEnTurno, int numeroDado) {
        
        jugadorEnTurno.setPosY(jugadorEnTurno.getPosY()+(50*numeroDado));
        
       jugadorEnTurno.setPaso(jugadorEnTurno.getPaso()+numeroDado);
    }//efect y

    @Override
    public void dibujar(Graphics g, int x, int y, int ancho, int alto) {
        // Dibujar rectángulo base
    g.setColor(Color.green);
    g.fillRect(x, y, ancho, alto);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, ancho, alto);
    }//dibujar
    
}//clase
