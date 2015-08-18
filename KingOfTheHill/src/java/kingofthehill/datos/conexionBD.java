package kingofthehill.datos;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import static java.util.Arrays.asList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shagy
 */
public class conexionBD implements ServletContextListener{

    MongoClient _mongoClient;
    static MongoDatabase _db;
    private static conexionBD _singleton = new conexionBD();
    

    /**
     * Singleton Constructor
     */
    private conexionBD() {
    }

    /* Static 'instance' method */
    /**
     *
     * @return
     */
    public static conexionBD getInstance() {
        return _singleton;
    }

    /**
     * Se realiza la comprobacion de credenciales.
     * 
     * @param pUsername
     * @param pPassword
     * @param pSchool
     * @return
     */
    public static boolean consultaLogin(String pUsername, String pPassword, String pSchool) {
        MongoCollection<Document> collection = _db.getCollection("users");
        Document query = collection.find(and(eq("username", pUsername), eq("password", pPassword))).first();
        return query != null;
    }

    /**
     * Utilizada para cambios de contraseña
     * 
     * @param pUsername
     * @param pPassword
     * @param pAnswer
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    public static boolean insercionPassword(String pUsername, String pPassword, String pAnswer) throws ParseException {
        MongoCollection<Document> collection = _db.getCollection("users");
         FindIterable<Document> doc = collection.find(eq("username", pUsername));
        if (!doc.iterator().hasNext()) {
            return false; //Si el usuario no existe
        } else {
            //El usuario si existe
            JSONParser parser = new JSONParser();
            //Extraigo secret
            Object obj = parser.parse(doc.first().toJson());
            JSONObject json = (JSONObject) obj;
            JSONArray array = (JSONArray) json.get("secret");
            //La segunda posicion posee la respuesta, la comparo, si es correcta procedo a cambiar la contrase;a
            if(array.get(1).toString().compareTo(pAnswer) == 0){
                UpdateResult query = collection.updateOne(new Document("username", pUsername), new Document("$set", new Document("password", pPassword)));
                return query.wasAcknowledged();
            } else {
                return false; //Respuesta incorreta
            }
        }
    }

    /**
     * Se utiliza para hacer un registro nuevo.
     *
     * @param pUsername
     * @param pPassword
     * @param pQuestion
     * @param pAnswer
     * @return
     */
    public static boolean consultaRegistrer(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        MongoCollection<Document> collection = _db.getCollection("users");
        Document query = collection.find(eq("username", pUsername)).first();
        if (query == null) {
            collection.insertOne(documentRegister(pUsername, pPassword, pQuestion, pAnswer));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna el 'secret' de un usuario.
     * 
     * @param pUsername
     * @return
     * @throws ParseException
     */
    public static String[] consultaForgot(String pUsername) throws ParseException {
        MongoCollection<Document> collection = _db.getCollection("users");
        FindIterable<Document> doc = collection.find(eq("username", pUsername));
        if (!doc.iterator().hasNext()) {
            return null;
        } else {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(doc.first().toJson());
            JSONObject json = (JSONObject) obj;
            JSONArray array = (JSONArray) json.get("secret");
            String[] res = new String[2];
            res[0] = array.get(0).toString();
            res[1] = array.get(1).toString();
            return res;
        }
    }


    /**
     * Retorna el 'score' de un usuario.
     * 
     * @param pUsername
     * @return
     * @throws org.json.simple.parser.ParseException
     */
    public int consultaScore(String pUsername) throws ParseException {
        MongoCollection<Document> collection = _db.getCollection("users");
        FindIterable<Document> doc = collection.find(eq("username", pUsername));
        if (!doc.iterator().hasNext()) {
            return -1;
        } else {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(doc.first().toJson());
            JSONObject json = (JSONObject) obj;
            
           return Integer.decode(json.get("score").toString());
        }
    }
    
    /**
     * Retorna el 'admin' de un usuario.
     *  
     * @param pUsername
     * @return
     * @throws ParseException
     */
    public boolean consultaAdmin(String pUsername) throws ParseException {
        MongoCollection<Document> collection = _db.getCollection("users");
        FindIterable<Document> doc = collection.find(eq("username", pUsername));
        if (!doc.iterator().hasNext()) {
            return false;
        } else {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(doc.first().toJson());
            JSONObject json = (JSONObject) obj;
            
           return (boolean) json.get("admin");
        }
    }
       
    /**
     * Se utiliza para cargar las zonas.
     * 
     * @param pZona
     * @param pLat1
     * @param pLong1
     * @param pLat2
     * @param pLong2
     * @param pColor
     * @return
     */
    public static boolean insertarZona(String pZona, String pLat1, String pLong1, String pLat2, String pLong2, String pColor){
        MongoCollection<Document> collection = _db.getCollection("zones");
        FindIterable<Document> doc = collection.find(eq("zone", pZona));
        if (!doc.iterator().hasNext()) {
            collection.insertOne(documentZone(pZona, pLat1, pLong1, pLat2, pLong2, pColor));
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * Genera documento a insertar.
     * 
     * @param pZona
     * @param pLat1
     * @param pLong1
     * @param pLat2
     * @param pLong2
     * @param pColor
     * @return
     */
    private static Document documentZone(String pZona, String pLat1, String pLong1, String pLat2, String pLong2, String pColor){
        Document document = new Document();
        document.append("zone", pZona);
        document.append("lat1", pLat1);
        document.append("long1", pLong1);
        document.append("lat2", pLat2);
        document.append("long2", pLong2);
        document.append("color", pColor);

        return document;
    }
    
    
    private static Document documentRegister(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        Document document = new Document();
        document.append("id", 0);
        document.append("username", pUsername);
        document.append("password", pPassword);
        document.append("score", 0);
        document.append("admin", false);
        document.append("username", pUsername);
        document.append("secret", asList(pQuestion, pAnswer));
        return document;
    }

    private static Document documentLogin(String pUsername, String pPassword) {
        Document document = new Document();
        document.put("username", pUsername);
        document.put("password", pPassword);
        return document;
    }

    private static Document documentPassword(String pUsername, String pPassword) {
        Document document = new Document();
        document.put("username", pUsername);
        document.put("password", pPassword);
        return document;
    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
        System.out.println("Conectando con la BD...");
        _mongoClient = new MongoClient("localhost", 27017);
        _db = _mongoClient.getDatabase("KingOfTheHill");
        } catch (NullPointerException e){
            System.out.println("Error...");
        }

    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try{
                    System.out.println("Cerrando conexion con la BD...");
        _mongoClient.close();
        } catch (MongoException e){
             System.out.println("Error...");
        }

    }
    
}
