package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static String selectedOption;
	public static String selectedOption2;

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
		JFrame painel = new JFrame("GAMES");
		JFrame login = new JFrame("LOGIN E CADASTRO");
		// Campos para cadastro do jogo
		JButton confirmar = new JButton("CADASTRAR");
		JButton verLista = new JButton("LISTA");
		JButton deletar = new JButton("DELETAR");
		JButton atualizar = new JButton("ATUALIZAR CLASSIFICAÇÃO");
		JButton buscar = new JButton("BUSCAR");
		JButton logout = new JButton("LOGOUT");
		JTextField titulo = new JTextField("Título");
		JTextField ano = new JTextField("Ano");
		JLabel AllStatus = new JLabel("");
		JLabel AllStatusTres = new JLabel("");
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
		logout.setBounds(320, 250, 200, 25);
		AllStatus.setBounds(320, 275, 225, 25);
		logout.setForeground(Color.white);
		logout.setBackground(Color.red);
		deletar.setForeground(Color.white);
		deletar.setBackground(Color.red);
		atualizar.setForeground(Color.white);
		atualizar.setBackground(Color.blue);
		verLista.setForeground(Color.white);
		verLista.setBackground(Color.gray);
		buscar.setForeground(Color.white);
		buscar.setBackground(Color.orange);
		// Campos para cadastro do usuário
		JTextField nome = new JTextField("Nome");
		JTextField email = new JTextField("Email");
		JTextField senha = new JTextField("Senha");
		JTextField emailLogin = new JTextField("Email");
		JTextField senhaLogin = new JTextField("Senha");
		JButton confirmarLogin = new JButton("CONFIRMAR");
		emailLogin.setBounds(320, 25, 200, 25);
		senhaLogin.setBounds(320, 50, 200, 25);
		confirmarLogin.setBounds(320, 75, 200, 25);
		AllStatusTres.setBounds(320, 100, 200, 25);
		confirmarLogin.setForeground(Color.white);
		confirmarLogin.setBackground(Color.green);

		JButton confirmarUser = new JButton("CADASTRAR");
		JButton atualizarSenha = new JButton("ATUALIZAR SENHA");
		JLabel AllStatusDois = new JLabel("");
		nome.setBounds(640, 25, 200, 25);
		email.setBounds(640, 50, 200, 25);
		senha.setBounds(640, 75, 200, 25);
		confirmarUser.setBounds(640, 100, 200, 25);
		atualizarSenha.setBounds(640, 125, 200, 25);
		AllStatusDois.setBounds(640, 150, 200, 25);
		confirmarUser.setForeground(Color.white);
		confirmarUser.setBackground(Color.green);
		atualizarSenha.setForeground(Color.white);
		atualizarSenha.setBackground(Color.blue);
		login.setVisible(true);
		login.setLayout(null);
		login.add(emailLogin);
		login.add(senhaLogin);
		login.add(AllStatusTres);
		login.add(confirmarLogin);
		login.add(nome);
		login.add(email);
		login.add(senha);
		login.add(confirmarUser);
		login.add(atualizarSenha);
		login.add(AllStatusDois);
		ListaDeGenerosDeGame.addActionListener(e -> {
			selectedOption = (String) ListaDeGenerosDeGame.getSelectedItem();
			System.out.println("Selected: " + selectedOption);
		});

		classifca.addActionListener(e -> {
			selectedOption2 = (String) classifca.getSelectedItem();
			System.out.println("Selected: " + selectedOption2);
		});
		// X
		painel.setSize(1280, 1280);
		login.setSize(1280, 1280);
		confirmarLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (emailLogin.getText().equals("Email") && senhaLogin.getText().equals("Senha")) {
					AllStatusTres.setText("Dados invalidos.");
				} else {
					try {

						String json = String.format(
								"{\"email\":\"%s\",\"senha\":\"%s\"}",
								emailLogin.getText(),
								senhaLogin.getText().toString());

						HttpClient client = HttpClient.newHttpClient();
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8000/auth/generateToken"))
								.header("Content-Type", "application/json")
								.POST(HttpRequest.BodyPublishers.ofString(json))
								.build();

						HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

						System.out.println(resp.body());
						System.out.println(resp.statusCode());
						if (resp.statusCode() == 200) {
							/*
							 * if (resp.body() != null) {
							 * AllStatusTres.setText("Usuário não encontrado!");
							 * AllStatusTres.setForeground(Color.red);
							 * }
							 */
							if (resp.body().startsWith("S")) {

								login.dispose();
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
								painel.add(logout);

							} else {
								AllStatusTres.setText("Usuário não encontrado!");
								AllStatusTres.setForeground(Color.red);
							}
						} else {
							AllStatusTres.setText("Usuário não encontrado!");
							AllStatusTres.setForeground(Color.red);
						}

					} catch (Exception e) {

						e.printStackTrace();
					}

				}

			}
		});
		logout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try{
					String json = String.format(
								"{\"email\":\"%s\",\"senha\":\"%s\"}",
								emailLogin.getText(),
								senhaLogin.getText().toString());

						HttpClient client = HttpClient.newHttpClient();
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8000/auth/logout"))
								.header("Content-Type", "application/json")
								.POST(HttpRequest.BodyPublishers.ofString(json))
								.build();

						HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

						System.out.println(resp.body());
						System.out.println(resp.statusCode());
						painel.dispose();
						login.setVisible(true);
						login.setLayout(null);
						login.add(emailLogin);
						login.add(senhaLogin);
						login.add(AllStatusTres);
						login.add(confirmarLogin);
						login.add(nome);
						login.add(email);
						login.add(senha);
						login.add(confirmarUser);
						login.add(atualizarSenha);
						login.add(AllStatusDois);
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
		});
		confirmar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (selectedOption == null || selectedOption2 == null) {
					AllStatus.setText("Campos não selecionados!");
					AllStatus.setForeground(Color.black);

				} else {
					if (ano.getText().equals("Ano") || titulo.getText().equals("Título")) {
						AllStatus.setText("Por favor preencher os campos!");
						AllStatus.setForeground(Color.black);

					} else if ((Integer.parseInt(ano.getText().toString()) < 1958
							|| Integer.parseInt(ano.getText().toString()) > 2025)) {
						AllStatus.setText("Ano invalido!");
						AllStatus.setForeground(Color.red);
					} else {
						try {
							// URL que será aberta

							String json = String.format(
									"{\"titulo\":\"%s\",\"ano\":%s,\"genero\":\"%s\",\"classificacao\":%s}",
									titulo.getText(),
									ano.getText(),
									selectedOption,
									selectedOption2);

							HttpClient client = HttpClient.newHttpClient();
							HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create("http://localhost:8000/auth/cadastrar"))
									.header("Content-Type", "application/json")
									.POST(HttpRequest.BodyPublishers.ofString(json))
									.build();

							HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

							System.out.println(resp.body());
							if (resp.body() != null) {
								if (resp.body().startsWith("N")) {
									AllStatus.setText("Jogo já foi cadastrado.");
									AllStatus.setForeground(Color.red);
								}
								if (resp.body().startsWith("S")) {
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

		confirmarUser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (nome.getText().equals("Nome") || senha.getText().equals("Senha")
						|| email.getText().equals("Email")) {

					AllStatusDois.setText("Por favor preencher os campos!");
					AllStatusDois.setForeground(Color.black);
				} else if (!email.getText().contains("@") || !email.getText().contains(".")) {
					AllStatusDois.setText("Email inválido!");
					AllStatusDois.setForeground(Color.red);
				} else if (!senha.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
					AllStatusDois.setText("Senha inválida!");
					AllStatusDois.setForeground(Color.red);
					JOptionPane.showMessageDialog(null,
							"A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas e números.",
							"Senha inválida",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {

						String json = String.format(
								"{\"nome\":\"%s\",\"email\":\"%s\",\"senha\":\"%s\"}",
								nome.getText(),
								email.getText().toString(),
								senha.getText());

						HttpClient client = HttpClient.newHttpClient();
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8000/auth/cadastrarUser"))
								.header("Content-Type", "application/json")
								.POST(HttpRequest.BodyPublishers.ofString(json))
								.build();

						HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

						System.out.println(resp.body());
						if (resp.body() != null) {
							if (resp.body().startsWith("N")) {
								AllStatusDois.setText("Usuário já foi cadastrado!");
								AllStatusDois.setForeground(Color.red);
							}
							if (resp.body().startsWith("S")) {
								AllStatusDois.setText("Usuário cadastrado com sucesso!!");
								AllStatusDois.setForeground(Color.green);
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					email.setText("Email");
					nome.setText("Nome");
					senha.setText("Senha");
				}

			}
		});

		verLista.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {
					// URL que será aberta
					URI url = new URI("http://localhost:8000/auth/VerGame");

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
					URI url = new URI("http://localhost:8000/auth/buscar/" + titulo.getText().replace(" ", "%20"));

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

					URI url = new URI("http://localhost:8000/auth/deletar/" + titulo.getText().replace(" ", "%20"));
					System.out.println(url);
					HttpClient client = HttpClient.newHttpClient();
					HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create(
									"http://localhost:8000/auth/deletar/" + titulo.getText().replace(" ", "%20")))
							.header("Content-Type", "application/json")
							.DELETE()
							.build();

					HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

					// System.out.println(resp.body());

					if (resp.body() != null) {
						if (resp.body().startsWith("S")) {
							AllStatus.setText("Jogo deletado com sucesso!");
							AllStatus.setForeground(Color.green);
						}
						if (resp.body().startsWith("N")) {
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
				if (selectedOption2 == null) {
					AllStatus.setText("Classificação não selecionada!");
					AllStatus.setForeground(Color.black);
				} else {
					try {

						String json = String.format(
								"{\"titulo\":\"%s\",\"classificacao\":%s}",
								titulo.getText(), // Overwatch
								selectedOption2);
						// URI url = new URI("http://localhost:8000/deletar/" +
						// titulo.getText().replace(" ", "%20"));
						// System.out.println(url);
						HttpClient client = HttpClient.newHttpClient();
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8000/auth/atualizar"))
								.header("Content-Type", "application/json")
								.PUT(HttpRequest.BodyPublishers.ofString(json))
								.build();

						HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

						System.out.println(resp.body());

						if (resp.body() != null) {
							if (resp.body().startsWith("S")) {
								AllStatus.setText("Jogo atualizado com sucesso!");
								AllStatus.setForeground(Color.green);
							}
							if (resp.body().startsWith("N")) {
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
		atualizarSenha.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				// URL que será aberta
				if (senha.getText().equals("Senha")) {
					AllStatusDois.setText("Senha não permitida.");
					AllStatusDois.setForeground(Color.black);
				} else if (!senha.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
					AllStatusDois.setText("Senha inválida!");
					AllStatusDois.setForeground(Color.red);
					JOptionPane.showMessageDialog(null,
							"A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas e números.",
							"Senha inválida",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {

						String json = String.format(
								"{\"email\":\"%s\",\"senha\":\"%s\"}",
								email.getText(),
								senha.getText());

						HttpClient client = HttpClient.newHttpClient();
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("http://localhost:8000/auth/atualizarUser"))
								.header("Content-Type", "application/json")
								.PUT(HttpRequest.BodyPublishers.ofString(json))
								.build();

						HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

						System.out.println(resp.body());

						if (resp.body() != null) {
							if (resp.body().startsWith("S")) {
								AllStatusDois.setText("Senha atualizada com sucesso!");
								AllStatusDois.setForeground(Color.green);
							}
							if (resp.body().startsWith("N")) {
								AllStatusDois.setText("Usuário não encontrado!");
								AllStatusDois.setForeground(Color.red);
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		SpringApplication.run(DemoApplication.class, args);

	}

}
