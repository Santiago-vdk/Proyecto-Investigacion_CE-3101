/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        } catch (Exception e) {
            System.out.println("Exception");
            return null;
        }

    }

    /**
     * @param headers
     * @return
     */
    @GET
    @Path("/sendResult")
    @Produces({MediaType.APPLICATION_JSON})
    public String sendResult(@Context HttpHeaders headers) {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            String score = headers.getRequestHeaders().getFirst("score");

            if (token != null && score != null) {
                BattleManager.getInstance().scoreBatalla(token, Float.parseFloat(score));
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            System.out.println("Exception");
            return null;
        }

    }

    /**
     *
     * @param headers
     * @return
     */
    @GET
    @Path("/checkBattle")
    @Produces({MediaType.APPLICATION_JSON})
    public String checkBattle(@Context HttpHeaders headers) {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            if (Jugadores.getInstance().buscarJugador(token).isEnPelea()) {
                //Jugador debe entrar en batalla
                return "battle";
            } else {
                return "success";
            }
        } catch (Exception e) {
            System.out.println("Exception");
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
                    //Retorno las posiciones de todos los usuarios
                    return Jugadores.getInstance().posicionJugadores().toString();
                } else {
                    //Retorno la posicion solo del usuario
                    return Jugadores.getInstance().posicionJugador(token).toString();
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Desconexion inseperada o token invalido.");
            return null;
        }
    }

    private String[] sendPosParser(String pData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String username = (String) jsonObject.get("username");
        String latitud = (String) jsonObject.get("lat");
        String longitud = (String) jsonObject.get("long");

        String[] parsed = new String[3];
        parsed[0] = username;
        parsed[1] = latitud;
        parsed[2] = longitud;

        return parsed;
    }
}
