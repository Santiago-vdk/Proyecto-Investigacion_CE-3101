package kingofthehill.servicios;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import kingofthehill.logica.BattleManager;
import kingofthehill.logica.Jugadores;
import kingofthehill.logica.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Shagy
 */
@Path("mobile")
public class MobileResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MobileResource
     */
    public MobileResource() {
    }

    /**
     * {
     * "username":username, "lat":latitud, "long":longitud } Recibe la posicion
     * del usuario
     *
     * @param msg
     * @param headers
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    @POST
    @Path("/send-position")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public String sendPosition(String msg, @Context HttpHeaders headers) throws ParseException {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            User user = Jugadores.getInstance().buscarJugador(token);
            if (user != null) {
                String[] parsedData = sendPosParser(msg);
                Double LatJugador = Double.parseDouble(parsedData[1]);
                Double LongJugador = Double.parseDouble(parsedData[2]);

                user.setLat(LatJugador);
                user.setLong(LongJugador);

                BattleManager.getInstance().nuevaBatalla(user, LatJugador, LongJugador);
                return "success";
            } else {
                return null;
            }
        } catch (ParseException | NumberFormatException e) {
            
            System.out.println("Exception send position");
            return null;
        }
    }

    /**
     * @param msg
     * @param headers
     * @return
     */
    @POST
    @Path("/sendresult")
    @Produces({MediaType.APPLICATION_JSON})
    public String sendResult(String msg, @Context HttpHeaders headers) {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
         
            if (token != null) {
                String[] parsedData = sendResultParser(msg);
                float res = Float.parseFloat(parsedData[0]);
                BattleManager.getInstance().scoreBatalla(token, res);
                return "success";
            } else {
                return "fail";
            }
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Exception send result");
            return null;
        }

    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("/check")
    @Produces({MediaType.APPLICATION_JSON})
    public String check() {
        return "rekt";
    }
    
    

    /**
     *
     * @param headers
     * @return
     */
    @GET
    @Path("/checkbattle")
    @Produces({MediaType.APPLICATION_JSON})
    public String checkBattle(@Context HttpHeaders headers) {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            User user = Jugadores.getInstance().buscarJugador(token);
            if (user.isEnPelea() && !user.isBot()) {
                //Jugador debe entrar en batalla
                JSONObject batt = new JSONObject();
                batt.put("battle", "true");
                return batt.toString(); //Hmmm
            } else if (user.isEnPelea() && user.isBot()) {
                return "battle";
            } else {
                if (!user.isBot()) {
                    JSONObject batt = new JSONObject();
                    batt.put("battle", "false");
                    return batt.toString();
                } else {
                    return "success";
                }
            }
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println("Exception check battle");
            return null;
        }

    }

    /**
     *
     * @param headers
     * @return
     */
    @GET
    @Path("/score")
    @Produces({MediaType.APPLICATION_JSON})
    public String score(@Context HttpHeaders headers) {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            User user = Jugadores.getInstance().buscarJugador(token);
            if (user == null) {
                return null;
            } else {
                int puntaje = user.getPuntaje();
                return "{score:" + puntaje + "}";
            }
        } catch (NullPointerException e) {
            System.out.println("Error al consultar puntaje.");
            return null;
        }
    }
    
    /**
     * @param headers
     * @return
     */
    @GET
    @Path("/retrieve-position")
    @Produces({MediaType.APPLICATION_JSON})
    public String retrievePosition(@Context HttpHeaders headers) {
        //Si el token es correcto signifca que el usuario esta autenticado y procedo a ver si es
        //admin o user si es user le devuelvo su posicion del marker
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            User user = Jugadores.getInstance().buscarJugador(token);
            if (user == null) {
                return null;
            } else {
                if (user.isAdmin()) {
                    return Jugadores.getInstance().posicionJugadores().toString();
                } else {
                    //Retorno la posicion solo del usuario
                    return Jugadores.getInstance().posicionJugador(token).toString();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Desconexion inseperada o token invalido.");
            return null;
        }
    }

    private String[] sendPosParser(String pData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String username = (String) jsonObject.get("username");
        String latitud = Double.toString((double) jsonObject.get("lat"));
        String longitud = Double.toString((double) jsonObject.get("long"));

        String[] parsed = new String[3];
        parsed[0] = username;
        parsed[1] = latitud;
        parsed[2] = longitud;

        return parsed;
    }

    private String[] sendResultParser(String pData) throws ParseException {      
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String puntaje =  Double.toString((double) jsonObject.get("points"));
        String[] parsed = new String[1];
        parsed[0] = puntaje;

        return parsed;
    }
}
