/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.servicios;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author Shagy
 */
@Path("markers")
public class MarkersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MarkersResource
     */
    public MarkersResource() {
    }

    /**
     * Retrieves representation of an instance of
     * kingofthehill.servicios.MarkersResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Markers GET";
    }

    /**
     * PUT method for updating or creating an instance of MarkersResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     *
     * @param headers
     * @return
     */
    @GET
    @Path("/vehicles")
    @Produces("application/json")
    public String getUser(@Context HttpHeaders headers) {

        String s = headers.getRequestHeaders().getFirst("userToken");

        //Comparar token
        if (s.compareTo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2IjowLCJkIjp7InVpZCI6IlNhbnRpYWdvIiwicGFzc3dvcmQiOiJlMTBhZGMzOTQ5YmE1OWFiYmU1NmUwNTdmMjBmODgzZSJ9LCJpYXQiOjE0Mzg0ODY5Mzl9.X2aJSetOiy0AyoXOdyHAYgsdgozZ3-P4oCEpdIxPn7Q") == 0) {
            JSONArray outerArray = new JSONArray();
            JSONObject innerObject = new JSONObject();
            JSONObject innerArray2 = new JSONObject();

            innerArray2.put("lat", "50");
            innerArray2.put("long", "40");

            innerObject.put("id", "1");
            innerObject.put("name", "Santiago");
            innerObject.put("pos", innerArray2);

            outerArray.add(innerObject);

            return outerArray.toString();
        } else {
            return "{}";
        }

    }
}
