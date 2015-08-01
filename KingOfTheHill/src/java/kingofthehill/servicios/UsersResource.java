package kingofthehill.servicios;

import java.net.UnknownHostException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import kingofthehill.logica.comunicacionDB;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * REST Web Service
 *
 * @author Shagy
 */
@Path("users")
public class UsersResource {

    @Context
    private UriInfo context;

    comunicacionDB _db;

    /**
     * Creates a new instance of UsersResource
     *
     * @throws java.net.UnknownHostException
     */
    public UsersResource() throws UnknownHostException {
        _db = new comunicacionDB();
    }

    /**
     *
     * @param msg
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    @POST
    @Path("/register")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response register(String msg) throws ParseException {

        //Obtengo una lista con los valores enviados por el usuario
        String[] parsedData = registerParser(msg);

        //Pasar a BD, validar y retornar success
        _db.register(parsedData[0], parsedData[1], parsedData[2], parsedData[3]);

        //Pasar a BD, validar y retornar success
        return Response.status(200).entity(msg).build();

    }

    /**
     *
     * @param msg
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String login(String msg) throws ParseException {

        //Obtengo una lista con los valores enviados por el usuario
        String[] parsedData = loginParser(msg);

        //Pasar a BD, validar y retornar success
        String userToken = _db.login(parsedData[0], parsedData[1], parsedData[2]);
        if (userToken == null) {
            return null;
        } else {
            JSONObject json = new JSONObject();
            json.put("access_token", userToken);
            json.put("expires_in", 3600);
            return json.toString();

        }

    }


    /**
     *
     * @param msg
     * @return
     */
    @POST
    @Path("/forgotpassword")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response forgotPassword(String msg) throws ParseException {
        //Obtengo una lista con los valores enviados por el usuario
        String[] parsedData = forgotParser(msg);

        //Pasar a BD, validar y retornar success
        _db.forgotpassword(parsedData[0]);

        return Response.status(200).entity(msg).build();

    }

    private String[] loginParser(String pData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        String school = (String) jsonObject.get("school");

        String[] parsed = new String[3];
        parsed[0] = username;
        parsed[1] = password;
        parsed[2] = school;

        return parsed;

    }

    private String[] registerParser(String pData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        String question = (String) jsonObject.get("question");
        String answer = (String) jsonObject.get("answer");

        String[] parsed = new String[4];
        parsed[0] = username;
        parsed[1] = password;
        parsed[2] = question;
        parsed[3] = answer;

        return parsed;

    }

    private String[] forgotParser(String pData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String username = (String) jsonObject.get("username");

        String[] parsed = new String[1];
        parsed[0] = username;

        return parsed;

    }

}
