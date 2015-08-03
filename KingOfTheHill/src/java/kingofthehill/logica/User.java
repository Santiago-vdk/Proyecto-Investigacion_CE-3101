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
public class User {
    private User _next = null;
    private User _prev = null;
    private String _nombre = "";
    private String _token = "";
    private String _escuela = "";
    private int _puntaje =  0;
    private double _lat = 0;
    private double _long = 0;

    public User(String pnombre,String ptoken,String pescuela,double plat,double plong,int ppuntaje){
        _nombre = pnombre;
        _token = ptoken;
        _escuela = pescuela;
        _lat = plat;
        _long = plong;
        _puntaje  = ppuntaje;
    }
    
    /**
     * @return the _next
     */
    public User getNext() {
        return _next;
    }

    /**
     * @param pnext the _next to set
     */
    public void setNext(User pnext) {
        _next = pnext;
    }

    /**
     * @return the _prev
     */
    public User getPrev() {
        return _prev;
    }

    /**
     * @param pprev the _prev to set
     */
    public void setPrev(User pprev) {
        _prev = pprev;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param pnombre the _nombre to set
     */
    public void setNombre(String pnombre) {
        _nombre = pnombre;
    }

    /**
     * @return the _puntaje
     */
    public int getPuntaje() {
        return _puntaje;
    }

    /**
     * @param ppuntaje the puntaje to set
     */
    public void setPuntaje(int ppuntaje) {
        _puntaje = ppuntaje;
    }

    /**
     * @return the _lat
     */
    public double getLat() {
        return _lat;
    }

    /**
     * @param plat the _lat to set
     */
    public void setLat(double plat) {
        _lat = plat;
    }

    /**
     * @return the _long
     */
    public double getLong() {
        return _long;
    }

    /**
     * @param plong the _long to set
     */
    public void setLong(double plong) {
        _long = plong;
    }

    /**
     * @return the _token
     */
    public String getToken() {
        return _token;
    }

    /**
     * @param ptoken the _token to set
     */
    public void setToken(String ptoken) {
        _token = ptoken;
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
    
    
}
