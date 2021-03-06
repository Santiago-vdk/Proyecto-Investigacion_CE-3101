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
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import kingofthehill.logica.Jugadores;
import kingofthehill.logica.Regiones;
import kingofthehill.logica.User;
import org.json.JSONString;

import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Shagy
 */
@Path("zones")
public class ZonesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ZonesResource
     */
    public ZonesResource() {
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/test")
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        System.out.println("EUREKA!");
        return "test";
    }

    /**
     * @param headers
     * @return
     * @throws ParseException
     */
    @GET
    @Path("/retrieve-zones")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String logout(@Context HttpHeaders headers) throws ParseException {
        try {
            String token = headers.getRequestHeaders().getFirst("userToken");
            User user = Jugadores.getInstance().buscarJugador(token);
            if (token != null && user != null && !user.isAdmin()) {
                return Regiones.getInstance().getZonas().toJSONString();
            } else if (token != null && user != null && user.isAdmin()) {
                return Regiones.getInstance().getZonasAdmin().toJSONString();
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            System.out.println("Error al solicitar zonas");
            return null;
        }

    }
}
