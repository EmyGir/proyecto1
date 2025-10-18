/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Domain.Jugador;
import GUI.JFPartida;
import GUI.JFRegistro;
import GUI.RegistroJugador;
import java.io.IOException;


/**
 *
 * @author emily
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) throws IOException {
         /* Jugador[] jugadores = new Jugador[2];
          jugadores[0]= new Jugador("prueba1", true, null, 0);
          jugadores[1]= new Jugador("prueba2", true, null, 0);
          
        JFRegistro fRegistro = new JFRegistro();
        JFPartida fPartida= new JFPartida(jugadores);
    }
    */
     
         RegistroJugador ventana = new RegistroJugador();{
        ventana.setVisible(true);
    }

    }
    }

 