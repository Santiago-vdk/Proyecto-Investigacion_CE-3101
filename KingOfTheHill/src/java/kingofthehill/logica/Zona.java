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
    private String _color = "";
    private String _lat1 ="";
    private String _long1 = "";
    private String _lat2 ="";
    private String _long2 = "";
    private String _nombre ="";
    
    /**
     *
     * @param pNombre
     * @param pLat1
     * @param pLong1
     * @param pLat2
     * @param pLong2
     * @param pColor
     */
    public Zona(String pNombre,String pLat1,String pLong1,String pLat2,String pLong2,String pColor){
        _color = pColor;
        _lat1 = pLat1;
        _long1 = pLong1;
        _lat2 = pLat2;
        _long2 = pLong2;
        _nombre = pNombre;
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
     * @return the _color
     */
    public String getColor() {
        return _color;
    }

    /**
     * @param pescuela the _color to set
     */
    public void setColor(String pescuela) {
        _color = pescuela;
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

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param _nombre the _nombre to set
     */
    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
    
    
}
