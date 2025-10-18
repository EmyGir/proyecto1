/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author emily
 */
public class Jugador extends Entidad{
    private String nombre;
    private boolean turno;
    private Image ficha;
    private int paso;
    private String ruta;

    public Jugador(String nombre, boolean turno, Image ficha, int paso, String ruta) {
        super(50, 50);
        this.nombre = nombre;
        this.turno = turno;
        this.ficha = ficha;
        this.paso = paso;
        this.ruta=ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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
    try {
        // Cargar la imagen desde la ruta específica del jugador
        if (this.ruta != null && !this.ruta.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(this.ruta));
            if (imageIcon.getImage() != null) {
                this.ficha = imageIcon.getImage();
                // Dibujar con tamaño fijo para mejor visualización
                g.drawImage(this.ficha, posX, posY, 30, 30, null);
                
                // Dibujar nombre del jugador
                g.setColor(Color.WHITE);
                g.drawString(this.nombre, posX, posY + 40);
                
                // Dibujar indicador de turno
                if (turno) {
                    g.setColor(Color.YELLOW);
                    g.drawRect(posX - 2, posY - 2, 34, 34);
                }
                return;
            }
        }
    } catch (Exception e) {
        System.out.println("Error cargando ficha para " + this.nombre + ": " + e.getMessage());
    }
    
  }// dibujar
   
    
 
}//clase
