package kingofthehill.logica;

/**
 *
 * @author RafaelAngel
 */
public class ListaBatallas {
    private Batalla _head=null;
    private Batalla _tail=null;
    private int _tam = 0;
    
    /**
     *
     * @param pBatalla
     */
    public void insertar(Batalla pBatalla){
        if(_head == null){
            _head = pBatalla;
            _tail = pBatalla;
        }
        else{
            _tail.setNext(pBatalla);
            pBatalla.setPrev(_tail);
            _tail = pBatalla;
        }
        _tam++;
    
    }

    /**
     *
     * @param pToken
     * @return
     */
    public Batalla buscarConToken(String pToken){
        Batalla tmp = getHead();
        while(tmp != null && !tmp.buscarUser(pToken)){
            tmp = tmp.getNext();
        }
        return tmp;
    }
    
    /**
     *
     * @param pIndice
     * @return
     */
    public Batalla buscarConIndice(int pIndice){
        Batalla tmp = getHead();
        for(int i=0;i<getTam() && i<pIndice;i++){
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
            Batalla tmp = _head;
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
     *
     * @param pToken
     */
    public void borrarConToken(String pToken){
        Batalla tmp = getHead();
        while(tmp != null && !tmp.buscarUser(pToken)){
            tmp = tmp.getNext();
        }
        
        if(tmp!= null){
            
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
    public Batalla getHead() {
        return _head;
    }

    /**
     * @param _head the _head to set
     */
    public void setHead(Batalla _head) {
        this._head = _head;
    }

    /**
     * @return the _tail
     */
    public Batalla getTail() {
        return _tail;
    }

    /**
     * @param _tail the _tail to set
     */
    public void setTail(Batalla _tail) {
        this._tail = _tail;
    }

    /**
     * @return the _tam
     */
    public int getTam() {
        return _tam;
    }

    /**
     * @param _tam the _tam to set
     */
    public void setTam(int _tam) {
        this._tam = _tam;
    }
    
}

