package kingofthehill.logica;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shagy
 */
public class Regiones {

    private static Regiones _singleton = new Regiones();
    private ListaZonas _zonasList;
    comunicacionDB _BD;

    private Regiones() {
        _zonasList = new ListaZonas();
        _BD = new comunicacionDB();
        leerZonas();

    }

    /**
     *
     * @return
     */
    public static Regiones getInstance() {
        return _singleton;
    }

    private void leerZonas() {
        System.out.println("Cargando Zonas...");
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    "C:\\Users\\Shagy\\Documents\\NetBeansProjects\\Proyecto-Investigacion_CE-3101\\KingOfTheHill\\config\\zones.txt"));

            JSONArray jsonArray = (JSONArray) obj;
            for (Object jsonArray1 : jsonArray) {
                JSONObject json = (JSONObject) jsonArray1;
                String zone = json.get("zone").toString();
                String lat1 = json.get("lat1").toString();
                String long1 = json.get("long1").toString();
                String lat2 = json.get("lat2").toString();
                String long2 = json.get("long2").toString();
                String color = json.get("color").toString();

                //Error
                if (_BD.cargarZona(zone, lat1, long1, lat2, long2, color)) {
                    getZonasList().insertar(zone, lat1, long1, lat2, long2, color);
                    //System.out.println("Server: Nueva zona añadida");
                } else {
                    getZonasList().insertar(zone, lat1, long1, lat2, long2, color);
                    //System.out.println("Server: Omito zona");
                }
            }
            System.out.println("Numero de zonas cargadas: " + _zonasList.getTam());
        } catch (IOException | ParseException | NullPointerException e) {
            System.out.println("fuck");
        }
    }

    /**
     *
     * @return
     */
    public JSONObject getZonas() {
        Zona zone;
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();

        for (int i = 0; i < getZonasList().getTam(); i++) {
            JSONObject object = new JSONObject();
            zone = getZonasList().buscar(i);
            object.put("name", zone.getNombre());
            object.put("lat1", Double.parseDouble(zone.getLat1()));
            object.put("long1", Double.parseDouble(zone.getLong1()));
            object.put("lat2", Double.parseDouble(zone.getLat2()));
            object.put("long2", Double.parseDouble(zone.getLong2()));
            object.put("color", zone.getColor());
            array.add(object);
        }
        obj.put("zonas", array);
        return obj;

    }

    /**
     *
     * @return
     */
    public JSONArray getZonasAdmin() {
        Zona zone;
        JSONArray array = new JSONArray();

        for (int i = 0; i < getZonasList().getTam(); i++) {
            JSONObject object = new JSONObject();
            zone = getZonasList().buscar(i);
            object.put("name", zone.getNombre());
            object.put("lat1", zone.getLat1());
            object.put("long1", zone.getLong1());
            object.put("lat2", zone.getLat2());
            object.put("long2", zone.getLong2());
            object.put("color", zone.getColor());
            array.add(object);
        }
        return array;
    }

    /**
     * @return the _zonasList
     */
    public ListaZonas getZonasList() {
        return _zonasList;
    }

    /**
     * @param pZonasList
     */
    public void setZonasList(ListaZonas pZonasList) {
        _zonasList = pZonasList;
    }
    /*
     @Override
     public void contextInitialized(ServletContextEvent sce) {
     System.out.println("1");
     leerZonas();
     }

    
     @Override
     public void contextDestroyed(ServletContextEvent sce) {
     }
     */

}
