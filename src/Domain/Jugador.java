/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Image;

/**
 *
 * @author emily
 */
public class Jugador {
    private String nombre;
    private boolean turno;
    private Image ficha;
    private int paso;

    public Jugador(String nombre, boolean turno, Image ficha) {
        this.nombre = nombre;
        this.turno = turno;
        this.ficha = ficha;
        this.paso=0;
    }//constructor

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
    
    
}//clase
