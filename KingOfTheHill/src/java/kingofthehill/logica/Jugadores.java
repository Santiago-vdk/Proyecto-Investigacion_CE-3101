package kingofthehill.logica;

import org.json.simple.*;

/**
 *
 * @author Shagy
 */
public class Jugadores {

    private int _admins = 0;
    private static Jugadores _singleton = new Jugadores();
    ListaUsers _usersList;

    private Jugadores() {
        _usersList = new ListaUsers();
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
     * @param pNombre
     * @param pToken
     * @param pEscuela
     * @param pLat
     * @param pLong
     * @param pPuntaje
     * @param pAdmin
     */
    public void conectarJugador(String pNombre, String pToken, String pEscuela, double pLat, double pLong, int pPuntaje, boolean pAdmin) {
        _usersList.insertar(pNombre, pToken, pEscuela, pLat, pLong, pPuntaje, pAdmin);
        System.out.println("Usuario: " + pNombre + ",conectando.");
    }

    /**
     *
     * @param pToken
     * @return
     */
    public boolean desconectarJugador(String pToken) {
        //System.out.println("Usuario: " + _usersList.buscar(pToken).getNombre() + ",desconectando.");
        _usersList.borrarConToken(pToken); 
        return true;
    }

    /**
     *
     * @param pToken
     * @return
     */
    public User buscarJugador(String pToken) {
        return _usersList.buscar(pToken);
    }

    /**
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
                array.add(object);
            }
        }
        return array;
    }
    
    /**
     *
     * @param pToken
     * @return
     */
    public JSONArray posicionJugador(String pToken) {
        User user;
        JSONArray array = new JSONArray();

        JSONObject object = new JSONObject();
        user = _usersList.buscar(pToken);
        if (!user.isAdmin()) {
            object.put("username", user.getNombre());
            object.put("lat", user.getLat());
            object.put("long", user.getLong());
            array.add(object);
        }
        System.out.println(array.get(0));
        return array;
    }

}
