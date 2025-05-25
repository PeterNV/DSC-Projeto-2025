package com.example.demo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
//import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		JFrame painel = new JFrame();
        JButton confirmar = new JButton("CONFIRMAR");
		JTextField nome = new JTextField("nome");
		JTextField email = new JTextField("email");
		email.setBounds(320,25,150,25);
		nome.setBounds(320,50,150,25);
		confirmar.setBounds(320,70,150,25);
        painel.setSize(640, 320);
		MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/");
		MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("IFPE_DSC");
        MongoCollection<Document> collection = database.getCollection("User");
		confirmar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                Document doc = new Document("Nome", nome.getText()).append("Email", email.getText());
    			collection.insertOne(doc);
				nome.setText("");
				email.setText("");
				
            }
		});
        painel.setLayout(null);
        painel.setVisible(true);
        painel.add(confirmar);
		painel.add(email);
		painel.add(nome);
		SpringApplication.run(DemoApplication.class, args);

	}

}
