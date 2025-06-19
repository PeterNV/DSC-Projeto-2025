package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
//import java.nio.charset.StandardCharsets;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static String selectedOption;
	public static String selectedOption2;

	public static void main(String[] args) {

		JFrame painel = new JFrame("GAMES");
		JButton confirmar = new JButton("CADASTRAR");
		JButton verLista = new JButton("LISTA");
		JButton deletar = new JButton("DELETAR");
		JButton atualizar = new JButton("ATUALIZAR CLASSIFICAÇÃO");
		JButton buscar = new JButton("BUSCAR");
		JTextField titulo = new JTextField("Título");
		JTextField ano = new JTextField("Ano");
		JLabel AllStatus = new JLabel("");
		// JTextField classifca = new JTextField("Classificação");
		JComboBox<String> classifca = new JComboBox<>();
		classifca.addItem("LIVRE");
		classifca.addItem("10");
		classifca.addItem("12");
		classifca.addItem("14");
		classifca.addItem("16");
		classifca.addItem("18");
		JComboBox<String> ListaDeGenerosDeGame = new JComboBox<>();
		ListaDeGenerosDeGame.addItem("Genêro");
		ListaDeGenerosDeGame.addItem("Aventura");
		ListaDeGenerosDeGame.addItem("Ação");
		ListaDeGenerosDeGame.addItem("FPS");
		ListaDeGenerosDeGame.addItem("Survival horror");
		ListaDeGenerosDeGame.addItem("RPG");
		ListaDeGenerosDeGame.addItem("Esporte");
		ListaDeGenerosDeGame.addItem("Violência");
		ListaDeGenerosDeGame.addItem("Ficção");
		ListaDeGenerosDeGame.addItem("Luta");
		ano.setBounds(320, 25, 200, 25);
		titulo.setBounds(320, 50, 200, 25);
		classifca.setBounds(320, 75, 200, 25);
		ListaDeGenerosDeGame.setBounds(320, 100, 200, 25);
		confirmar.setBounds(320, 125, 200, 25);
		confirmar.setForeground(Color.white);
		confirmar.setBackground(Color.green);

		verLista.setBounds(320, 150, 200, 25);
		deletar.setBounds(320, 175, 200, 25);
		atualizar.setBounds(320, 200, 200, 25);
		buscar.setBounds(320, 225, 200, 25);
		AllStatus.setBounds(320, 250, 225, 25);
		deletar.setForeground(Color.white);
		deletar.setBackground(Color.red);
		atualizar.setForeground(Color.white);
		atualizar.setBackground(Color.blue);
		verLista.setForeground(Color.white);
		verLista.setBackground(Color.gray);
		buscar.setForeground(Color.white);
		buscar.setBackground(Color.orange);
		ListaDeGenerosDeGame.addActionListener(e -> {
			selectedOption = (String) ListaDeGenerosDeGame.getSelectedItem();
			System.out.println("Selected: " + selectedOption);
		});
		classifca.addActionListener(e -> {
			selectedOption2 = (String) classifca.getSelectedItem();
			System.out.println("Selected: " + selectedOption2);
		});
		painel.setSize(640, 640);

		confirmar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(selectedOption == null&&selectedOption2 == null){
					AllStatus.setText("Campos não selecionados!");
					AllStatus.setForeground(Color.black);
					
				}else{
					if(ano.equals("Ano")&&titulo.equals("Título")){
						AllStatus.setText("Por favor preencher os campos!");
						AllStatus.setForeground(Color.black);
					}else{
						try {
						// URL que será aberta
						JSONObject gameJson = new JSONObject();
						gameJson.put("titulo", titulo.getText().replace(" ", "%20"));
						gameJson.put("ano", ano.getText());
						gameJson.put("classificacao", selectedOption2);
						gameJson.put("genero", selectedOption.replace(" ", "%20"));
					
						//String json_str = "{\"titulo\":\"Overwatch\",\"ano\":2016,\"genero\":\"Ficção\"\"classificacao\":\"12\"}";

						String json = String.format(
						"{\"titulo\":\"%s\",\"ano\":%s,\"genero\":\"%s\",\"classificacao\":%s}",
						titulo.getText(),            // Overwatch
						ano.getText(),               // 2016
						selectedOption,              // FPS
						selectedOption2              // 12
					);

					HttpClient client = HttpClient.newHttpClient();
					HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:8000/cadastrar"))
						.header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json))
						.build();

					HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());
						
					//System.out.println(resp.body());
					if(resp.body() != null){
						if(resp.body().startsWith("N")){
							AllStatus.setText("Jogo já foi cadastrado.");
							AllStatus.setForeground(Color.red);
						}
						if(resp.body().startsWith("S")){
							AllStatus.setText("Cadastro realizado com sucesso.");
							AllStatus.setForeground(Color.green);
						}
					}
					
					} catch (Exception e) {
						e.printStackTrace();
					}
					titulo.setText("Título");
					ano.setText("Ano");
					}
					
				}
				
			}
		});
		verLista.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {
					// URL que será aberta
					URI url = new URI("http://localhost:8000/VerGame");

					// Verifica se o Desktop é suportado no sistema
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						desktop.browse(url); // Abre a URL no navegador padrão
					} else {
						System.out.println("Desktop não é suportado neste sistema.");
					}
				} catch (URISyntaxException | IOException e) {
					e.printStackTrace();
				}

			}
		});
		buscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {
					// URL que será aberta
					URI url = new URI("http://localhost:8000/buscar/"+titulo.getText().replace(" ", "%20"));

					// Verifica se o Desktop é suportado no sistema
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						desktop.browse(url); // Abre a URL no navegador padrão
					} else {
						System.out.println("Desktop não é suportado neste sistema.");
					}
				} catch (URISyntaxException | IOException e) {
					e.printStackTrace();
				}

			}
		});
		deletar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {
					// URL que será aberta

					URI url = new URI("http://localhost:8000/deletar/" + titulo.getText().replace(" ", "%20"));
					System.out.println(url);
					HttpClient client = HttpClient.newHttpClient();
					HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:8000/deletar/"+titulo.getText().replace(" ", "%20")))
						.header("Content-Type", "application/json")
						.DELETE()
						.build();

					HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());
						
					//System.out.println(resp.body());
					
					if(resp.body() != null){
						if(resp.body().startsWith("S")){
							AllStatus.setText("Jogo deletado com sucesso!");
							AllStatus.setForeground(Color.green);
						}
						if(resp.body().startsWith("N")){
							AllStatus.setText("Jogo não encontrado!");
							AllStatus.setForeground(Color.red);
						}
					}
						

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		atualizar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			
					// URL que será aberta
					if(selectedOption2 == null){
						AllStatus.setText("Classificação não selecionada!");
						AllStatus.setForeground(Color.black);
					}else{
						try{
							JSONObject gameJson = new JSONObject();
							gameJson.put("titulo", titulo.getText().replace(" ", "%20"));
							gameJson.put("classificacao", selectedOption2);
							String json = String.format(
								"{\"titulo\":\"%s\",\"classificacao\":%s}",
								titulo.getText(),            // Overwatch
								selectedOption2         
							);
							//URI url = new URI("http://localhost:8000/deletar/" + titulo.getText().replace(" ", "%20"));
							//System.out.println(url);
							HttpClient client = HttpClient.newHttpClient();
							HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8000/atualizar"))
								.header("Content-Type", "application/json")
								.PUT(HttpRequest.BodyPublishers.ofString(json))
								.build();

							HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());
								
							System.out.println(resp.body());
							
							if(resp.body() != null){
								if(resp.body().startsWith("S")){
									AllStatus.setText("Jogo atualizado com sucesso!");
									AllStatus.setForeground(Color.green);
								}
								if(resp.body().startsWith("N")){
									AllStatus.setText("Jogo não encontrado!");
									AllStatus.setForeground(Color.red);
								}
							}
								

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}
		});
		painel.setLayout(null);
		painel.setVisible(true);
		painel.add(confirmar);
		painel.add(ano);
		painel.add(titulo);
		painel.add(classifca);
		painel.add(ListaDeGenerosDeGame);
		painel.add(verLista);
		painel.add(deletar);
		painel.add(AllStatus);
		painel.add(atualizar);
		painel.add(buscar);
		SpringApplication.run(DemoApplication.class, args);

	}

}
