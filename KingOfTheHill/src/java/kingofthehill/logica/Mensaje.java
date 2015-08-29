/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.logica;

/**
 *
 * @author Shagy
 */
public class Mensaje {
    private Mensaje _next=null;
    private Mensaje _prev=null;
    private String _msj="";

    /**
     *
     * @param pMsj
     */
    public Mensaje(String pMsj){
        _msj = pMsj;
    }
    /**
     * @return the _next
     */
    public Mensaje getNext() {
        return _next;
    }

    /**
     * @param pNext the _next to set
     */
    public void setNext(Mensaje pNext) {
        _next = pNext;
    }

    /**
     * @return the _prev
     */
    public Mensaje getPrev() {
        return _prev;
    }

    /**
     * @param pPrev
     */
    public void setPrev(Mensaje pPrev) {
        _prev = pPrev;
    }

    /**
     * @return the _msj
     */
    public String getMsj() {
        return _msj;
    }

    /**
     * @param pMsj the _msj to set
     */
    public void setMsj(String pMsj) {
        _msj = pMsj;
    }
    
    
    
}
