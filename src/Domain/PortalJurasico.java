/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author emily
 */
public class PortalJurasico extends Casilla{
    private Random random;

    public PortalJurasico(int id, int posY, int posX) {
        super(id, posY, posX);
        
    }


    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    
    public void efecto(Jugador jugadorEnTurno, Jugador JugadorSinTurno){
        
     int cantidadDePasos=jugadorEnTurno.getPaso()- JugadorSinTurno.getPaso();
     jugadorEnTurno.setPaso(jugadorEnTurno.getPaso()+cantidadDePasos);
     
        
    }//efecto
    
      

    public void dibujar(Graphics g, int x, int y, int ancho, int alto) {
        // Dibujar rectángulo base
    g.setColor(Color.ORANGE);
    g.fillRect(x, y, ancho, alto);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, ancho, alto);}
    
}//clase
