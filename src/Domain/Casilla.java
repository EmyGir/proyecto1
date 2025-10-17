/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;

/**
 *
 * @author emily
 */
public abstract class Casilla extends Entidad{
    protected int id;

    public Casilla(int id, int posY, int posX) {
        super(posY, posX);
        this.id = id;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public  void efecto(Jugador jugadorEnTurno){
        
    }
    
    public void dibujar(Graphics g, int x, int y, int ancho, int alto){}
}//clase
