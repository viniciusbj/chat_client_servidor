package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class User {
	private Socket connection;
	private String name;
	private Server server;
	
	public User (Socket c, Server s) {
		this.connection = c;
		this.server = s;
		new listenClient().start();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void newMessage (String msg) {
		new sendNotify(msg).start();
	}
	
	private void receiveMsg (String msg) {
		server.deserializeMsg(msg, this);
	}
	
	public void disconect () {
		server.disconectUser(this);
	}
	
	private class listenClient extends Thread {
		
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

	private class sendNotify extends Thread {
		
		private String msg;
		
		public sendNotify (String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			PrintWriter output;
			try {
				output = new PrintWriter(connection.getOutputStream(), true);
				output.println(msg);
			} catch (IOException e) {		
				disconect();
			}
		}
		
	}
	
}
