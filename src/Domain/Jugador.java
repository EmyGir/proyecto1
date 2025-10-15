/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author emily
 */
public class Jugador extends Entidad{
    private String nombre;
    private boolean turno;
    private Image ficha;
    private int paso;

    public Jugador(String nombre, boolean turno, Image ficha, int paso, int posY, int posX) {
        super(posY, posX);
        this.nombre = nombre;
        this.turno = turno;
        this.ficha = ficha;
        this.paso = paso;
    }

  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public Image getFicha() {
        return ficha;
    }

    public void setFicha(Image ficha) {
        this.ficha = ficha;
    }

    public int getPaso() {
        return paso;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", turno=" + turno + ", ficha=" + ficha + ", paso=" + paso + '}';
    }

    @Override
    public void dibujar(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
 
}//clase
