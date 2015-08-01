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

/**
 * REST Web Service
 *
 * @author Shagy
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     *
     * @param msg
     * @return
     */
    @POST
    @Path("/send-position")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sendPosition(String msg) {

        //Pasar a BD, validar y retornar success
        return Response.status(200).entity(msg).build();

    }

    /**
     *
     * @param msg
     * @param headers
     * @return
     */
    @GET
    @Path("/retrieve-position")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response retrievePosition(String msg, @Context HttpHeaders headers) {
        
        
        //Si el token es correcto signifca que el usuario esta autenticado y procedo a ver si es
        //admin o user si es user le devuelvo su posicion del marker
        String s = headers.getRequestHeaders().getFirst("userToken");
        System.out.println(s);
        
        return Response.status(200).entity(msg).build();

    }

}
