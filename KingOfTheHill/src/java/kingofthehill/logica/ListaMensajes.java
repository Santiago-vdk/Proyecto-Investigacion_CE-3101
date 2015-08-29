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
public class ListaMensajes {
    private Mensaje _head=null;
    private Mensaje _tail=null;
    private int _tam=0;
    
    /**
     *
     * @param pMsj
     */
    public void insertar(String pMsj){
        Mensaje tmp = new Mensaje(pMsj);
    if (_head == null) {
            _head = tmp;
            _tail = tmp;
        } 
    else {
            _tail.setNext(tmp);
            tmp.setPrev(_tail);
            _tail = tmp;
        }
        _tam ++;
    }
    
    /**
     *
     * @return
     */
    public String pop(){
        if(getTam() > 0){
            String tmp = _head.getMsj();
            
            if(getTam()==1){
                _head = null;
                _tail = null;
            }
            else{
                _head.getNext().setPrev(null);
                _head = _head.getNext();
            }
            _tam --;
            return tmp;
        }
        return null;
    }

    /**
     * @return the _tam
     */
    public int getTam() {
        return _tam;
    }

    /**
     * @param pTam the _tam to set
     */
    public void setTam(int pTam) {
        _tam = pTam;
    }
 
    
}
