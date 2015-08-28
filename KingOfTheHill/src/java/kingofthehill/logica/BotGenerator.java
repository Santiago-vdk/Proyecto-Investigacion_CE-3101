package kingofthehill.logica;

import com.firebase.security.token.TokenGenerator;
import com.googlecode.jeneratedata.people.NameGenerator;
import com.googlecode.jeneratedata.text.AlphabeticStringGenerator;
import com.googlecode.jeneratedata.numbers.IntegerGenerator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shagy
 */
public class BotGenerator {
    private static BotGenerator _singleton = new BotGenerator();
    private String[] _escuelas = {"Computacion", "Matematica", "Fisica", "Forestal",
        "Electromecanica", "Produccion Indus", "Disenio Indus"};

    private BotGenerator() {
    }

    /**
     *
     * @return
     */
    public static BotGenerator getInstance() {
        return _singleton;
    }

    /**
     * Genera un bot nuevo.
     */
    public void newBot() {
        NameGenerator name = new NameGenerator();
        AlphabeticStringGenerator password = new AlphabeticStringGenerator(6);
        IntegerGenerator school = new IntegerGenerator(6);
        String botName = name.generate();
        String botPassword = password.generate();

        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", botName);
        authPayload.put("password", botPassword);

        TokenGenerator tokenGenerator = new TokenGenerator("D5kWnfUVl9BiR2NzzOMEvze6ulKpqfDeoJ2LVPGX");
        String token = tokenGenerator.createToken(authPayload);

        int random = school.generate();

        User bot = new User(botName, token, parserSchool(_escuelas[random]), 9.855685, -83.912867, 0, false);
        bot.setIsBot(true);
        System.out.println("Jugador: " + botName + ", conectando... Escuela: " + _escuelas[random]);
        Jugadores.getInstance().conectarJugador(bot);
        Bot botThread = new Bot(token, 9.855685, -83.912867);
    }

    //Mejora futura
    private String parserSchool(String pSchool) {
        if (pSchool.compareTo("Computacion") == 0) {
            return "#FF0000";
        } else if (pSchool.compareTo("Matematica") == 0) {
            return "#0000FF";
        } else if (pSchool.compareTo("Fisica") == 0) {
            return "#33CC33";
        } else if (pSchool.compareTo("Forestal") == 0) {
            return "#FF9966";
        } else if (pSchool.compareTo("Electromecanica") == 0) {
            return "#FF66CC";
        } else if (pSchool.compareTo("Produccion Indus") == 0) {
            return "#FFFF66";
        } else if (pSchool.compareTo("Disenio Indus") == 0) {
            return "#00FF00";
        }
        return null;
    }

}
