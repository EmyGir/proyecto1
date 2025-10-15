/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.Jugador;
import javax.swing.JFrame;

/**
 *
 * @author emily
 */
public class JFPartida extends JFrame{
private Jugador[] jugadores;
    public JFPartida(Jugador[] jugadors ) {
       this.jugadores=jugadors;
               
    
        add(new JPPartida(this.jugadores));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
