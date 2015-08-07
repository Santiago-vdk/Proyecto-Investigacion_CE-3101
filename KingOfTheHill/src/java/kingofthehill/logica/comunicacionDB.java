package kingofthehill.logica;

import com.firebase.security.token.TokenGenerator;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import kingofthehill.datos.conexionBD;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shagy
 */
public class comunicacionDB {

    //conexionBD _DB;

    /**
     *
     * @throws UnknownHostException
     */
    public comunicacionDB() throws UnknownHostException {
        //_DB = conexionBD.getInstance();
       
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
            Jugadores.getInstance().conectarJugador(pUsername, token, pSchool, 0, 0, getScore(pUsername),getAdmin(pUsername));
            
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
        return conexionBD.consultaRegistrer(pUsername, pPassword, pQuestion, pAnswer);
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
    
    public boolean getAdmin(String pUsername) throws ParseException {
        return conexionBD.getInstance().consultaAdmin(pUsername);
        
    }

}
