/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.servicios;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
     * Retrieves representation of an instance of
     * kingofthehill.servicios.MobileResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * {
     * "username":username, "lat":latitud, "long":longitud } 
     * Recibe la posicion
     * del usuario
     *
     * @param msg
     * @return
     */
    @POST
    @Path("/send-position")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public String sendPosition(String msg, @Context HttpHeaders headers) throws ParseException {
        String token = headers.getRequestHeaders().getFirst("userToken");
        User user = Jugadores.getInstance().buscarJugador(token);

        if (user != null) {
            String[] parsedData = sendPosParser(msg);
            user.setLat(Float.parseFloat(parsedData[1]));
            user.setLong(Float.parseFloat(parsedData[2]));
            return "success";
        } else {
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
    public String retrievePosition( @Context HttpHeaders headers) {

        //Si el token es correcto signifca que el usuario esta autenticado y procedo a ver si es
        //admin o user si es user le devuelvo su posicion del marker
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
