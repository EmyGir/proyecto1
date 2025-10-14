/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;
import java.util.Timer;

/**
 *
 * @author emily
 */
public class Reto extends Carta{
    private Timer timer;
    private ArrayList<String>problemas;

    public Reto(int id) {
        super(id);
    }

    
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public ArrayList<String> getProblemas() {
        return problemas;
    }

    public void setProblemas(ArrayList<String> problemas) {
        this.problemas = problemas;
    }

    @Override
    public void efecto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}//clase
