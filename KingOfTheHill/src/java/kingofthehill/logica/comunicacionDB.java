/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.logica;

import com.firebase.security.token.TokenGenerator;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import kingofthehill.datos.consultasDB;

/**
 *
 * @author Shagy
 */
public class comunicacionDB {

    consultasDB _DB;

    /**
     *
     * @throws UnknownHostException
     */
    public comunicacionDB() throws UnknownHostException {
        _DB = new consultasDB();
    }

    /**
     *
     * @param pUsername
     * @param pPassword
     * @param pSchool
     * @return 
     */
    public String login(String pUsername, String pPassword, String pSchool) {
        if (_DB.consultaLogin(pUsername, pPassword, pSchool)) {
            //Usuario autenticado correctamente genero token

            Map<String, Object> authPayload = new HashMap<String, Object>();
            authPayload.put("uid", pUsername);
            authPayload.put("password", pPassword);

          
            
            TokenGenerator tokenGenerator = new TokenGenerator("D5kWnfUVl9BiR2NzzOMEvze6ulKpqfDeoJ2LVPGX");
            String token = tokenGenerator.createToken(authPayload);
            System.out.println("HERE");
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
        _DB.consultaRegistrer(pUsername, pPassword, pQuestion, pAnswer);
    }

    public void forgotpassword(String pUsername) {
        _DB.consultaForgot(pUsername);
    }

    /**
     *
     */
    public void score() {

    }

}
