import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;



public class ServidorCentral {
    
	private static Socket socket;
	private static int PUERTO = 9000;
	private static List<DistritoDatos> distritos = new ArrayList<DistritoDatos>();
	int id = 0;
	
    public static void main(String[] args) {
		
		while (true) {
			System.out.println("AGREGAR DISTRITO");
			System.out.println("[Servidor Central] Nombre Distrito:");
			Scanner scan1 = new Scanner(System.in);
			String nombre = scan1.nextLine();
			
			System.out.println("[Servidor Central] IP Multicast:");
			Scanner scan2 = new Scanner(System.in);
			String IP_multi = scan2.nextLine();
			
			System.out.println("[Servidor Central] Puerto Multicast:");
			Scanner scan3 = new Scanner(System.in);
			int puerto_multi = scan3.nextInt();
			
			System.out.println("[Servidor Central] IP Peticiones:");
			Scanner scan4 = new Scanner(System.in);
			String IP_peti = scan4.nextLine();
			
			System.out.println("[Servidor Central] Puerto peticiones:");
			Scanner scan5 = new Scanner(System.in);
			int puerto_peti = scan5.nextInt();
			
			DistritoDatos d = new DistritoDatos(nombre,IP_multi,puerto_multi,IP_peti,puerto_peti);
			distritos.add(d);
	
			System.out.println("[Servidor Central] Seguir agregando distritos? (y/n):");
			Scanner scan6 = new Scanner(System.in);
			String letra = scan6.nextLine();
			
			if (letra.equalsIgnoreCase("y")){
				continue;
			} break;
		}

		try {
			ServerSocket servCentral = new ServerSocket(PUERTO);
			System.out.println("[Servidor Central] Servidor iniciado y escuchando el puerto "+PUERTO);
			
			while(true) {
				System.out.println("Esperando conexión\n");
				socket = servCentral.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				String nombreDistrito = br.readLine();
				String ip_cliente = socket.getRemoteSocketAddress().toString();
				System.out.println("Message received from client is "+nombreDistrito);
				
				System.out.println("[Servidor Central] Dar Autorización a "+ip_cliente+" por Distrito "+nombreDistrito+":\n1 .- SI\n2 .- NO");
				Scanner scanauth = new Scanner(System.in);
				int auth = scanauth.nextInt();
				String mensajeRetorno;
				mensajeRetorno = String.valueOf(auth) + "\n";
				
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				
				if (auth == 1){

					bw.write(mensajeRetorno);
					System.out.println("Message sent to the client is "+mensajeRetorno);
					bw.flush();
					
					int x = getDistrito(nombreDistrito);
					
					System.out.println("[Servidor Central] Respuesta a "+ip_cliente+" por "+nombreDistrito);
					System.out.println("[Servidor Central] Nombre: "+distritos.get(x).getNombre()+", IP Multicast: "+distritos.get(x).getIP_multi()+", Puerto Multicast: "+distritos.get(x).getPuerto_multi()+", IP Peticiones: "+distritos.get(x).getIP_peti()+", Puerto Peticiones: "+distritos.get(x).getPuerto_peti());
					
					String msg = distritos.get(x).getNombre()+","+distritos.get(x).getIP_multi()+","+distritos.get(x).getPuerto_multi()+","+distritos.get(x).getIP_peti()+","+distritos.get(x).getPuerto_peti()+"\n";
					bw.write(msg);
					bw.flush();
					
				} else {
					System.out.println("No se ha permitido la conexión.\n");
				}
			}
        } catch (Exception exception) {
            exception.printStackTrace();
        }
		
    }
	
	public static int getDistrito(String nombre_d) {
		int pos = 0;
		for (DistritoDatos dd : distritos) {
			if (dd.getNombre().equals(nombre_d)){
				return pos;
			}
			pos++;
		}
		System.out.println("No existe tal distrito.");
		return -1;
	}
	
	
}