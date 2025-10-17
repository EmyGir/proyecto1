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
public class Castigo extends Carta{

    public Castigo(int id) {
        super(id);
    }

   
    
    @Override
    public void efecto(Jugador jugadorEnTurno) {
      int resultado = (int) (Math.random() * 2) + 1;
       if(resultado==1){
           JOptionPane.showMessageDialog(null, "Tienes un evento especial! Retrocederas 4 casillas");
           int nuevoPaso = Math.max(0, jugadorEnTurno.getPaso() - 4); 
           jugadorEnTurno.setPaso(nuevoPaso);
           
       }else if(resultado==2){
          JOptionPane.showMessageDialog(null, "Encontraste un fosil! Perderas un turno");
          jugadorEnTurno.setTurno(false);
          
       }//
           

    }//
    
}//clase
