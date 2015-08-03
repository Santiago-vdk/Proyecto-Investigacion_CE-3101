/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.logica;

/**
 *
 * @author RafaelAngel
 */
public class Zona {
    private Zona _next = null;
    private Zona _prev = null;
    private String _escuela = "";

    /**
     * @return the _next
     */
    public Zona getNext() {
        return _next;
    }

    /**
     * @param pnext the _next to set
     */
    public void setNext(Zona pnext) {
        _next = pnext;
    }

    /**
     * @return the _prev
     */
    public Zona getPrev() {
        return _prev;
    }

    /**
     * @param pprev the _prev to set
     */
    public void setPrev(Zona pprev) {
        _prev = pprev;
    }

    /**
     * @return the _escuela
     */
    public String getEscuela() {
        return _escuela;
    }

    /**
     * @param pescuela the _escuela to set
     */
    public void setEscuela(String pescuela) {
        _escuela = pescuela;
    }
    
    
}
