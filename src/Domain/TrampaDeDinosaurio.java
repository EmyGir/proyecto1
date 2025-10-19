/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author emily
 */
public class TrampaDeDinosaurio extends Casilla{

    public TrampaDeDinosaurio(int id, int posY, int posX) {
        super(id, posY, posX);
       
    }

   

    
    @Override
    public void efecto(Jugador jugadorEnTurno) {
        JOptionPane.showMessageDialog(null, 
            "¡Caíste en una trampa de dinosaurio! Vuelves al inicio.");

      jugadorEnTurno.setPaso(0);
     
    }

  public void dibujar(Graphics g, int x, int y, int ancho, int alto) {
        // Dibujar rectángulo base
    g.setColor(Color.RED);
    g.fillRect(x, y, ancho, alto);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, ancho, alto);
  }
}//clase
