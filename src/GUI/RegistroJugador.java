/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.Jugador;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JPanel;



/**
 *
 * @author kevin
 */
public class RegistroJugador extends JFrame {


    private Jugador jugador1;
    private Jugador jugador2;
    private Image fichaJugador1;
    private Image fichaJugador2;
    private String ruta="";

    public RegistroJugador() throws IOException {
//        setTitle("Registro de Jugadores");
//        setSize(500, 500);  
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(null);
          
        inicializarComponentes();
    }

    private void inicializarComponentes() throws IOException {
        registrarJugadores();
       
    }


   private void registrarJugadores() throws IOException {
    String nombreJugador1 = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 1:");

    if (nombreJugador1 == null || nombreJugador1.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre del jugador 1 no puede estar vacío. Intenta de nuevo.");
        return;
    }

    // Cambiar: no pasar ruta en el constructor
    jugador1 = new Jugador(nombreJugador1, true, null, 0, ""); // ← ruta vacía
    
    fichaJugador1 = elegirFicha(true);

    if (fichaJugador1 == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una ficha.");
        return;
    }

    jugador1.setFicha(fichaJugador1);

    String nombreJugador2 = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 2:");

    while (nombreJugador2 == null || nombreJugador2.trim().isEmpty() || nombreJugador2.equals(jugador1.getNombre())) {
        if (nombreJugador2.equals(jugador1.getNombre())) {
            JOptionPane.showMessageDialog(this, "Los nombres no pueden ser iguales. Intenta de nuevo.");
        } else {
            JOptionPane.showMessageDialog(this, "El nombre del jugador 2 no puede estar vacío. Intenta de nuevo.");
        }
        nombreJugador2 = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 2:");
    }

    // Cambiar: no pasar ruta en el constructor  
    jugador2 = new Jugador(nombreJugador2, false, null, 0, ""); // ← ruta vacía

    fichaJugador2 = elegirFicha(false);

    if (fichaJugador2 == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una ficha.");
        return;
    }

    jugador2.setFicha(fichaJugador2);

    JOptionPane.showMessageDialog(this, "Los jugadores han sido registrados correctamente \n" +
            "Jugador 1: " + jugador1.getNombre() + "\n" +
            "Jugador 2: " + jugador2.getNombre());
    mandarJugadoresVentana();
}

    
private Image elegirFicha(boolean esJugador1) {
    final Image[] fichaSeleccionada = {null};
    final String[] rutaSeleccionada = {""};

    JDialog dialog = new JDialog(this, "Selecciona tu ficha", true);
    dialog.setSize(350, 200);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new FlowLayout());

    ImageIcon icono1 = new ImageIcon(getClass().getResource("/Assets/Cuadrado.png"));
    ImageIcon icono2 = new ImageIcon(getClass().getResource("/Assets/Circulo.png"));
    Image imagen1 = icono1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    Image imagen2 = icono2.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

    // Panel personalizado para Cuadrado
    JPanel panelFicha1 = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagen1, 0, 0, getWidth(), getHeight(), this);
        }
    };
    panelFicha1.setPreferredSize(new Dimension(120, 120));
    panelFicha1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    // Panel personalizado para Círculo
    JPanel panelFicha2 = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagen2, 0, 0, getWidth(), getHeight(), this);
        }
    };
    panelFicha2.setPreferredSize(new Dimension(120, 120));
    panelFicha2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    // Listeners
    panelFicha1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!esJugador1 && fichaJugador1 != null && fichaJugador1.equals(imagen1)) {
                JOptionPane.showMessageDialog(dialog, "Esa ficha ya fue seleccionada por el Jugador 1.");
                return;
            }
            fichaSeleccionada[0] = imagen1;
            rutaSeleccionada[0] = "/Assets/Cuadrado.png";
            dialog.dispose();
        }
    });

    panelFicha2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!esJugador1 && fichaJugador1 != null && fichaJugador1.equals(imagen2)) {
                JOptionPane.showMessageDialog(dialog, "Esa ficha ya fue seleccionada por el Jugador 1.");
                return;
            }
            fichaSeleccionada[0] = imagen2;
            rutaSeleccionada[0] = "/Assets/Circulo.png";
            dialog.dispose();
        }
    });

    dialog.add(panelFicha1);
    dialog.add(panelFicha2);
    dialog.setVisible(true);

    if (esJugador1) {
        this.jugador1.setRuta(rutaSeleccionada[0]);
    } else {
        this.jugador2.setRuta(rutaSeleccionada[0]);
    }

    return fichaSeleccionada[0];
}


 private void mandarJugadoresVentana() throws IOException{
     Jugador[] jugadors = new Jugador[2];
     jugadors[0]=this.jugador1;
     jugadors[1]=this.jugador2;
     JFPartida Partida= new JFPartida(jugadors);
     
     
 }//
 
}//class