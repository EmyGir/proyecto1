/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
    
    public void efecto() {
        int random =(int)(Math.random()* this.mazo.getCartas().size());
        this.mazo.getCartas().get(random).efecto();
         
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
