/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import javax.swing.JOptionPane;

/**
 *
 * @author emily
 */
public class Recompensa extends Carta{

    public Recompensa(int id) {
        super(id);
    }

    @Override
    public void efecto(Jugador jugador) {
         int resultado = (int) (Math.random() * 2) + 1;
        if(resultado == 1){
            JOptionPane.showMessageDialog(null, "¡Encontraste un atajo! Avanzas 3 casillas.");
            jugador.setPaso(jugador.getPaso() + 3);
        } else {
            JOptionPane.showMessageDialog(null, "¡Energía extra! Tienes un turno adicional.");
            jugador.setTurno(true); // Mantiene el turno
        }
    }    

   
}//clase
