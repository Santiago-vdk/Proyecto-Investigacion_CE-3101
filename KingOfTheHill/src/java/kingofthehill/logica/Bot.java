package kingofthehill.logica;

import com.googlecode.jeneratedata.numbers.IntegerGenerator;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shagy
 */
public class Bot extends Thread {

    private String _token;
    private double _initLat;
    private double _initLong;
    private double _lat;
    private double _long;
    private ArrayList<ArrayList<String>> _listRutas = new ArrayList<ArrayList<String>>();
    private int _goingTo = -1;
    private IntegerGenerator number = new IntegerGenerator(15);
    private IntegerGenerator score = new IntegerGenerator(70);
    //private User _user;
    
    /**
     *
     * @param pToken
     * @param pLat
     * @param pLong
     */
    public Bot(String pToken, double pLat, double pLong) {
        _token = pToken;
        _initLat = pLat;
        _initLong = pLong;
        cargarRutas();
        _goingTo = number.generate();
        //_user = Jugadores.getInstance().buscarJugador(pToken);
        start();
    }

    @Override
    public void run() {
        _lat = _initLat;
        _long = _initLong;
        try {
            while (!Jugadores.getInstance().buscarJugador(_token).isSuicidarme()) {
                try {
                    Thread.sleep(500);
                    if (Jugadores.getInstance().buscarJugador(_token).isEnPelea()) {
                        BattleManager.getInstance().scoreBatalla(_token, (float) score.generate());
                        Thread.sleep(500);
                    }
                    if (_goingTo != -1) {
                        double toLat = Double.parseDouble(_listRutas.get(_goingTo).get(0));
                        double toLong = Double.parseDouble(_listRutas.get(_goingTo).get(1));
                        double toLat2 = Double.parseDouble(_listRutas.get(_goingTo).get(2));
                        double toLong2 = Double.parseDouble(_listRutas.get(_goingTo).get(3));
                        if (_lat < toLat && _long > toLong && _lat > toLat2 && _long < toLong2) {
                            //System.out.println("Llegue");
                            //Llegue al lugar, vuelvo al punto de inicio
                            while (_goingTo != -1) {
                                Thread.sleep(3000);
                                _goingTo = -1;
                            }
                        } else {
                            // System.out.println("Voy al nuevo lugar");
                            if (_lat < toLat && _long > toLong && _lat > toLat2 && _long < toLong2) {
                                System.out.println(Jugadores.getInstance().buscarJugador(_token).getNombre() + "Break");
                                //_goingTo = -1;
                                break;
                            }
                            if (_lat >= toLat) {
                                _lat = _lat - 0.00001; 
                            }
                            if (_lat <= toLat2) {
                                _lat = _lat + 0.00001;
                            }
                            if (_long >= toLong) {
                                _long = _long - 0.00001;
                            }
                            if (_long <= toLong2) {
                                _long = _long + 0.00001;
                            }
                            
                            //if (Jugadores.getInstance().buscarJugador(_token) != null) {
                                Jugadores.getInstance().buscarJugador(_token).setLat(_lat);
                                Jugadores.getInstance().buscarJugador(_token).setLong(_long);
                                BattleManager.getInstance().nuevaBatalla(Jugadores.getInstance().buscarJugador(_token), _lat, _long);
                            //}
                        }
                    } else {
                        //System.out.println("Tengo adonde ir");
                        _goingTo = number.generate();
                    }
                } catch (InterruptedException | NullPointerException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("BOT thread Died 1");
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("BOT thread Died 2");
        }

    }

    private void cargarRutas() {
        //Escuela de Fisica
        ArrayList<String> ruta1 = new ArrayList<String>();
        ruta1.add("9.856089");
        ruta1.add("-83.913229");
        ruta1.add("9.856038");
        ruta1.add("-83.913177");
        _listRutas.add(ruta1);

        //B-3
        ArrayList<String> ruta2 = new ArrayList<String>();
        ruta2.add("9.856397"); 
        ruta2.add("-83.912681");
        ruta2.add("9.856330");
        ruta2.add("-83.912504");
        _listRutas.add(ruta2);

        //B-2
        ArrayList<String> ruta3 = new ArrayList<String>();
        ruta3.add("9.856175");
        ruta3.add("-83.912674");
        ruta3.add("9.856102"); 
        ruta3.add("-83.912491");
        _listRutas.add(ruta3);
        
        //B-1
        ArrayList<String> ruta4 = new ArrayList<String>();
        ruta4.add("9.855974");
        ruta4.add("-83.912685");
        ruta4.add("9.855906"); 
        ruta4.add("-83.912532");
        _listRutas.add(ruta4);
        
         //B-6
        ArrayList<String> ruta5 = new ArrayList<String>();
        ruta5.add("9.856044");
        ruta5.add("-83.912170");
        ruta5.add("9.855970");
        ruta5.add("-83.911974");
        _listRutas.add(ruta5);
        
        //C-1
        ArrayList<String> ruta6 = new ArrayList<String>();
        ruta6.add("9.855746");
        ruta6.add("-83.913240");
        ruta6.add("9.855674");
        ruta6.add("-83.913086");
        _listRutas.add(ruta6);
        
        //C-10
        ArrayList<String> ruta7 = new ArrayList<String>();
        ruta7.add("9.855481");
        ruta7.add("-83.913090");
        ruta7.add("9.855290");
        ruta7.add("-83.912784");
        _listRutas.add(ruta7);
        
        //A-3
        ArrayList<String> ruta8 = new ArrayList<String>();
        ruta8.add("9.856645");
        ruta8.add("-83.913082");
        ruta8.add("9.856562");
        ruta8.add("-83.912924");
        _listRutas.add(ruta8);
        
        //A-2
        ArrayList<String> ruta9 = new ArrayList<String>();
        ruta9.add("9.856943");
        ruta9.add("-83.912634");
        ruta9.add("9.856682"); 
        ruta9.add("-83.9125764");
        _listRutas.add(ruta9);

        //B-6 UP
        ArrayList<String> ruta10 = new ArrayList<String>();
        ruta10.add("9.856614"); 
        ruta10.add("-83.912196");
        ruta10.add("9.856508");
        ruta10.add("-83.911842");
        _listRutas.add(ruta10);

        //D-1
        ArrayList<String> ruta11 = new ArrayList<String>();
        ruta11.add("9.855730");
        ruta11.add("-83.912366");
        ruta11.add("9.855631");
        ruta11.add("-83.912194");
        _listRutas.add(ruta11);

        //D-2
        ArrayList<String> ruta12 = new ArrayList<String>();
        ruta12.add("9.855660");
        ruta12.add("-83.911929");
        ruta12.add("9.855580");
        ruta12.add("-83.911762");
        _listRutas.add(ruta12);

        //Libreria
        ArrayList<String> ruta13 = new ArrayList<String>();
        ruta13.add("9.855505");
        ruta13.add("-83.912323");
        ruta13.add("9.855374"); 
        ruta13.add("-83.912163");
        _listRutas.add(ruta13);

        //Escuela de mate
        ArrayList<String> ruta14 = new ArrayList<String>();
        ruta14.add("9.856291"); 
        ruta14.add("-83.913212");
        ruta14.add("9.856242");
        ruta14.add("-83.913096");
        _listRutas.add(ruta14);

        //Cancha
        ArrayList<String> ruta15 = new ArrayList<String>();
        ruta15.add("9.856251"); 
        ruta15.add("-83.909614");
        ruta15.add("9.855919");
        ruta15.add("-83.909218");
        _listRutas.add(ruta15);


    }
}
