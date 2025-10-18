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
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author emily
 */
public class JPPartida extends JPanel implements ActionListener {

    private Timer timer;
    private JButton jbDado;
    private JButton jbMazo;
    private Partida partida;
    private int contador=0;
    private int[]lanzamientos= new int[2];

    public JPPartida(Jugador[] jugadors) throws IOException {
        this.lanzamientos[0]=0;
        this.lanzamientos[1]=0;
        setPreferredSize(new Dimension(700, 800));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        
        //inicializar partida
        this.partida = new Partida(jugadors);
        //botones
        this.jbDado = new JButton("Lanzar");
        this.jbDado.setBounds(500, 350, 100, 30);
        this.jbDado.addActionListener(this);
        add(this.jbDado);

        this.jbMazo = new JButton("Escoger Carta");
        this.jbMazo.setBounds(50, 680, 150, 30);
        this.jbMazo.addActionListener(this);
        add(this.jbMazo);

        //agregar timer
        this.timer = new Timer(100, this);

        this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Esto se ejecuta CADA 100ms cuando el timer está activo
                actualizarAnimacion();
            }
        });

    }//constructor

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
    
    // Dibujar elementos en orden
    this.partida.getTablero().dibujar(g);
    this.partida.getDado().dibujar(g);
    this.partida.getMazo().dibujar(g);
    
    // DEBUG: información de jugadores
    g.setColor(Color.WHITE);
    g.drawString("Estado del juego:", 500, 50);
    
    for (int i = 0; i < this.partida.getJugadores().length; i++) {
        Jugador jugador = this.partida.getJugadores()[i];
        
        // Dibujar jugador
        jugador.dibujar(g);
        

   }


    }//dibujar
  


 @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.jbDado) {
        int cara = this.partida.getDado().lanzar(); // 0..5
        this.timer.start();
        repaint();

        if (contador < 2) {
            // fase de sorteo
            definirTurnos(cara);
        } else {
            // fase de juego normal
            Jugador jugadorEnTurno = obtenerJugadorEnTurno();
            if (jugadorEnTurno == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No hay jugador en turno.");
                return;
            }
            this.partida.moverJugador(jugadorEnTurno);
            repaint();
        }
        contador++;
        return;
    }

    if (e.getSource() == this.jbMazo) {
        Jugador jugadorEnTurno = obtenerJugadorEnTurno();
        if (jugadorEnTurno == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay jugador en turno.");
            return;
        }
        this.partida.getMazo().escogerCarta(jugadorEnTurno);
        repaint();
    }
}

private Jugador obtenerJugadorEnTurno() {
    for (Jugador j : this.partida.getJugadores()) {
        if (j.isTurno()) return j;
    }
    return null;
}

public void definirTurnos(int caraDado0a5) {
    int valor = caraDado0a5 + 1; // 1..6
    if (contador == 0) {
        lanzamientos[0] = valor;
        javax.swing.JOptionPane.showMessageDialog(this, "Jugador 1 sacó " + valor + ". Ahora lanza el Jugador 2.");
    } else if (contador == 1) {
        lanzamientos[1] = valor;
        // Resolver
        if (lanzamientos[0] == lanzamientos[1]) {
            javax.swing.JOptionPane.showMessageDialog(this, "Empate " + valor + " vs " + valor + ". Vuelven a lanzar.");
            lanzamientos[0] = 0;
            lanzamientos[1] = 0;
            contador = -1; // para que en el ++ del handler vuelva a 0
            // No setees turnos todavía
        } else if (lanzamientos[0] > lanzamientos[1]) {
            this.partida.getJugadores()[0].setTurno(true);
            this.partida.getJugadores()[1].setTurno(false);
            javax.swing.JOptionPane.showMessageDialog(this, "Empieza " + this.partida.getJugadores()[0].getNombre());
        } else {
            this.partida.getJugadores()[0].setTurno(false);
            this.partida.getJugadores()[1].setTurno(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Empieza " + this.partida.getJugadores()[1].getNombre());
        }
    }
}

}//clase
