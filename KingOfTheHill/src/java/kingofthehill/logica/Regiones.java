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
public class Regiones {
    
    private static Regiones _singleton = new Regiones();
    ListaZonas _zonas;
    
    private Regiones() {
        _zonas = new ListaZonas();
    }

    
    public static Regiones getInstance() {
        return _singleton;
    }

    
    
}
