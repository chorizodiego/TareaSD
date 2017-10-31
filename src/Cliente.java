import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class Cliente {

	private static Socket socket;
	
	public static void main(String[] args) {
		System.out.println("[Cliente] Ingresar IP Servidor Central:");
		Scanner scan1 = new Scanner(System.in);
		String IP_SC = scan1.nextLine();
		
		System.out.println("[Cliente] Ingresar puerto Servidor Central:");
		Scanner scan2 = new Scanner(System.in);
		int puerto_SC = scan2.nextInt();
		
		System.out.println("[Cliente] Introducir nombre de distrito a investigar:");
		Scanner scan3 = new Scanner(System.in);
		String nombreDistrito = scan3.nextLine();	
		
		try {
			InetAddress SC_address = InetAddress.getByName(IP_SC);
			socket = new Socket(SC_address, puerto_SC);
			
			OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
			
			String enviado = nombreDistrito + "\n";
			bw.write(enviado);
            bw.flush();
			
			System.out.println("[Cliente] Esperando respuesta del servidor central...\n");
			
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String auth = br.readLine();
            
			if (auth.equals("1")) {

				String recibido = br.readLine();
				List<String> datos = Arrays.asList(recibido.split(","));
				// datos del distrito enviados por el SC
				// datos.get(i) con i [0-4]
				// 0 = Nombre distrito
				// 1 = IP Multicast
				// 2 = Puerto multicast
				// 3 = IP peticiones
				// 4 = Puerto peticiones
				
				System.out.println("Imprimo datos:\n"+datos.get(0)+"\n"+datos.get(1)+"\n"+datos.get(2)+"\n"+datos.get(3)+"\n"+datos.get(4)+"\n");
				
				InetAddress SD_address = InetAddress.getByName(datos.get(1));
				
				
				byte[] buf = new byte[1024];
				
				
				try (MulticastSocket clientSocket = new MulticastSocket(Integer.parseInt(datos.get(2)))){
					
					clientSocket.joinGroup(SD_address);
					
					while (true) {
						
						DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
						clientSocket.receive(msgPacket);
						String msg = new String(buf, 0, buf.length);
						List<String> datos2 = Arrays.asList(msg.split(","));
						System.out.println("[Cliente] Aparece nuevo titán! "+datos2.get(1)+", tipo "+datos2.get(2)+", ID "+datos2.get(0));
						
						System.out.println("[Cliente] Consola");
						System.out.println("[Cliente] (1) Listar titanes");
						//System.out.println("[Cliente] (2) Cambiar distrito");
						System.out.println("[Cliente] (3) Capturar titán");
						System.out.println("[Cliente] (4) Asesinar titán");
						//System.out.println("[Cliente] (5) Listar titanes capturados");
						//System.out.println("[Cliente] (6) Listar titanes asesinados");
						Scanner scan4 = new Scanner(System.in);
						int num = scan4.nextInt();
						
						if (num == 1){
							// listar titanes
							continue;
						}
						
						else if (num == 2){
							System.out.println("[Cliente] Introducir nombre de distrito a investigar: ");
							Scanner scan5 = new Scanner(System.in);
							nombreDistrito = scan5.nextLine();
							// cambiar distrito
						}
						
						else if (num == 3){
							// capturar titan
						}
						
						else if (num == 4){
							// asesinar titan
						}
						
						else if (num == 5){
							// listar capturados
						}
						
						else {
							// listar asesinados
						}
					}
				} catch (IOException ex) {
				  ex.printStackTrace();
				}
		
			} else {
				System.out.println("[Cliente] El servidor central te cagó :c\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
        }
	}
}
