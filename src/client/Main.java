package client;

import java.net.Socket;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String name;
		String serverAddress;
		int port;
		try {
			name = args[0];
			serverAddress = args[1];
			port = Integer.parseInt(args[2]);
			
			try {
				Socket connection = new Socket(serverAddress, port);
				
				Client client = new Client(name, connection);
				Scanner scanner = new Scanner(System.in);
				while (client.isConnected) {
					client.sendMsg(scanner.nextLine());
				}
				scanner.close();
			} catch (Exception e) {
				System.out.println(e);
				System.err.println("Erro: não foi possível conectar com o servidor");
			}
			
		} catch (Exception e) {
			System.err.println("Erro: espera-se o nome do cliente, o endereço IP do server e um inteiro representando o número da porta como argumentos");
		}
	}

}
