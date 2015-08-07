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
public class ListaUsers {
    
    private User _head = null;
    private User _tail = null;
    private int _tam = 0;

    /**
     *
     * @param pNombre
     * @param pToken
     * @param pLong
     * @param pEscuela
     * @param pLat
     * @param pPuntaje
     * @param pAdmin
     */
    public void insertar(String pNombre,String pToken,String pEscuela,double pLat,double pLong,int pPuntaje, boolean pAdmin){
        User tmp = new User(pNombre,pToken,pEscuela,pLat,pLong,pPuntaje,pAdmin );
        
        if(_head == null){
            _head = tmp;
            _tail = tmp;
        }
        else{
            _tail.setNext(tmp);
            tmp.setPrev(_tail);
            _tail = tmp;
        }
        _tam++;
        
    }
    
    /**
     *
     * @param ptoken
     */
    public void borrarConToken(String ptoken){
        
        User tmp = _head;
        
        while(tmp!=null){
            
            if(tmp.getToken().compareTo(ptoken) == 0){
                
                if(_tam ==1){
                    _head = null;
                    _tail = null;
                }
                
                //se valida si es el primer elemento
                else if(tmp == _head){
                    tmp.getNext().setPrev(null);
                    _head = tmp.getNext();
                    _tam--;
                }
                
                //se valida si es el ultimo elemento
                else if(tmp == _tail){
                    tmp.getPrev().setNext(null);
                    _tail = tmp.getPrev();
                    _tam--;
                }
                else{
                    tmp.getPrev().setNext(tmp.getNext());
                    tmp.getNext().setPrev(tmp.getPrev());
                    _tam--;
                }
                
            }
            tmp = tmp.getNext();
        }
    }
    
    /**
     *
     * @param ptoken
     * @return
     */
    public User buscar(String ptoken){
        User tmp = _head;
        
        while(tmp!=null){
            if(tmp.getToken().compareTo(ptoken)==0){
                return tmp;
            }
            tmp=tmp.getNext();
        }
        return null;
    }
    
    /**
     *
     * @param pIndice
     * @return
     */
    public User buscarConIndice(int pIndice){
        User tmp = _head;
        int i=0;
        if(pIndice>=_tam){
            return null;
        }
        while(i<pIndice){
            tmp=tmp.getNext();
            i++;
        }
        return tmp;
    }
    
    
    
    
    
    
    
    
    /**
     * @return the _head
     */
    public User getHead() {
        return _head;
    }

    /**
     * @param phead the _head to set
     */
    public void setHead(User phead) {
        _head = phead;
    }

    /**
     * @return the _tail
     */
    public User getTail() {
        return _tail;
    }

    /**
     * @param ptail the _tail to set
     */
    public void setTail(User ptail) {
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
