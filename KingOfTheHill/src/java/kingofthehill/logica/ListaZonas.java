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
     *
     * @param pNombre
     * @param pLat1
     * @param pLong1
     * @param pLat2
     * @param pLong2
     * @param pColor
     */
    public void insertar(String pNombre,String pLat1,String pLong1,String pLat2,String pLong2,String pColor){
        Zona tmp = new Zona(pNombre,pLat1,pLong1,pLat2,pLong2,pColor);
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
     * @param pIndice
     * @return
     */
    public Zona buscar(int pIndice){
        Zona tmp = _head;
        for(int i=0;i<_tam && i<pIndice;i++){
            tmp = tmp.getNext();
        }
        return tmp;
    }
    
    /**
     *
     * @param pNombre
     * @return
     */
    public Zona buscarPorNombre(String pNombre){
        Zona tmp = _head;
        while(tmp != null && tmp.getNombre().compareTo(pNombre) != 0) {
            tmp = tmp.getNext();
        }
        return tmp;
    }
    
    /**
     *
     * @param pIndice
     */
    public void borrar(int pIndice){
        if(pIndice < _tam){
            Zona tmp = _head;
            for(int i=0;i<_tam && i<pIndice;i++){
                tmp = tmp.getNext();
            }
            if(_tam ==1){
                    _head = null;
                    _tail = null;
                    _tam = 0;
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
     * @param pHead
     */
    public void setHead(Zona pHead) {
        _head = pHead;
    }

    /**
     * @return the _tail
     */
    public Zona getTail() {
        return _tail;
    }

    /**
     * @param pTail
     */
    public void setTail(Zona pTail) {
        _tail = pTail;
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
