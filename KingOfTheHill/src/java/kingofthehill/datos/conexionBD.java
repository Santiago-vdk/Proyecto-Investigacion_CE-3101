/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.datos;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.where;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import static java.util.Arrays.asList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Shagy
 */
public class conexionBD {

    MongoClient _mongoClient;
    static MongoDatabase _db;
    private static conexionBD singleton = new conexionBD();

    /**
     *
     */
    public conexionBD() {
        // To connect to mongodb server
        _mongoClient = new MongoClient("192.168.1.135", 27017);
        // Now connect to your databases
        _db = _mongoClient.getDatabase("KingOfTheHill");

    }

    /* Static 'instance' method */
    /**
     *
     * @return
     */
    public static conexionBD getInstance() {
        return singleton;
    }

    /**
     * Query a la DB las credenciales
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
     *
     * @param pUsername
     * @param pPassword
     * @return 
     */
    public static boolean insercionPassword(String pUsername, String pPassword) {
        MongoCollection<Document> collection = _db.getCollection("users");
        UpdateResult query = collection.updateOne(new Document("username", pUsername), new Document("$set", new Document("password", pPassword)));
        return query.wasAcknowledged();
    }

    /**
     * Insert a la DB las credenciales
     *
     * @param pUsername
     * @param pPassword
     * @param pQuestion
     * @param pAnswer
     */
    public static void consultaRegistrer(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        MongoCollection<Document> collection = _db.getCollection("users");
        collection.insertOne(documentRegister(pUsername, pPassword, pQuestion, pAnswer));
    }

    /**
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

    //ObjectId secrets = query.getObjectId("secret");
    /**
     *
     */
    protected static void consultaScore() {

    }

    private static Document documentRegister(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        Document document = new Document();
        document.append("id", 0);
        document.append("username", pUsername);
        document.append("password", pPassword);
        document.append("school", "null");
        document.append("score", 0);
        document.append("admin", false);
        document.append("username", pUsername);

        /*
         Document documentDetail = new Document();
         documentDetail.append("question", pQuestion);
         documentDetail.append("answer", pAnswer);*/
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

}
