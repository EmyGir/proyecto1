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
public class JPPartida extends JPanel implements ActionListener {

    private Timer timer;
    private JButton jbDado;
    private JButton jbMazo;
    private Partida partida;

    public JPPartida(Jugador[] jugadors) {
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
        this.partida.getMazo().dibujar(g);

    }//dibujar

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.jbDado) {
        this.partida.getDado().lanzar();
        this.timer.start(); // ← solo una vez
        repaint();
        return;
    }

    if (e.getSource() == this.jbMazo) {
        Jugador jugadorEnTurno = null;
        for (Jugador j : this.partida.getJugadores()) {
            if (j.isTurno()) {
                jugadorEnTurno = j;
                break; // ← importantísimo
            }
        }
        if (jugadorEnTurno == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay jugador en turno.");
            return;
        }
        this.partida.getMazo().escogerCarta(jugadorEnTurno);
        repaint();
    }

    }//actionPerformed

}//clase
