package kingofthehill.datos;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoDBJDBC {

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            
            DB db = mongoClient.getDB("mydb");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
