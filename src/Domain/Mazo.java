/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author emily
 */
public class Mazo extends Entidad{
    private ArrayList<Carta>cartas;
    private Image imagen;

    public Mazo(int posY, int posX) {
        super(posY, posX);
        this.cartas=new ArrayList<>();
        this.imagen=new ImageIcon(Mazo.class.getResource("/Assets/mazo.png")).getImage();
        rellenarCartas();
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(this.imagen, posX, posY, null);
                
    }
    
    public void rellenarCartas(){
        for (int i = 0; i < 8; i++) {
            int resultado = (int) (Math.random() * (3-1+1) + 1);
            switch (resultado) {
                case 1:
                    this.cartas.add(new Reto(i));
                    break;
                case 2:
                    this.cartas.add(new Recompensa(i));
                    break;
                    
                case 3:
                    this.cartas.add(new Castigo(i));
                    break;
                default:
                    throw new AssertionError();
            }
           
        }
             
        
    }//rellenar cartas
        public Carta escogerCarta(Jugador jugadorEnTurno){
          if (cartas.isEmpty()) {
            // reponer mazo; usa limpiar=true para barajar desde cero
            rellenarCartas();
        }
        int idx = (int) (Math.random() * (8-1+1) + 1);
        Carta carta = cartas.remove(idx);
       
        carta.efecto();  
        return carta;
    }
    
    
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    
}//clase
