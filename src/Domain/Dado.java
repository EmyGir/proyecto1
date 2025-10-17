/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javax.swing.ImageIcon;

/**
 *
 * @author emily
 */
public class Dado extends Entidad{
     private Image[] caras;
    private int caraActual;
    private Timer timerAnimacion;
    private int resultadoFinal;
    private Random random;
    
    private boolean animado;
   private int framesAnimacion = 0;
   private int valor;     

    public Dado(int posY, int posX) {
        super(posY, posX);
        this.caras = new Image[6];
        this.random = new Random();
        this.caraActual = 0;
        this.animado=false;
        
          this.caras[0]=new ImageIcon(Dado.class.getResource("/Assets/cara1.png")).getImage();
           this.caras[1]=new ImageIcon(Dado.class.getResource("/Assets/cara2.png")).getImage();
           this.caras[2]=new ImageIcon(Dado.class.getResource("/Assets/cara3.png")).getImage();
           this.caras[3]=new ImageIcon(Dado.class.getResource("/Assets/cara4.png")).getImage();
           this.caras[4]=new ImageIcon(Dado.class.getResource("/Assets/cara5.png")).getImage();
           this.caras[5]=new ImageIcon(Dado.class.getResource("/Assets/cara6.png")).getImage();
          
    }

    public int lanzar() {
        resultadoFinal = random.nextInt(6);
        caraActual = resultadoFinal;
         this.animado = true;           // ✅ Activar animación
        this.framesAnimacion = 0;       // ✅ Reiniciar contador
        //this.caraActual = 0;            // ✅ Empezar con primera cara

        return resultadoFinal ;
      
    }//lanzar
    
     public void movimiento() {
        if (framesAnimacion < 25) {  // Primeros 25 frames: animación rápida
            this.caraActual = random.nextInt(6);  // Cara aleatoria
        } else {  // Últimos 5 frames: mostrar resultado final
            this.caraActual = resultadoFinal;  // -1 porque array es 0-based
        }
        
        framesAnimacion++;
        
        // Detener animación después de 30 frames (3 segundos a 100ms)
        if (framesAnimacion >= 30) {
            this.animado = false;
            // Asegurar que muestra el resultado final
            this.caraActual = resultadoFinal ;
            System.out.println("Animación terminada - Cara final: " + resultadoFinal);
        }
    }
    
      public boolean estaAnimando() {
        return this.animado;
    }

    public int getResultadoFinal() {
        return resultadoFinal;
    }
      
     
    public Image[] getCaras() {
        return caras;
    }

    public void setCaras(Image[] caras) {
        this.caras = caras;
    }

    @Override
    public void dibujar(Graphics g) {
        
        // Dibujar el dado según si está animando o no
        if (this.animado) {
            g.setColor(Color.RED);  // Color durante animación
        } else {
            g.setColor(Color.WHITE); // Color normal
        }

        
        if (caras != null && caraActual >= 0 && caraActual < caras.length) {
          g.drawImage(caras[caraActual], posX , posY, null);
            
        }
    }//dibujar
        
    
    
}//clase
