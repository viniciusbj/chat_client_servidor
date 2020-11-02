package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

	private int port;
	private ArrayList<User> users = new ArrayList<User>();
	
	public Server (int port) {
		this.port = port;
		System.out.println(port);
	}
	
	@Override
	public void run() {
		ServerSocket servidor;
		try {
			servidor = new ServerSocket(this.port);
			while (true) {
				Socket User = servidor.accept();
				users.add(new User(User, this));
			}
		} catch (IOException e) {
		}
		for (User User: users) {
			disconectUser(User);
		}
	}
	
	public void deserializeMsg (String msg, User user) {
		
		String command = msg.split(":")[0];

		// envia messagem para um cliente específico
		if (msg.startsWith("SENDTO")) {
			
			// Procura cliente pelo nome
			String receiverName = msg.split(":")[1];
			User receiver = getUserByName(receiverName);
			
			if (receiver == null) {
				user.newMessage("ERRO: cliente não encontrado");
				command += "\t Executado: Não";
				Main.printConsole(user.getName(), command);
			} else {
				String msgToSend = user.getName();
				msgToSend += ' ' + msg.split(":")[2];
				sendToUser(msgToSend, receiver);
				
				command += "\t Executado: Sim";
				Main.printConsole(user.getName(), command);
			}
		}
		// envia mensagem para todos
		else if (msg.startsWith("SEND")) {
			String msgToSend = user.getName();
			msgToSend += msg.split(":")[1];
			sendToAllUser(msgToSend, user);

			command += "\t Executado: Sim";
			Main.printConsole(user.getName(), command);
		}
		// retorna quem está conectado
		else if (msg.equals("WHO")) {
			
			String nomes = users.get(0).getName();
			for (int i=1; i<users.size(); i++) {
				nomes += ", ";
				nomes += users.get(i).getName();
			}
			user.newMessage(nomes);
			
			command += "\t Executado: Sim";
			Main.printConsole(user.getName(), command);
		}
		// tabela de ajuda
		else if (msg.equals("HELP")) {						
			
			user.newMessage("Comandos:\n"
					+ "SEND: Envia mensagem para todos os clientes conectados.\n"
					+ "SENDTO: Idêntico com SEND, porém envia a mensagem apenas para o cliente especificado.\n"
					+ "WHO: Retorna a lista dos clientes conectados ao servidor.\n"
					+ "HELP: Retorna a lista de comandos suportados e seu uso.\n"
			);
			
			command += "\t Executado: Sim";
			Main.printConsole(user.getName(), command);
		}
		// recebe nome do cliente para confirmar conexão
		else if (msg.startsWith("NAME")) {
			
			String userName = msg.split(":")[1];
			User newUser = getUserByName(userName);
			// nome ainda não escolhido
			if (newUser == null) {
				user.setName(userName);
				user.newMessage("Conectado com sucesso");
				Main.printConsole(userName, "Conectado");
			} 
			// nome escolhido, desconecta cliente
			else {
				user.newMessage("ERRO: nome já escolhido\n"
						+ "Falha ao conectar com servidor");
				users.remove(user);
			}
		} else {
			user.newMessage("ERRO: servidor não reconhece o comando.");
			command += "\t Executado: Não";
			Main.printConsole(user.getName(), command);
		}
	}

	public void sendToUser (String msg, User user) {
		user.newMessage(msg);
	}
	
	public void sendToAllUser (String msg, User user) {
		for (User userTo: users) {
			if (userTo == user) {
				continue;
			}
			userTo.newMessage(msg);
		}
	}
	
	private User getUserByName (String name) {
		for (User userTo: users) {
			if (userTo.getName() != null && userTo.getName().equals(name)) {
				return userTo;
			}
		}
		return null;
	}
	
	public void disconectUser (User user) {
		Main.printConsole(user.getName(), "Desconectado");
		users.remove(user);
	}

}
