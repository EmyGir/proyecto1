/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.awt.Image;
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
    private boolean animando;
    private Timer timerAnimacion;
    private int contadorAnimacion;
    private int resultadoFinal;
    private Random random;
    

    public Dado(int posY, int posX) {
        super(posY, posX);
        this.caras = new Image[6];
        this.random = new Random();
        this.caraActual = 0;
        this.animando = false;
        this.contadorAnimacion = 0;
        
           this.caras[0]=new ImageIcon(Dado.class.getResource("/Assets/cara1.png")).getImage();
           this.caras[1]=new ImageIcon(Dado.class.getResource("/Assets/cara2.png")).getImage();
           this.caras[2]=new ImageIcon(Dado.class.getResource("/Assets/cara3.png")).getImage();
           this.caras[3]=new ImageIcon(Dado.class.getResource("/Assets/cara4.png")).getImage();
           this.caras[4]=new ImageIcon(Dado.class.getResource("/Assets/cara5.png")).getImage();
//           this.caras[5]=new ImageIcon(Dado.class.getResource("/Assets/cara6.png")).getImage();
          
    }

    public int lanzar() {
        resultadoFinal = random.nextInt(6);
        caraActual = resultadoFinal;
        return resultadoFinal + 1;
    }

    public Image[] getCaras() {
        return caras;
    }

    public void setCaras(Image[] caras) {
        this.caras = caras;
    }

    @Override
    public void dibujar(Graphics g) {
        if (caras != null && caraActual >= 0 && caraActual < caras.length) {
            g.drawImage(caras[caraActual], posX, posY, null);
        }
    }
    
}//clase
