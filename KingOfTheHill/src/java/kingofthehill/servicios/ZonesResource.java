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
        
        String token = headers.getRequestHeaders().getFirst("userToken");
        if (token != null && Jugadores.getInstance().buscarJugador(token) != null && !Jugadores.getInstance().buscarJugador(token).isAdmin()){
            return Regiones.getInstance().getZonas().toJSONString();
        } else if (token != null && Jugadores.getInstance().buscarJugador(token) != null && Jugadores.getInstance().buscarJugador(token).isAdmin()){
                return Regiones.getInstance().getZonasAdmin().toJSONString();
            
        }else{
            return null;
        }

    }
}
