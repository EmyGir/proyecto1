/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.Jugador;
import Domain.Partida;
import Domain.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author emily
 */
public class JPPartida extends JPanel implements KeyListener, ActionListener{
private Timer timer;
private Tablero tablero= new Tablero();
private Partida partida;
    public JPPartida(Jugador[] jugadors) {
        setPreferredSize(new Dimension(700, 800));
        setBackground(Color.BLACK);
        setFocusable(true);
         addKeyListener(this);
      
        this.partida= new Partida(jugadors);
       this.tablero= partida.crearTableroPredeterminado();
       
         this.timer = new Timer(30,  this);
        this.timer.start();

    }
@Override
       protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             this.tablero.dibujar(g);

       }//dibujar
      

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
