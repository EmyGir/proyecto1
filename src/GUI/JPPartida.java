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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author emily
 */
public class JPPartida extends JPanel implements KeyListener, ActionListener{
private Timer timer;
private JButton jbDado;
private Partida partida;
    public JPPartida(Jugador[] jugadors) {
        setPreferredSize(new Dimension(700, 800));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);
        //inicializar partida
        this.partida= new Partida(jugadors);
        //boton
        this.jbDado= new JButton("Lanzar");
        this.jbDado.setBounds(500, 350, 100, 30);
        this.jbDado.addActionListener(this);
        add(this.jbDado);
        
       //agregar boton
        this.timer = new Timer(100, this);
        
       this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Esto se ejecuta CADA 100ms cuando el timer está activo
                actualizarAnimacion();
            }
        });

    }
    private void actualizarAnimacion() {
        // Actualizar movimiento del dado
        this.partida.getDado().movimiento();
        
        // Redibujar
        repaint();
        
        // Verificar si la animación terminó
        if (!this.partida.getDado().estaAnimando()) {
            this.timer.stop();
            System.out.println("Animación terminada");
            // Aquí puedes avanzar el turno, mover ficha, etc.
        }
    }
@Override
       protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             this.partida.getDado().dibujar(g);
             this.partida.getTablero().dibujar(g);
             this.partida.getDado().dibujar(g);
             

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
        if(e.getSource()==this.jbDado){
           
         this.partida.getDado().lanzar();
          this.timer.start();       
        }//if boton lanzar
        repaint();
       
        
            
    }//actionPerformed
    
}//clase
