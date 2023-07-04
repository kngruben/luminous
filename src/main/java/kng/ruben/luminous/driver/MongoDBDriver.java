package kng.ruben.luminous.driver;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDriver {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public MongoDBDriver(String connectionString, String databaseName) {
        ConnectionString connString = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString).uuidRepresentation(UuidRepresentation.STANDARD).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
    }

    public void insertDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
    }

    public Document findDocument(String collectionName, Bson query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find(query).first();
    }

    public List<Document> findDocuments(String collectionName, Bson query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> result = collection.find(query);
        List<Document> documents = new ArrayList<>();
        try (MongoCursor<Document> cursor = result.iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }
        return documents;
    }

    public void updateDocument(String collectionName, Bson query, Bson update) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.updateOne(query, new Document("$set", update));
    }

    public void deleteDocument(String collectionName, Bson query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteOne(query);
    }

    public void deleteDocuments(String collectionName, Bson query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteMany(query);
    }

    public long countDocuments(String collectionName, Bson query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.countDocuments(query);
    }

    public void close() {
        mongoClient.close();
    }
}
