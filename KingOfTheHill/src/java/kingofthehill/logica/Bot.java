package kingofthehill.logica;

import com.googlecode.jeneratedata.numbers.DoubleGenerator;
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
    private IntegerGenerator number = new IntegerGenerator(3);
    private IntegerGenerator score = new IntegerGenerator(70);
    //private User _user;

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
                        Thread.sleep(1000);
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
                            if (((toLat < _lat) && (_lat >= toLat2)) && ((toLong < _long) && (_long >= toLong2))) {
                                break;
                            }
                            if (_lat > toLat) {
                                _lat = _lat - 0.00001; //Corregir
                            } else {
                                _lat = _lat + 0.00001;
                            }
                            if (_long > toLong) {
                                _long = _long - 0.00001;
                            } else {
                                _long = _long + 0.00001;
                            }
                            if (Jugadores.getInstance().buscarJugador(_token) != null) {
                                Jugadores.getInstance().buscarJugador(_token).setLat(_lat);
                                Jugadores.getInstance().buscarJugador(_token).setLong(_long);
                                BattleManager.getInstance().nuevaBatalla(Jugadores.getInstance().buscarJugador(_token), _lat, _long);
                            }
                        }
                    } else {
                        //System.out.println("Tengo adonde ir");
                        _goingTo = number.generate();
                    }
                } catch (InterruptedException | NullPointerException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("BOT thread Died 1");
                    //this.stop();
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("BOT thread Died 2");
        }

    }

    private void cargarRutas() {
        ArrayList<String> ruta1 = new ArrayList<String>();
        ruta1.add("9.856089");
        ruta1.add("-83.913229");
        ruta1.add("9.856038");
        ruta1.add("-83.913177");
        _listRutas.add(ruta1);

        ArrayList<String> ruta2 = new ArrayList<String>();
        ruta2.add("9.856384");
        ruta2.add("-83.912648");
        ruta2.add("9.856334");
        ruta2.add("-83.91256");
        _listRutas.add(ruta2);

        ArrayList<String> ruta3 = new ArrayList<String>();
        ruta3.add("9.855725");
        ruta3.add("-83.912332");
        ruta3.add("9.855673");
        ruta3.add("-83.912249");
        _listRutas.add(ruta3);

    }
}
