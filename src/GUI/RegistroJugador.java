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
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;


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
        setSize(500, 500);  
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
        
        fichaJugador1 = elegirFicha();

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


        fichaJugador2 = elegirFicha();

        if (fichaJugador2 == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una ficha.");
            return;
        }

    
        

        jugador2.setFicha(fichaJugador2);


        JOptionPane.showMessageDialog(this, "Los jugadores han sido registrados correctamente \n" +
                "Jugador 1: " + jugador1.getNombre() + "\n" +
                "Jugador 2: " + jugador2.getNombre());
    }

    private Image elegirFicha() {
  
        JPanel panelFicha = new JPanel();
        panelFicha.setLayout(null);
        JLabel labelFicha1 = new JLabel();
        JLabel labelFicha2 = new JLabel();


        labelFicha1.setIcon(new ImageIcon(getClass().getResource("/Assets1/Rombo.png")));
        labelFicha2.setIcon(new ImageIcon(getClass().getResource("/Assets1/Circulo.png")));

        labelFicha1.setBounds(0, 0, 100, 100);
        labelFicha2.setBounds(140, 0, 100, 100);

        labelFicha1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelFicha2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        final boolean[] fichaSeleccionada = {false};

        labelFicha1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fichaJugador1 = new ImageIcon(getClass().getResource("/Assets1/Rombo.png")).getImage();
                fichaSeleccionada[0] = true;
                JOptionPane.showMessageDialog(null, "Ficha Rombo seleccionada");

                dispose();
            }
        });

        labelFicha2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fichaJugador1 = new ImageIcon(getClass().getResource("/Assets1/Circulo.png")).getImage();
                fichaSeleccionada[0] = true;
                JOptionPane.showMessageDialog(null, "Ficha Círculo seleccionada");
 
                dispose();
            }
        });

        panelFicha.add(labelFicha1);
        panelFicha.add(labelFicha2);

        while (!fichaSeleccionada[0]) {
            JOptionPane.showMessageDialog(this, panelFicha, "Selecciona tu ficha", JOptionPane.PLAIN_MESSAGE);
        }

        return fichaJugador1;
    }








 
}//class