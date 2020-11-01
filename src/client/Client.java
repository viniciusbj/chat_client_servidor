package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private Socket connection;
	
	public Client (String name, Socket c) {
		this.connection = c;
		new listenServer().start();
		new sendCommand("NAME:"+name).start();
	}
	
	public void sendMsg (String input) {
		String command = input.split(" ")[0].toUpperCase();
		if (command.equals("WHO") || command.equals("HELP")) {
			new sendCommand(command).start();
		}
		else if (command.equals("SEND") || command.equals("SENDTO")) {
			int indexOfFirstSpace = input.indexOf(' ');
			
			if (command.equals("SENDTO")) {
				indexOfFirstSpace += 1;
				int lengthOfReceiver = input.substring(indexOfFirstSpace).split(" ")[0].length();
				int indexOfSecondSpace = input.indexOf(' ', lengthOfReceiver);

				String receiver = input.substring(indexOfFirstSpace, indexOfSecondSpace);
				command += ":"+receiver;
				indexOfFirstSpace = indexOfSecondSpace;
			}
			
			String msg = input.substring(indexOfFirstSpace);
			if (msg.length() > 0) {
				new sendCommand(command+":"+msg).start();
			} else {
				System.err.println("ERRO: espera-se uma mensagem a ser enviada no comando "+command);
			}
		}
	}
	
	private void receiveMsg (String msg) {
		System.out.println(msg);
	}
	
	private void disconect () {
		try {
			connection.close();
		} catch (IOException e) {
			System.err.println("ERRO: não foi possível fechar a conexão");
		}
	}
	
	private class listenServer extends Thread {
		
		@Override
		public void run() {
			try {
				Scanner input = new Scanner(connection.getInputStream());
				String msg;
				while ((msg = input.nextLine()) != null) {
					receiveMsg(msg);
				}
				input.close();
			} catch (Exception e) {
			} finally {
				disconect();
			}
		}
	}
	
	private class sendCommand extends Thread {
		
		private String msg;
		
		public sendCommand (String m) {
			this.msg = m;
		}
		
		@Override
		public void run() {
			PrintWriter output;
			try {
				output = new PrintWriter(connection.getOutputStream(), true);
				output.println(msg);
			} catch (IOException e) {		
				e.printStackTrace();
			}
		}
	}
}
