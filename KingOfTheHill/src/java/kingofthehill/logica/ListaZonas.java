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
public class ListaZonas {
    private Zona _head = null;
    private Zona _tail = null;
    private int _tam = 0;

    /**
     * @return the _head
     */
    public Zona getHead() {
        return _head;
    }

    /**
     * @param phead the _head to set
     */
    public void setHead(Zona phead) {
        _head = phead;
    }

    /**
     * @return the _tail
     */
    public Zona getTail() {
        return _tail;
    }

    /**
     * @param ptail the _tail to set
     */
    public void setTail(Zona ptail) {
        _tail = ptail;
    }

    /**
     * @return the _tam
     */
    public int getTam() {
        return _tam;
    }

    /**
     * @param ptam the _tam to set
     */
    public void setTam(int ptam) {
        _tam = ptam;
    }
}
