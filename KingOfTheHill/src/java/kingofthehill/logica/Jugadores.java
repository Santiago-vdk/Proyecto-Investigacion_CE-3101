package kingofthehill.logica;

import kingofthehill.datos.conexionBD;
import org.json.simple.*;

/**
 *
 * @author RafaelAngel
 */
public class Jugadores {

    private boolean _firstRun = true;
    private static Jugadores _singleton = new Jugadores();
    ListaUsers _usersList;

    private Jugadores() {
        _usersList = new ListaUsers();
    }

    /**
     *
     * @param pZona
     * @param pEscuela
     * @param pRival
     * @return
     */
    public User defensor(String pZona, String pEscuela, String pRival) {
        return _usersList.defensor(pZona, pEscuela, pRival);
    }

    /**
     *
     * @return
     */
    public static Jugadores getInstance() {
        return _singleton;
    }

    /**
     *
     * @return
     */
    public ListaUsers getAllUsers() {
        return _usersList;
    }

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
    public void conectarJugador(String pNombre, String pToken, String pEscuela, double pLat, double pLong, int pPuntaje, boolean pAdmin) {
        if (_firstRun) {
            System.out.println("Usuario: " + pNombre + ", conectando.");
            _usersList.insertar(pNombre, pToken, pEscuela, pLat, pLong, pPuntaje, pAdmin);
            Regiones.getInstance();
            _firstRun = false;
        } else {
            System.out.println("Usuario: " + pNombre + ", conectando.");
            _usersList.insertar(pNombre, pToken, pEscuela, pLat, pLong, pPuntaje, pAdmin);
        }
    }

    /**
     * Para conectar un bot.
     * 
     * @param pUser
     */
    public void conectarJugador(User pUser) {
        if (pUser.isBot()) {
            _usersList.insertar(pUser.getNombre(), pUser.getToken(), pUser.getEscuela(),
                    pUser.getLat(), pUser.getLong(), pUser.getPuntaje(), false);
            _usersList.buscar(pUser.getToken()).setIsBot(true);
        } else {
            System.out.println("Bot connecting... Error :S");
        }

    }

    /**
     * Desconecta jugadores por medio del token.
     * 
     * @param pToken
     * @return
     */
    public boolean desconectarJugador(String pToken) {
        System.out.println("Usuario: " + _usersList.buscar(pToken).getNombre() + ", desconectando.");

        //Guardo el puntaje del jugador
        conexionBD.getInstance().actualizarPuntaje(_usersList.buscar(pToken).getNombre(), _usersList.buscar(pToken).getPuntaje());

        //Borro al jugador logico
        _usersList.borrarConToken(pToken);

        return true;
    }

    /**
     * Realiza busqueda de jugadores por medio del token.
     * 
     * @param pToken
     * @return
     */
    public User buscarJugador(String pToken) {
        return _usersList.buscar(pToken);
    }

    /**
     * Crea JSON con la posicion de todos los jugadores.
     * 
     * @return
     */
    public JSONArray posicionJugadores() {
        User user;
        JSONArray array = new JSONArray();
        for (int i = 0; i < _usersList.getTam(); i++) {
            JSONObject object = new JSONObject();
            user = _usersList.buscarConIndice(i);
            if (!user.isAdmin()) {
                object.put("username", user.getNombre());
                object.put("lat", user.getLat());
                object.put("long", user.getLong());
                object.put("alive", user.isSuicidarme());
                object.put("color", user.getEscuela().subSequence(1, user.getEscuela().length()));
                array.add(object);
            }
        }
        return array;
    }

    /**
     * Crea JSON para responder la posicion de 1 jugador.
     * 
     * @param pToken
     * @return
     */
    public JSONObject posicionJugador(String pToken) {
        User user;
        //JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        user = _usersList.buscar(pToken);
        if (!user.isAdmin()) {
            object.put("username", user.getNombre());
            object.put("lat", user.getLat());
            object.put("long", user.getLong());
            object.put("color", user.getEscuela().subSequence(1, user.getEscuela().length()));
            //array.add(object);
        }
        return object;
    }
}
