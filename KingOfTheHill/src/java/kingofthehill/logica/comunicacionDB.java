package kingofthehill.logica;

import com.firebase.security.token.TokenGenerator;
import java.util.HashMap;
import java.util.Map;
import kingofthehill.datos.conexionBD;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shagy
 */
public class comunicacionDB {

    /**
     *
     */
    public comunicacionDB() {
    }
    
    /**
     *
     * @param pUsername
     * @param pPassword
     * @param pAnswer
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    public boolean setPassword(String pUsername, String pPassword, String pAnswer) throws ParseException{
        return conexionBD.insercionPassword(pUsername,pPassword,pAnswer);   
    }

    /**
     *
     * @param pUsername
     * @param pPassword
     * @param pSchool
     * @return 
     * @throws org.json.simple.parser.ParseException 
     */
    public String login(String pUsername, String pPassword, String pSchool) throws ParseException {
        if (conexionBD.consultaLogin(pUsername, pPassword, pSchool)) {
            
            //Usuario autenticado correctamente genero token
            Map<String, Object> authPayload = new HashMap<String, Object>();
            authPayload.put("uid", pUsername);
            authPayload.put("password", pPassword);
            
            TokenGenerator tokenGenerator = new TokenGenerator("D5kWnfUVl9BiR2NzzOMEvze6ulKpqfDeoJ2LVPGX");
            String token = tokenGenerator.createToken(authPayload);
            
            //Creo usuario en logica
            String color = parserSchool(pSchool);
            Jugadores.getInstance().conectarJugador(pUsername, token, color, 0, 0, getScore(pUsername),getAdmin(pUsername));
            
            return token;
        }
        else{
            return null;
        }
        
    }

    /**
     *
     * @param pUsername
     * @param pPassword
     * @param pQuestion
     * @param pAnswer
     * @return 
     */
    public boolean register(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        System.out.println("Registrando Nuevo Usuario!");
        return conexionBD.consultaRegistrer(pUsername, pPassword, pQuestion, pAnswer);
    }
    
    /**
     *
     * @param pZona
     * @param pLat1
     * @param pLong1
     * @param pLat2
     * @param pLong2
     * @param pColor
     * @return
     */
    public boolean cargarZona(String pZona, String pLat1, String pLong1, String pLat2, String pLong2, String pColor) {
        return conexionBD.insertarZona(pZona, pLat1, pLong1, pLat2, pLong2, pColor);
    }


    /**
     *
     * @param pUsername
     * @return
     * @throws ParseException
     */
    public String[] forgotpassword(String pUsername) throws ParseException {
        String[] consulta;
        consulta = conexionBD.consultaForgot(pUsername);
        if(consulta == null){
            return null;
        }
        else{
            return consulta;
        }
        
    }

    /**
     *
     * @param pUsername
     * @return 
     * @throws ParseException
     */
    public int getScore(String pUsername) throws ParseException {
        return conexionBD.getInstance().consultaScore(pUsername);
    }
    
    /**
     *
     * @param pUsername
     * @return
     * @throws ParseException
     */
    public boolean getAdmin(String pUsername) throws ParseException {
        return conexionBD.getInstance().consultaAdmin(pUsername);
    }
    
    /**
     * Se parsea el nombre de la escuela a un color hexadecimal.
     * @param pSchool
     * @return
     */
    private String parserSchool(String pSchool) {
        if(pSchool.compareTo("Computacion")==0){
            return "#FF0000";
        }else if(pSchool.compareTo("Matematica")==0){
            return "#0000FF";
        }else if(pSchool.compareTo("Fisica")==0){
            return "#33CC33";
        }else if(pSchool.compareTo("Forestal")==0){
            return "#FF9966";
        }else if(pSchool.compareTo("Electromecanica")==0){
            return "#FF66CC";
        }else if(pSchool.compareTo("Produccion Indus")==0){
            return "#FFFF66";
        }else if(pSchool.compareTo("Disenio Indus")==0){
            return "#00FF00";
        }
        return null;
    }

}
