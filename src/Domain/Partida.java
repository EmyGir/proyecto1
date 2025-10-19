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
    private boolean juegoIniciado;
    private Jugador jugadorEnTurno;
     private DeHallazgo casillaHallazgoActiva; 
      private boolean esperandoAccionHallazgo; 
    public Partida(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.dado = new Dado(200, 500);
        this.mazo = new Mazo(500, 50);
        this.juegoIniciado = false;
         this.esperandoAccionHallazgo = false;
        
        // MOVER la creación del tablero aquí - ES CRÍTICO
        crearTableroPredeterminado();
        
        inicializarPosicionesJugadores();
        
        // DEBUG
        System.out.println("=== INICIALIZACIÓN PARTIDA ===");
        System.out.println("Jugador 1: " + jugadores[0].getNombre() + ", turno: " + jugadores[0].isTurno());
        System.out.println("Jugador 2: " + jugadores[1].getNombre() + ", turno: " + jugadores[1].isTurno());
        System.out.println("Tablero creado con " + tablero.getCasillas().length + "x" + tablero.getCasillas()[0].length + " casillas");
    }
 private void inicializarPosicionesJugadores() {
        // Obtener la primera casilla
        Casilla inicio = obtenerCasillaPorId(0);
        if (inicio != null) {
            for (int i = 0; i < jugadores.length; i++) {
                Jugador jugador = jugadores[i];
                jugador.setPaso(0);
                // Posicionar jugadores en lugares ligeramente diferentes
                jugador.setPosX(inicio.getPosX() + (i * 20)); // Jugador 1: +0, Jugador 2: +20
                jugador.setPosY(inicio.getPosY() + 5);
                
                System.out.println("Posicionado " + jugador.getNombre() + " en (" + 
                    jugador.getPosX() + "," + jugador.getPosY() + ")");
            }
        } else {
            System.out.println("ERROR: No se encontró la casilla inicial (ID 0)");
        }
    }


    // Añadir validación del estado del juego
    public boolean isJuegoIniciado() {
        return juegoIniciado;
    }

    public Jugador getJugadorEnTurno() {
        return jugadorEnTurno;
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
    System.out.println("=== CREANDO TABLERO PREDETERMINADO ===");
    int totalCasillas = 0;
    
    for (int i = 0; i < this.tablero.getCasillas().length; i++) {
        for (int j = 0; j < this.tablero.getCasillas()[i].length; j++) {
            // CORREGIR: Calcular ID único para cada casilla
            int id = i * this.tablero.getCasillas()[i].length + j;
            
            if(((i==2&& j==5) || (i==7&& j==0) || (i==1&& j==8) || (i==5&& j==3) || (i==0&& j==6) || 
                    (i==5&& j==2) || (i==8&& j==1) || (i==3&& j==7) || (i==6&& j==4) || (i==2&& j==0) || 
                    (i==7&& j==3) || (i==1&& j==5) || (i==4&& j==8) || (i==0&& j==2))) {
                this.tablero.getCasillas()[i][j] = new TrampaDeDinosaurio(id, i, j);
                System.out.println("Trampa en: " + i + "," + j + " ID: " + id);
            } else if(((i==5&& j==7) || (i==8&& j==4) || (i==3&& j==1) || (i==6&& j==0) || (i==2&& j==3) || 
                    (i==7&& j==6) || (i==1&& j==2) || (i==4&& j==5) || (i==0&& j==8) || (i==5&& j==1) || 
                    (i==8&& j==7) || (i==3&& j==4) || (i==6&& j==2) || (i==2&& j==6))) {
                this.tablero.getCasillas()[i][j] = new DeHallazgo(id, i, j, this.getMazo());
                System.out.println("Hallazgo en: " + i + "," + j + " ID: " + id);
            } else if((i==7&& j==1) || (i==1&& j==4) || (i==4&& j==0) || (i==0&& j==3) || (i==3&& j==6) || 
                    (i==6&& j==8) || (i==8&& j==5) || (i==5&& j==7) || (i==7&& j==2) || (i==2&& j==4) || 
                    (i==4&& j==1) || (i==1&& j==7) || (i==8&& j==3) || (i==3&& j==0)) {
                this.tablero.getCasillas()[i][j] = new PortalJurasico(id, i, j);
                System.out.println("Portal en: " + i + "," + j + " ID: " + id);
            } else {
                this.tablero.getCasillas()[i][j] = new DePaso(id, i, j);
            }
            totalCasillas++;
        }
    }
    System.out.println("Total casillas creadas: " + totalCasillas);
    setTablero(this.tablero);
}//crear tablero predeterminado
     
public void crearTableroAleatorio(){
    System.out.println("=== CREANDO TABLERO ALEATORIO ===");
    int totalCasillas = 0;
    
    for (int i = 0; i < this.tablero.getCasillas().length; i++) {
        for (int j = 0; j < this.tablero.getCasillas()[i].length; j++) {
            // CORREGIR: Calcular ID único
            int id = i * this.tablero.getCasillas()[i].length + j;
            int resultado = (int) (Math.random() * (4-1+1) + 1);
            
            switch (resultado) {
                case 2:
                    this.tablero.getCasillas()[i][j] = new TrampaDeDinosaurio(id, i, j);
                    break;
                case 4:
                    this.tablero.getCasillas()[i][j] = new DeHallazgo(id, i, j, this.getMazo());
                    break;
                case 3:
                    this.tablero.getCasillas()[i][j] = new PortalJurasico(id, i, j);
                    break;
                case 1:
                    this.tablero.getCasillas()[i][j] = new DePaso(id, i, j);
                    break;
                default:
                    break;
            }
            totalCasillas++;
        }
    }
    System.out.println("Total casillas creadas: " + totalCasillas);
    setTablero(this.tablero);
}//crear tablero aleatorio
    public Tablero getTablero() {
 
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }


   

   public void moverJugador(Jugador jugadorEnTurno) {
        // 1) Valor real del dado (0..5 -> 1..6)
        int valor = this.dado.getResultadoFinal() + 1;
        System.out.println("=== MOVIMIENTO INICIADO ===");
        System.out.println("Jugador: " + jugadorEnTurno.getNombre());

        // 2) Calcular destino
        int maxId = this.tablero.getCasillas().length * this.tablero.getCasillas()[0].length - 1;
        int destino = jugadorEnTurno.getPaso() + valor;
        if (destino > maxId) destino = maxId;
        if (destino < 0) destino = 0;

        // 3) Casilla destino
        Casilla casilla = obtenerCasillaPorId(destino);
        if (casilla == null) {
            System.out.println("ERROR: Casilla " + destino + " no encontrada!");
            cambiarTurno(jugadorEnTurno);
            return;
        }

        // 4) Actualizar estado
        jugadorEnTurno.setPaso(destino);
        jugadorEnTurno.setPosX(casilla.getPosX());
        jugadorEnTurno.setPosY(casilla.getPosY());

        System.out.println("Casilla destino: " + casilla.getClass().getSimpleName() + " ID: " + casilla.getId());

        // 5) Efecto
        if (casilla instanceof DePaso) {
            System.out.println("Casilla de paso - cambiar turno");
            cambiarTurno(jugadorEnTurno);
        } else if (casilla instanceof PortalJurasico) {
            System.out.println("Activando Portal Jurásico");
            Jugador otro = obtenerOtroJugador(jugadorEnTurno);
            ((PortalJurasico) casilla).efecto(jugadorEnTurno, otro);
            cambiarTurno(jugadorEnTurno);
        } else if (casilla instanceof TrampaDeDinosaurio) {
            System.out.println("Activando Trampa de Dinosaurio");
            casilla.efecto(jugadorEnTurno);
            cambiarTurno(jugadorEnTurno);
        } else if (casilla instanceof DeHallazgo) {
            System.out.println("Activando Casilla de Hallazgo - NO cambiar turno aún");
            DeHallazgo hallazgo = (DeHallazgo) casilla;
            hallazgo.efecto(jugadorEnTurno);
            this.casillaHallazgoActiva = hallazgo;
            this.esperandoAccionHallazgo = true; // ← NO cambiar turno aquí
            // IMPORTANTE: NO llamar a cambiarTurno() aquí
        } else {
            System.out.println("Activando efecto genérico");
            casilla.efecto(jugadorEnTurno);
            cambiarTurno(jugadorEnTurno);
        }

        // 6) Reubicar posición visual
        Casilla casillaFinal = obtenerCasillaPorId(jugadorEnTurno.getPaso());
        if (casillaFinal != null) {
            jugadorEnTurno.setPosX(casillaFinal.getPosX() + 10);
            jugadorEnTurno.setPosY(casillaFinal.getPosY() + 10);
        }
        
        System.out.println("=== MOVIMIENTO TERMINADO ===");
    }

    // Nuevo método para completar el turno después de tomar carta
    public void completarTurnoHallazgo(Jugador jugador) {
        if (this.esperandoAccionHallazgo) {
            System.out.println("Completando turno después de hallazgo para: " + jugador.getNombre());
            this.esperandoAccionHallazgo = false;
            this.casillaHallazgoActiva = null;
            cambiarTurno(jugador);
        }
    }

    public Carta procesarSeleccionCarta(Jugador jugador) {
        if (casillaHallazgoActiva != null && casillaHallazgoActiva.isEsperandoSeleccion()) {
            Carta carta = this.mazo.escogerCarta(jugador);
            casillaHallazgoActiva.setEsperandoSeleccion(false);
            
            // Después de tomar la carta, completar el turno
            completarTurnoHallazgo(jugador);
            return carta;
        }
        return null;
    }

    public boolean hayCasillaHallazgoActiva() {
        return casillaHallazgoActiva != null && casillaHallazgoActiva.isEsperandoSeleccion();
    }

    public boolean isEsperandoAccionHallazgo() {
        return esperandoAccionHallazgo;
    }
   

    public void cambiarTurno(Jugador actual) {
        int idx = -1;
        for (int i = 0; i < this.jugadores.length; i++) {
            if (this.jugadores[i] == actual) {
                idx = i;
                break;
            }
        }
        
        if (idx != -1) {
            int next = (idx + 1) % this.jugadores.length;
            this.jugadores[idx].setTurno(false);
            this.jugadores[next].setTurno(true);
            this.jugadorEnTurno = this.jugadores[next];
            
            System.out.println("Turno cambiado a: " + this.jugadorEnTurno.getNombre());
        }
    }





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
    }//obtener jugadores

public void iniciarJuego() {
    this.juegoIniciado = true;
    // Establecer turno del primer jugador
    for (Jugador jugador : jugadores) {
        if (jugador.isTurno()) {
            this.jugadorEnTurno = jugador;
            break;
        }
    }
    System.out.println("Juego iniciado. Primer turno: " + this.jugadorEnTurno.getNombre());
}//iniciar juego


    
 
    
    // Método para obtener el mensaje de la casilla activa
    public String getMensajeCasillaHallazgo() {
        if (hayCasillaHallazgoActiva()) {
            return "Debes tomar una carta del mazo por haber caído en casilla de hallazgo";
        }
        return null;
    }

    
}//clase
