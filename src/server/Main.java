package server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {
	
	public static void main(String[] args) {
		int port;
		try {
			port = Integer.parseInt(args[0]);
			Server server = new Server(port);
			new Thread(server).start();
		} catch (Exception e) {
			System.err.println("Erro: espera-se um inteiro representando o número da porta como argumento");
		}
	}
	
	// Imprime mensagem no console do servidor
	public static void printConsole(String clientName, String msg) {
		System.out.print(getHour() + "\t");
		System.out.print(clientName +"\t");
		System.out.println(msg);
	}
	
	private static String getHour() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(new Date());
	}
	
}
