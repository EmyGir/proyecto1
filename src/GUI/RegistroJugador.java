/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.Jugador;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.WindowConstants;


/**
 *
 * @author kevin
 */
public class RegistroJugador extends JFrame {


    private Jugador jugador1;
    private Jugador jugador2;
    private Image fichaJugador1;
    private Image fichaJugador2;

    public RegistroJugador() {
        setTitle("Registro de Jugadores");
        setSize(700, 700);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        registrarJugadores();
    }


    private void registrarJugadores() {
        String nombreJugador1 = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 1:");

        if (nombreJugador1 == null || nombreJugador1.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del jugador 1 no puede estar vacío. Intenta de nuevo.");
            return;
        }


        jugador1 = new Jugador(nombreJugador1, true, null, 0);
        
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
        
       


        jugador2 = new Jugador(nombreJugador2, false, null, 0);


        fichaJugador2 = elegirFicha(false);

        if (fichaJugador2 == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una ficha.");
            return;
        }


        

        jugador2.setFicha(fichaJugador2);


        JOptionPane.showMessageDialog(this, "Los jugadores han sido registrados correctamente \n" +
                "Jugador 1: " + jugador1.getNombre() + "\n" +
                "Jugador 2: " + jugador2.getNombre());
    }

    
private Image elegirFicha(boolean esJugador1) {
    final Image[] fichaSeleccionada = {null};

    // Crear diálogo modal para elegir ficha
    JDialog dialog = new JDialog(this, "Selecciona tu ficha", true);
    dialog.setSize(350, 200);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new FlowLayout());

    // Cargar imágenes y escalarlas
    ImageIcon icono1 = new ImageIcon(getClass().getResource("/Assets/Cuadrado.png"));
    ImageIcon icono2 = new ImageIcon(getClass().getResource("/Assets/Circulo.png"));
    Image imagen1 = icono1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    Image imagen2 = icono2.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

    // Labels para mostrar imágenes
    JLabel labelFicha1 = new JLabel(new ImageIcon(imagen1));
    JLabel labelFicha2 = new JLabel(new ImageIcon(imagen2));

    labelFicha1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    labelFicha2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    // Mouse listeners para seleccionar ficha
    labelFicha1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (fichaJugador1 == null || fichaJugador1 .equals(fichaJugador2)) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Esa ficha ya fue seleccionada por el Jugador 1.");
                return;
            }
            fichaSeleccionada[0] = imagen1;
            dialog.dispose();
        }
    });

    labelFicha2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (fichaJugador2 == null ||fichaJugador2 .equals(fichaJugador1)) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Esa ficha ya fue seleccionada por el Jugador 1.");
                return;
            }
            fichaSeleccionada[0] = imagen2;
            dialog.dispose();
        }
    });

    dialog.add(labelFicha1);
    dialog.add(labelFicha2);

    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    dialog.setVisible(true); // Esto es modal, espera hasta que se cierre

    return fichaSeleccionada[0];
}

 /*

 while (fichaJugador2 == null ||fichaJugador2 .equals(fichaJugador1)) {
    if (fichaJugador2 != null && fichaJugador2.equals(fichaJugador1)) {
        JOptionPane.showMessageDialog(this, "Esa ficha ya fue seleccionada por el Jugador 1. Elige otra.");
    }
 }


*/



 
}//class