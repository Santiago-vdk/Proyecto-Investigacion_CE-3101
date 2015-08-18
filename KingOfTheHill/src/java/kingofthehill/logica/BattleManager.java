package kingofthehill.logica;

/**
 *
 * @author RafaelAngel
 */
public class BattleManager {

    private static BattleManager _singleton = new BattleManager();
    private ListaBatallas _batallas = new ListaBatallas();

    private BattleManager() {
    }

    /**
     * Singleton constructor
     *
     * @return
     */
    public static BattleManager getInstance() {
        return _singleton;
    }

    /**
     *
     * @param pToken
     * @param pScore
     */
    public void scoreBatalla(String pToken, float pScore) {//metodo llamado por el post de batalla result
        Batalla batalla = _batallas.buscarConToken(pToken);
        if (batalla.buscarUser(pToken)) {       
            boolean flag = batalla.ScoreBatalla(pToken, (int) pScore);
            if (flag) {
                _batallas.borrarConToken(pToken);
            }
        }
    }

    /**
     *
     * @param pUser
     * @param pLat
     * @param pLong
     */
    public void nuevaBatalla(User pUser, double pLat, double pLong) {
        Batalla batalla = battleNeeded(pUser, pLat, pLong);
        if (batalla != null) {
            _batallas.insertar(batalla);
        }
    }


    //Meter en lista de batallas
    public Batalla battleNeeded(User user, Double LatJugador, Double LongJugador) {

        for (int i = 0; i < Regiones.getInstance().getZonasList().getTam(); i++) {
            String nombre = Regiones.getInstance().getZonasList().buscar(i).getNombre();
            String color = Regiones.getInstance().getZonasList().buscar(i).getColor();

               
            Double lat1 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLat1());
            Double long1 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLong1());

            Double lat2 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLat2());
            Double long2 = Double.parseDouble(Regiones.getInstance().getZonasList().buscar(i).getLong2());

            if (LatJugador < lat1 && LatJugador > lat2
                    && LongJugador > long1 && LongJugador < long2) {//jugador dentro de la zona
                

                if (user.CambioZona(nombre)) {//entro a una zona nueva
                   // System.out.println("jugador: " + user.getNombre() + " en zona: " + nombre);
                    User defensor = Jugadores.getInstance().defensor(nombre, color, user.getNombre());
                    if (defensor != null) {
                        //pelea entre user y defensor
                        user.setEnPelea(true);
                        defensor.setEnPelea(true);
                            //_jugador1 = user;
                        //_jugador2 = defensor;
                        Batalla batalla = new Batalla(user, defensor, i);
                        System.out.println("Fight! " + user.getNombre() + " vs " + defensor.getNombre());
                        return batalla;

                    } else {
                        //conquisto zona
                        String escuela = user.getEscuela();
                        Regiones.getInstance().getZonasList().buscar(i).setColor(escuela);
                    }
                }
            }
        }
        return null;
    }

}
