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
     * @param pSchool
     * @return 
     */
    public String login(String pUsername, String pPassword, String pSchool) {
        if (conexionBD.consultaLogin(pUsername, pPassword, pSchool)) {
            
            //Usuario autenticado correctamente genero token
            Map<String, Object> authPayload = new HashMap<String, Object>();
            authPayload.put("uid", pUsername);
            authPayload.put("password", pPassword);
            
            TokenGenerator tokenGenerator = new TokenGenerator("D5kWnfUVl9BiR2NzzOMEvze6ulKpqfDeoJ2LVPGX");
            String token = tokenGenerator.createToken(authPayload);
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
     */
    public void register(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        conexionBD.consultaRegistrer(pUsername, pPassword, pQuestion, pAnswer);
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
     */
    public void score() {

    }

}
