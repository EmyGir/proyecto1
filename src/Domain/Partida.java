/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emily
 */
public class Partida {
    private Tablero tablero;
    private Jugador[] jugadores;
    private Dado dado;
    private Mazo mazo; 
    
    public Partida(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.tablero=new Tablero();
       this.dado= new Dado(200, 500);
       this.mazo= new Mazo(500, 50);
       
      
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
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
    
     public void crearTableroPredeterminado(){
          for (int i = 0; i < this.tablero.getCasillas().length; i++) {
            for (int j = 0; j < this.tablero.getCasillas()[i].length; j++) {
                
                if(((i==2&& j==5) || (i==7&& j==0) || (i==1&& j==8) || (i==5&& j==3) || (i==0&& j==6) || 
                        (i==5&& j==2) || (i==8&& j==1) || (i==3&& j==7) || (i==6&& j==4) || (i==2&& j==0) || 
                        (i==7&& j==3) || (i==1&& j==5) || (i==4&& j==8) || (i==0&& j==2)))
                 this.tablero.getCasillas()[i][j]= new TrampaDeDinosaurio(i, j, j);//rojo
              
                
                else if(((i==5&& j==7) || (i==8&& j==4) || (i==3&& j==1) || (i==6&& j==0) || (i==2&& j==3) || 
                        (i==7&& j==6) || (i==1&& j==2) || (i==4&& j==5) || (i==0&& j==8) || (i==5&& j==1) || 
                        (i==8&& j==7) || (i==3&& j==4) || (i==6&& j==2) || (i==2&& j==6)))
                 this.tablero.getCasillas()[i][j]= new DeHallazgo(i, j, j,this.getMazo());//amarillo
                
                else if((i==7&& j==1) || (i==1&& j==4) || (i==4&& j==0) || (i==0&& j==3) || (i==3&& j==6) || 
                        (i==6&& j==8) || (i==8&& j==5) || (i==5&& j==7) || (i==7&& j==2) || (i==2&& j==4) || 
                        (i==4&& j==1) || (i==1&& j==7) || (i==8&& j==3) || (i==3&& j==0))
                 this.tablero.getCasillas()[i][j]= new PortalJurasico(i, j, j);//naranja
                
                else
                 this.tablero.getCasillas()[i][j]= new DePaso(i, j, j);//verde
            }
        }
         setTablero(this.tablero);
     } 
     
     public void crearTableroAleatorio(){
      
          for (int i = 0; i < this.tablero.getCasillas().length; i++) {
            for (int j = 0; j < this.tablero.getCasillas()[i].length; j++) {
                  int resultado = (int) (Math.random() * (4-1+1) + 1);
                
                switch (resultado) {
                    case 2:
                        this.tablero.getCasillas()[i][j]= new TrampaDeDinosaurio(i, j, j);//rojo
                        break;
                    case 4:
                        this.tablero.getCasillas()[i][j]= new DeHallazgo(i, j, j,this.getMazo());//amarillo
                        break;
                    case 3:
                        this.tablero.getCasillas()[i][j]= new PortalJurasico(i, j, j);//naranja
                        break;
                    case 1:
                        this.tablero.getCasillas()[i][j]= new DePaso(i, j, j);//verde
                        break;
                    default:
                        break;
                }
            }
        }
          setTablero(this.tablero);
     } 

    public Tablero getTablero() {
        crearTableroPredeterminado();
       // crearTableroAleatorio();
       //esto va en registro
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }


   

public void moverJugador(Jugador jugadorEnTurno) {
    // 1) Valor real del dado (0..5 -> 1..6)
    int valor = this.dado.getResultadoFinal() + 1;

    // 2) Calcular destino y clamplear
    int maxId = this.tablero.getCasillas().length * this.tablero.getCasillas()[0].length - 1; // 63
    int destino = jugadorEnTurno.getPaso() + valor;
    if (destino > maxId) destino = maxId;
    if (destino < 0) destino = 0;

    // 3) Casilla destino
    Casilla casilla = obtenerCasillaPorId(destino);
    if (casilla == null) return;

    // 4) Actualizar estado lógico y posición visual
    jugadorEnTurno.setPaso(destino);
    jugadorEnTurno.setPosX(casilla.getPosX());
    jugadorEnTurno.setPosY(casilla.getPosY());

    // 5) Efecto
    if (casilla instanceof DePaso) {
        cambiarTurno(jugadorEnTurno);
        return;
    } else if (casilla instanceof PortalJurasico) {
        Jugador otro = obtenerOtroJugador(jugadorEnTurno);
        ((PortalJurasico) casilla).efecto(jugadorEnTurno, otro);

        // Si el portal cambió el 'paso', reubica la ficha en la nueva casilla:
        Casilla casillaNueva = obtenerCasillaPorId(jugadorEnTurno.getPaso());
        if (casillaNueva != null) {
            jugadorEnTurno.setPosX(casillaNueva.getPosX());
            jugadorEnTurno.setPosY(casillaNueva.getPosY());
        }
        cambiarTurno(jugadorEnTurno);
    } else {
        // TrampaDeDinosaurio, DeHallazgo, etc.
        casilla.efecto(jugadorEnTurno);

        // Reubica por si el efecto modificó 'paso'
        Casilla casillaNueva = obtenerCasillaPorId(jugadorEnTurno.getPaso());
        if (casillaNueva != null) {
            jugadorEnTurno.setPosX(casillaNueva.getPosX());
            jugadorEnTurno.setPosY(casillaNueva.getPosY());
        }
        cambiarTurno(jugadorEnTurno);
    }

    // 6) Meta (opcional)
    if (jugadorEnTurno.getPaso() >= maxId) {
        // victoria...
    }
}


public void cambiarTurno(Jugador actual) {
    int idx = (this.jugadores[0] == actual) ? 0 : 1;
    int next = 1 - idx;
    this.jugadores[idx].setTurno(false);
    this.jugadores[next].setTurno(true);
}



// Helpers
private Casilla obtenerCasillaPorId(int idBuscado) {
    for (int i = 0; i < this.tablero.getCasillas().length; i++) {
        for (int j = 0; j < this.tablero.getCasillas()[i].length; j++) {
            Casilla c = this.tablero.getCasillas()[i][j];
            if (c != null && c.getId() == idBuscado) {
                return c;
            }
        }
    }
    return null;
}

private Jugador obtenerOtroJugador(Jugador actual) {
    for (Jugador j : this.jugadores) if (j != actual) return j;
    return actual; // defensa si solo hay uno
}


    
}//clase
