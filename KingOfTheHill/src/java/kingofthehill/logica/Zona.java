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
    private String _lat1 ="";
    private String _long1 = "";
    private String _lat2 ="";
    private String _long2 = "";
    
    public Zona(String pEscuela,String pLat1,String pLong1,String pLat2,String pLong2){
        _escuela = pEscuela;
        _lat1 = pLat1;
        _long1 = pLong1;
        _lat2 = pLat2;
        _long2 = pLong2;
    }

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

    /**
     * @return the _lat1
     */
    public String getLat1() {
        return _lat1;
    }

    /**
     * @param _lat1 the _lat1 to set
     */
    public void setLat1(String _lat1) {
        this._lat1 = _lat1;
    }

    /**
     * @return the _long1
     */
    public String getLong1() {
        return _long1;
    }

    /**
     * @param _long1 the _long1 to set
     */
    public void setLong1(String _long1) {
        this._long1 = _long1;
    }

    /**
     * @return the _lat2
     */
    public String getLat2() {
        return _lat2;
    }

    /**
     * @param _lat2 the _lat2 to set
     */
    public void setLat2(String _lat2) {
        this._lat2 = _lat2;
    }

    /**
     * @return the _long2
     */
    public String getLong2() {
        return _long2;
    }

    /**
     * @param _long2 the _long2 to set
     */
    public void setLong2(String _long2) {
        this._long2 = _long2;
    }
    
    
}
