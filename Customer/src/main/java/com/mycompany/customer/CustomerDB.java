/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customer;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import java.util.*;
/**
 *
 * @author ASUS
 */

public class CustomerDB {

    private final MongoCollection<Document> collection;

    public CustomerDB() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("customer");
        collection = db.getCollection("customers");
    }

    public boolean insertCustomer(Customer customer) {
        long count = collection.countDocuments();
        String id = String.valueOf(count + 1);
        customer.setId(id);

        Document doc = new Document("id", customer.getId())
                .append("nama", customer.getNama())
                .append("alamat", customer.getAlamat())
                .append("telepon", customer.getTelepon());

        collection.insertOne(doc);
        return true;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            Customer customer = new Customer(
                    doc.getString("id"),
                    doc.getString("nama"),
                    doc.getString("alamat"),
                    doc.getString("telepon")
            );
            list.add(customer);
        }
        return list;
    }

    public boolean updateCustomer(Customer customer) {
        Document update = new Document("$set", new Document("nama", customer.getNama())
                .append("alamat", customer.getAlamat())
                .append("telepon", customer.getTelepon()));
        UpdateResult result = collection.updateOne(Filters.eq("id", customer.getId()), update);
        return result.getModifiedCount() > 0;
    }

    public boolean deleteCustomer(String id) {
        DeleteResult result = collection.deleteOne(Filters.eq("id", id));
        return result.getDeletedCount() > 0;
    }
}


