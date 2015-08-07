/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tempLate file, choose Tools | TempLates
 * and open the tempLate in the editor.
 */
package kingofthehill.logica;

/**
 *
 * @author RafaelAngel
 */
public class User {
    private User _next = null;
    private User _prev = null;
    private String _nombre = "";
    private String _token = "";
    private String _escuela = "";
    private int _puntaje =  0;
    private double _lat = 0;
    private double _long = 0;
    private boolean _admin;
    private boolean _enPelea=false;
    private String _zonaPrevia ="";

    /**
     *
     * @param pNombre
     * @param pToken
     * @param pEscuela
     * @param pLat
     * @param pLong
     * @param pPuntaje
     * @param pAdmin
     */
    public User(String pNombre,String pToken,String pEscuela,double pLat,double pLong,int pPuntaje,boolean pAdmin){
        _nombre = pNombre;
        _token = pToken;
        _escuela = pEscuela;
        _lat = pLat;
        _long = pLong;
        _puntaje  = pPuntaje;
        _admin = pAdmin;
    }
    
    /**
     *
     * @param pZona
     * @return
     */
    public boolean CambioZona(String pZona){
        if(_zonaPrevia.compareTo(pZona)!=0){
            _zonaPrevia = pZona;
            return true;
        }
        return false;
    }
    /**
     * @return the _next
     */
    public User getNext() {
        return _next;
    }

    /**
     * @param pNext the _next to set
     */
    public void setNext(User pNext) {
        _next = pNext;
    }

    /**
     * @return the _prev
     */
    public User getPrev() {
        return _prev;
    }

    /**
     * @param pPrev the _prev to set
     */
    public void setPrev(User pPrev) {
        _prev = pPrev;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param pNombre the _nombre to set
     */
    public void setNombre(String pNombre) {
        _nombre = pNombre;
    }

    /**
     * @return the _puntaje
     */
    public int getPuntaje() {
        return _puntaje;
    }

    /**
     * @param pPuntaje the puntaje to set
     */
    public void setPuntaje(int pPuntaje) {
        _puntaje = pPuntaje;
    }

    /**
     * @return the _lat
     */
    public double getLat() {
        return _lat;
    }

    /**
     * @param pLat the _lat to set
     */
    public void setLat(double pLat) {
        _lat = pLat;
    }

    /**
     * @return the _long
     */
    public double getLong() {
        return _long;
    }

    /**
     * @param pLong the _long to set
     */
    public void setLong(double pLong) {
        _long = pLong;
    }

    /**
     * @return the _token
     */
    public String getToken() {
        return _token;
    }

    /**
     * @param pToken the _token to set
     */
    public void setToken(String pToken) {
        _token = pToken;
    }

    /**
     * @return the _escuela
     */
    public String getEscuela() {
        return _escuela;
    }

    /**
     * @param pEscuela the _escuela to set
     */
    public void setEscuela(String pEscuela) {
        _escuela = pEscuela;
    }

    /**
     * @return the _admin
     */
    public boolean isAdmin() {
        return _admin;
    }

    /**
     * @param pAdmin the pAdmin to set
     */
    public void setAdmin(boolean pAdmin) {
        _admin = pAdmin;
    }

    /**
     * @return the _enPelea
     */
    public boolean isEnPelea() {
        return _enPelea;
    }

    /**
     * @param _enPelea the _enPelea to set
     */
    public void setEnPelea(boolean _enPelea) {
        this._enPelea = _enPelea;
    }

    /**
     * @return the _zonaPrevia
     */
    public String getZonaPrevia() {
        return _zonaPrevia;
    }

    /**
     * @param _zonaPrevia the _zonaPrevia to set
     */
    public void setZonaPrevia(String _zonaPrevia) {
        this._zonaPrevia = _zonaPrevia;
    }
    
}
