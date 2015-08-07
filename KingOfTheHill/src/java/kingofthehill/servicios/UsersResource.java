package kingofthehill.servicios;

import java.net.UnknownHostException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import kingofthehill.logica.Jugadores;
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
    @Produces({MediaType.TEXT_HTML})
    public String register(String msg) throws ParseException {

        //Obtengo una lista con los valores enviados por el usuario
        String[] parsedData = registerParser(msg);

        System.out.println("En serivicio de register con POST");
        
        //Pasar a BD, validar y retornar success
        if(_db.register(parsedData[0], parsedData[1], parsedData[2], parsedData[3])){
            System.out.println("REGISTRADO");
            return "success";
        }
        else{
            return null;
        }

        //Pasar a BD, validar y retornar success
        //return Response.status(200).entity(msg).build();

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
        if (userToken == null) { //Login invalido
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
     * @param headers
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    @GET
    @Path("/logout")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response logout(@Context HttpHeaders headers) throws ParseException {

        String token = headers.getRequestHeaders().getFirst("userToken");
        if (Jugadores.getInstance().desconectarJugador(token)) {
            return Response.accepted().type(MediaType.TEXT_HTML).build();
        } //Check if token matches with a user
        else {
            return Response.noContent().type(MediaType.TEXT_HTML).build();
        }

    }

    /**
     *
     * @param headers
     * @return
     */
    @GET
    @Path("/auth")
    @Produces({MediaType.TEXT_HTML})
    public Response isLogged(@Context HttpHeaders headers) {
        String token = headers.getRequestHeaders().getFirst("userToken");
        if (token == null) {
            return Response.noContent().type(MediaType.TEXT_HTML).build();
        } //Check if token matches with a user
        else {
            String username = Jugadores.getInstance().buscarJugador(token).getNombre();
            String score = Integer.toString(Jugadores.getInstance().buscarJugador(token).getPuntaje());
            return Response.accepted().header("username", username).header("score", score).type(MediaType.TEXT_HTML).build();
        }

    }

    /**
     *
     * @param msg
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    @POST
    @Path("/forgotpassword")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public String forgotPassword(String msg) throws ParseException {
        //Obtengo una lista con los valores enviados por el usuario
        String[] parsedData = forgotParser(msg);
        //Voy por pregunta a la BD
        if (parsedData[1].compareTo("true") == 0) {
            //Pasar a BD, validar y retornar success
            String[] info = new String[2];
            info = _db.forgotpassword(parsedData[0]);
            
            if(info == null){
                return null;
            }
            else{
                return info[0];
            }
        }
        //Voy por respuesta
        else {
            String[] info = new String[2];
            info = _db.forgotpassword(parsedData[0]);
            if(info[1].compareTo(parsedData[2]) == 0){
                return "success";
            }
            else{
                return null;
            }
            
        }

        //return Response.status(200).header("question", info[0]).build();
    }

    /**
     *
     * @param msg
     * @return
     * @throws ParseException
     */
    @POST
    @Path("/setpassword")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public String setPassword(String msg) throws ParseException {
        //Obtengo una lista con los valores enviados por el usuario
        String[] parsedData = passwordParser(msg);
        //Voy por pregunta a la BD
        if(_db.setPassword(parsedData[0], parsedData[1], parsedData[2])){
            return "success";
        } else {
            return null;
        }
        

     
    }
    
    private String[] passwordParser(String pData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(pData);
        JSONObject jsonObject = (JSONObject) obj;

        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        String answer = (String) jsonObject.get("answer");

        String[] parsed = new String[3];
        parsed[0] = username;
        parsed[1] = password;
        parsed[2] = answer;
        return parsed;

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
        String question = (String) jsonObject.get("question");
        String answer = (String) jsonObject.get("answer");

        String[] parsed = new String[3];
        parsed[0] = username;
        parsed[1] = question;
        parsed[2] = answer;

        return parsed;

    }

}
