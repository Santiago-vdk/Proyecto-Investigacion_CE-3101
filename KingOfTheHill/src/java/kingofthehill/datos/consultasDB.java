/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingofthehill.datos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import org.bson.Document;
import static org.glassfish.hk2.utilities.reflection.Pretty.collection;

/**
 *
 * @author Shagy
 */
public class consultasDB {

    MongoClient _mongoClient;
    MongoDatabase _db;
    boolean firstRun = true;

    /**
     *
     * @throws UnknownHostException
     */
    public consultasDB() throws UnknownHostException {
        // To connect to mongodb server
        _mongoClient = new MongoClient();
        // Now connect to your databases
        _db = _mongoClient.getDatabase("KingOfTheHill");

    }

    /**
     * Query a la DB las credenciales
     *
     * @param pUsername
     * @param pPassword
     * @param pSchool
     */
    public boolean consultaLogin(String pUsername, String pPassword, String pSchool) {
        MongoCollection<Document> collection = _db.getCollection("users");
        Document query = collection.find(and(eq("username", pUsername),eq("password",pPassword))).first();
        if(query == null){
            return false;
        }
        else{
            return true;
        }

    }

    /**
     * Insert a la DB las credenciales
     *
     * @param pUsername
     * @param pPassword
     * @param pQuestion
     * @param pAnswer
     */
    public void consultaRegistrer(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        MongoCollection<Document> collection = _db.getCollection("users");
        collection.insertOne(documentRegister(pUsername, pPassword, pQuestion, pAnswer));

    }

    public void consultaForgot(String pUsername) {

    }

    /**
     *
     */
    public void consultaScore() {

    }

    private Document documentRegister(String pUsername, String pPassword, String pQuestion, String pAnswer) {
        Document document = new Document();
        document.put("id", 0);
        document.put("username", pUsername);
        document.put("password", pPassword);
        document.put("school", "null");
        document.put("score", 0);
        document.put("admin", false);
        document.put("username", pUsername);

        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("question", pQuestion);
        documentDetail.put("answer", pAnswer);

        document.put("secret", documentDetail);

        return document;
    }

    private Document documentLogin(String pUsername, String pPassword) {
        Document document = new Document();
        document.put("username", pUsername);
        document.put("password", pPassword);

        return document;
    }

}
