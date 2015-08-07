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
    
    
    
    
    public void insertar(String pEscuela){
        Zona tmp = new Zona(pEscuela);
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
    
    public Zona buscar(int pIndice){
        Zona tmp = _head;
        for(int i=0;i<_tam && i<pIndice;i++){
            tmp = tmp.getNext();
        }
        return tmp;
    }
    
    public void borrar(int pIndice){
        if(pIndice < _tam){
            Zona tmp = _head;
            for(int i=0;i<_tam && i<pIndice;i++){
                tmp = tmp.getNext();
            }
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
    }
    
    

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
