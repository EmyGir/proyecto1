/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;

/**
 *
 * @author emily
 */
public class Partida {
    private Tablero tablero;
    private Jugador[] jugadores;
    private Dado dado;
    

    public Partida(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.tablero=new Tablero();
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }
     public Tablero crearTableroPredeterminado(){
          for (int i = 0; i < this.tablero.getCasillas().length; i++) {
            for (int j = 0; j < this.tablero.getCasillas()[i].length; j++) {
                
                if(((i==2&& j==5) || (i==7&& j==0) || (i==1&& j==8) || (i==5&& j==3) || (i==0&& j==6) || 
                        (i==5&& j==2) || (i==8&& j==1) || (i==3&& j==7) || (i==6&& j==4) || (i==2&& j==0) || 
                        (i==7&& j==3) || (i==1&& j==5) || (i==4&& j==8) || (i==0&& j==2)))
                 this.tablero.getCasillas()[i][j]= new TrampaDeDinosaurio(i, j, j);//rojo
              
                
                else if(((i==5&& j==7) || (i==8&& j==4) || (i==3&& j==1) || (i==6&& j==0) || (i==2&& j==3) || 
                        (i==7&& j==6) || (i==1&& j==2) || (i==4&& j==5) || (i==0&& j==8) || (i==5&& j==1) || 
                        (i==8&& j==7) || (i==3&& j==4) || (i==6&& j==2) || (i==2&& j==6)))
                 this.tablero.getCasillas()[i][j]= new DeHallazgo(i, j, j);//amarillo
                
                else if((i==7&& j==1) || (i==1&& j==4) || (i==4&& j==0) || (i==0&& j==3) || (i==3&& j==6) || 
                        (i==6&& j==8) || (i==8&& j==5) || (i==5&& j==7) || (i==7&& j==2) || (i==2&& j==4) || 
                        (i==4&& j==1) || (i==1&& j==7) || (i==8&& j==3) || (i==3&& j==0))
                 this.tablero.getCasillas()[i][j]= new PortalJurasico(i, j, j);//naranja
                
                else
                 this.tablero.getCasillas()[i][j]= new DePaso(i, j, j);//verde
            }
        }
          return this.tablero;
     } 

   
    
}//clase
