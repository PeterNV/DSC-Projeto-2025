package com.example.demo;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
//import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
@RestController
public class CadastroUser {
    String nome;
    String email;
    MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/");
    
   
    @GetMapping("/CadUser")
    public String CadastroFinal(){
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("IFPE_DSC");
        MongoCollection<Document> collection = database.getCollection("User");
        FindIterable<Document> allDocuments = collection.find();
			//collection.find(doc);
            String pesquisar = "";
			if(allDocuments == null){
				pesquisar = "<table>"+"<tr>"+"<td>"+"NADA FOI CADASTRADO"+"</td>"+"</tr>"+"</table>";
			}else{
				for (Document document : allDocuments) {
                String nome = document.getString("Nome");
                String email = document.getString("Email");
                //pesquisar = "<style> table{border: 3px solid black;} td{border: 3px solid black;}</style>";
				pesquisar += "<tr><td>"+nome+"</td>"+"<td>"+email+"</td></tr>";
            }
			}
        mongoClient.close();
        return "<style> table{border: 3px solid black;} td{border: 3px solid black;}</style><table>"+ pesquisar;      
    }
}
