package kingofthehill.logica;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RafaelAngel
 */
public class Batalla extends Thread {

    private Batalla _next;
    private Batalla _prev;
    private int _scoreJugador1 = -1;
    private int _scoreJugador2 = -1;
    private User _jugador1;
    private User _jugador2;
    private int _IndiceZona = -1;

    /**
     *
     * @param pJugador1
     * @param pJugador2
     * @param pIndiceZona
     */
    public Batalla(User pJugador1, User pJugador2, int pIndiceZona) {
        _jugador1 = pJugador1;
        _jugador2 = pJugador2;
        _IndiceZona = pIndiceZona;
        start(); //Volatil
    }

    /**
     * Buscar jugadores por medio de tokens.
     * 
     * @param pToken
     * @return
     */
    public boolean buscarUser(String pToken) {

        if (_jugador1.getToken().compareTo(pToken) == 0) {
            return true;
        } else if (_jugador2.getToken().compareTo(pToken) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param pToken
     * @param pScore
     * @return
     */
    public boolean ScoreBatalla(String pToken, int pScore) {
        
        if (_jugador1.getToken().compareTo(pToken) == 0) {
            _scoreJugador1 = pScore;
            _jugador1.setEnPelea(false);
            if (_scoreJugador2 != -1) {
                return true;
            }
        } else if (_jugador2.getToken().compareTo(pToken) == 0) {
            _scoreJugador2 = pScore;
            _jugador2.setEnPelea(false);
            if (_scoreJugador1 != -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (_scoreJugador1 == -1 || _scoreJugador2 == -1) {
            try {
                //System.out.println("Score1: " + _scoreJugador1 + "," + "Score2: " + _scoreJugador2);
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Batalla.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        _jugador1.setPuntaje(_jugador1.getPuntaje() + _scoreJugador1);
        _jugador2.setPuntaje(_jugador2.getPuntaje() + _scoreJugador2);
        
        

        if (_scoreJugador1 > _scoreJugador2) {
            if (_jugador2.isBot()) {//gano jugador1 suicidar jugador2 si es bot
                _jugador2.setSuicidarme(true);
                System.out.println("Termino Batalla, el ganador fue: " + _jugador1.getNombre());
            }
            //cambiar color de zona porque perdio el defensor 
            String escuela = _jugador1.getEscuela();
            Regiones.getInstance().getZonasList().buscar(_IndiceZona).setColor(escuela);
        } else {
            if (_jugador1.isBot()) {//gano jugador2 suicidar jugador1 si es bot
                _jugador1.setSuicidarme(true);
                System.out.println("Termino Batalla, el ganador fue: " + _jugador2.getNombre());
            }
        }

    }

    /**
     * @return the _next
     */
    public Batalla getNext() {
        return _next;
    }

    /**
     * @param pNext
     */
    public void setNext(Batalla pNext) {
        _next = pNext;
    }

    /**
     * @return the _prev
     */
    public Batalla getPrev() {
        return _prev;
    }

    /**
     * @param pPrev
     */
    public void setPrev(Batalla pPrev) {
        _prev = pPrev;
    }
}
