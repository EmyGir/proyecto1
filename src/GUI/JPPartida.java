package GUI;

import Domain.Jugador;
import Domain.Partida;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JPPartida extends JPanel implements ActionListener {

    private javax.swing.Timer timer;
    private JButton jbDado;
    private JButton jbMazo;
    private Partida partida;
    private int contadorTurnos = 0;
    private int[] lanzamientos = new int[2];
    private boolean faseSorteo = true;

    public JPPartida(Jugador[] jugadors) throws IOException {
        this.lanzamientos[0] = 0;
        this.lanzamientos[1] = 0;
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
        this.timer = new javax.swing.Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarAnimacion();
            }
        });//

    }//constructor

    private void actualizarAnimacion() {
        this.partida.getDado().movimiento();
        repaint();

        if (!this.partida.getDado().estaAnimando()) {
            this.timer.stop();
            System.out.println("Animación terminada");

            // Si estamos en fase de juego normal, procesar el movimiento
            if (!faseSorteo) {
                procesarMovimientoJuego();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ← NO OLVIDES ESTA LÍNEA

        // Dibujar elementos en orden
        this.partida.getTablero().dibujar(g);
        this.partida.getDado().dibujar(g);
        this.partida.getMazo().dibujar(g);

        // DEBUG: información de jugadores
        g.setColor(Color.WHITE);
        g.drawString("Estado del juego:", 500, 50);
        g.drawString("Fase: " + (faseSorteo ? "SORTEO" : "JUEGO"), 500, 65);

        for (int i = 0; i < this.partida.getJugadores().length; i++) {
            Jugador jugador = this.partida.getJugadores()[i];
            jugador.dibujar(g);

            // Información de debug
            g.drawString(jugador.getNombre() + " - Turno: " + jugador.isTurno(),
                    500, 85 + i * 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbDado) {
            int cara = this.partida.getDado().lanzar(); // 0..5
            this.timer.start();
            repaint();

            if (faseSorteo) {
                // fase de sorteo
                definirTurnos(cara);
            } else {
                // fase de juego normal - el movimiento se procesa cuando termina la animación
                System.out.println("Dado lanzado para movimiento normal");
            }
            return;
        }

        if (e.getSource() == this.jbMazo) {
            if (faseSorteo) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Primero deben definir los turnos lanzando el dado.");
                return;
            }

            Jugador jugadorEnTurno = obtenerJugadorEnTurno();
            if (jugadorEnTurno == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No hay jugador en turno.");
                return;
            }
            this.partida.getMazo().escogerCarta(jugadorEnTurno);
            repaint();
        }
    }

    private void procesarMovimientoJuego() {
        Jugador jugadorEnTurno = obtenerJugadorEnTurno();
        if (jugadorEnTurno == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay jugador en turno.");
            return;
        }
        this.partida.moverJugador(jugadorEnTurno);
        repaint();

        // Mostrar información del turno actual
        Jugador siguiente = obtenerJugadorEnTurno();
        if (siguiente != null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Turno de: " + siguiente.getNombre());
        }
    }

    private Jugador obtenerJugadorEnTurno() {
        for (Jugador j : this.partida.getJugadores()) {
            if (j.isTurno()) {
                return j;
            }
        }
        return null;
    }

    public void definirTurnos(int caraDado0a5) {
        int valor = caraDado0a5 + 1; // 1..6

        if (contadorTurnos == 0) {
            lanzamientos[0] = valor;
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Jugador 1 (" + this.partida.getJugadores()[0].getNombre()
                    + ") sacó " + valor + ". Ahora lanza el Jugador 2.");
            contadorTurnos++; // ← AÑADIR ESTO
        } else if (contadorTurnos == 1) {
            lanzamientos[1] = valor;

            // Resolver empate o definir ganador
            if (lanzamientos[0] == lanzamientos[1]) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Empate " + lanzamientos[0] + " vs " + lanzamientos[1]
                        + ". Vuelven a lanzar.");
                lanzamientos[0] = 0;
                lanzamientos[1] = 0;
                contadorTurnos = 0; // ← CAMBIAR de -1 a 0
            } else if (lanzamientos[0] > lanzamientos[1]) {
                this.partida.getJugadores()[0].setTurno(true);
                this.partida.getJugadores()[1].setTurno(false);
                javax.swing.JOptionPane.showMessageDialog(this,
                        "¡" + this.partida.getJugadores()[0].getNombre()
                        + " empieza! (Sacó " + lanzamientos[0] + " vs " + lanzamientos[1] + ")");
                iniciarJuegoNormal(); // ← AÑADIR ESTA LLAMADA
            } else {
                this.partida.getJugadores()[0].setTurno(false);
                this.partida.getJugadores()[1].setTurno(true);
                javax.swing.JOptionPane.showMessageDialog(this,
                        "¡" + this.partida.getJugadores()[1].getNombre()
                        + " empieza! (Sacó " + lanzamientos[1] + " vs " + lanzamientos[0] + ")");
                iniciarJuegoNormal(); // ← AÑADIR ESTA LLAMADA
            }
        }
    }

    private void iniciarJuegoNormal() {
        faseSorteo = false;
        contadorTurnos = 0; // Reiniciar contador para el juego

        // Iniciar el juego en la partida
        this.partida.iniciarJuego();

        // Mostrar quién empieza
        Jugador primerJugador = obtenerJugadorEnTurno();
        if (primerJugador != null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "¡Que comience el juego! " + primerJugador.getNombre() + " tiene el primer turno.");
        }

        // Actualizar interfaz
        this.jbDado.setText("Lanzar Dado");
        repaint();
    }
}
