package kingofthehill.logica;

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
    public User defensor(String pZona,String pEscuela, String pRival){
        return _usersList.defensor(pZona, pEscuela,pRival);
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
    public ListaUsers getAllUsers(){
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
             System.out.println("First run...");
            System.out.println("Usuario: " + pNombre + ",conectando.");
            _usersList.insertar(pNombre, pToken, pEscuela, pLat, pLong, pPuntaje, pAdmin);
            Regiones.getInstance();
            _firstRun = false;
        } else {
            System.out.println("Usuario: " + pNombre + ",conectando.");
            _usersList.insertar(pNombre, pToken, pEscuela, pLat, pLong, pPuntaje, pAdmin);
        }
    }

    /**
     *
     * @param pUser
     */
    public void conectarJugador(User pUser){
        if(pUser.isBot()){
            _usersList.insertar(pUser.getNombre(), pUser.getToken(), pUser.getEscuela(), 
                pUser.getLat(), pUser.getLong(), pUser.getPuntaje(), false);
            _usersList.buscar(pUser.getToken()).setIsBot(true);
        }
        else {
            System.out.println("Bot connecting... Error :S");
        }
        
    }
    
    /**
     *
     * @param pToken
     * @return
     */
    public boolean desconectarJugador(String pToken) {
        System.out.println("Usuario: " + _usersList.buscar(pToken).getNombre() + ",desconectando.");
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
                object.put("alive",user.isSuicidarme());
                object.put("color",user.getEscuela().subSequence(1, user.getEscuela().length()));
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
            object.put("color",user.getEscuela().subSequence(1, user.getEscuela().length()));
            array.add(object);
        }
        return array;
    }
    
    

}
