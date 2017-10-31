import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class ServidorDistrito {
    
    private static String nombre;
	private static String IP_multi;
	private static int puerto_multi;
	private static String IP_peti;
	private static int puerto_peti;
	private static List<Titan> titanes = new ArrayList<Titan>();
	private static List<Titan> asesinados = new ArrayList<Titan>();
	private static List<Titan> capturados = new ArrayList<Titan>();
	int id = 0;

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        
		System.out.println("INICIALIZAR DISTRITO");
		System.out.println("[Distrito] Nombre Servidor:");
		Scanner scan1 = new Scanner(System.in);
		nombre = scan1.nextLine();
		
		System.out.println("[Distrito "+nombre+"] IP Multicast:");
		Scanner scan2 = new Scanner(System.in);
		IP_multi = scan2.nextLine();
		
		System.out.println("[Distrito "+nombre+"] Puerto Multicast:");
		Scanner scan3 = new Scanner(System.in);
		puerto_multi = scan3.nextInt();
		
		System.out.println("[Distrito "+nombre+"] IP Peticiones:");
		Scanner scan4 = new Scanner(System.in);
		IP_peti = scan4.nextLine();
		
		System.out.println("[Distrito "+nombre+"] Puerto peticiones:");
		Scanner scan5 = new Scanner(System.in);
		puerto_peti = scan5.nextInt();
		
        InetAddress addr = InetAddress.getByName(IP_multi);
     
        try (DatagramSocket serverSocket = new DatagramSocket()) {
			
			while(true) {
				
				System.out.println("[Distrito "+nombre+"] Consola");
				System.out.println("[Distrito "+nombre+"] (1) Publicar titán");
				System.out.println("[Distrito "+nombre+"] (2) Responder Petición");
				System.out.println("[Distrito "+nombre+"] (3) Enviar Actualización");
				Scanner scan6 = new Scanner(System.in);
				int num = scan6.nextInt();
				
				if (num == 1){
					String titan = publicarTitan();
					DatagramPacket msgPacket = new DatagramPacket(titan.getBytes(),titan.getBytes().length, addr , puerto_multi);
					serverSocket.send(msgPacket);
					}
						
				else if (num == 2){
					// responder peticion
				}
				
				else {
					//String actualizacion = printTitanes();
					//DatagramPacket msgPacket = new DatagramPacket(actualizacion.getBytes(),actualizacion.getBytes().length, addr , puerto_multi);
					//serverSocket.send(msgPacket);
				}

			}
			
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	public static String publicarTitan() {
		System.out.println("[Distrito "+nombre+"] Publicar titán");
		System.out.println("[Distrito "+nombre+"] Introducir nombre:");
		Scanner scan1 = new Scanner(System.in);
		String nombret = scan1.nextLine();
		
		System.out.println("[Distrito "+nombre+"] Introducir tipo:");
		System.out.println("1. Normal\n2. Excéntrico\n3. Cambiante");
		Scanner scan2 = new Scanner(System.in);
		int tipo = scan2.nextInt();
		
		Titan t = new Titan(id,nombret,tipo);
		titanes.add(t);
		String datos = id+","+nombret+","+t.getTipoS();
		System.out.println("[Distrito "+nombre+"] Se ha publicado el titán: "+nombret);
		System.out.println("***************");
		System.out.println(" ID: "+id);
		System.out.println(" Nombre: "+nombret);
		System.out.println(" Tipo: "+t.getTipoS());
		System.out.println("***************");
		id++;
		
		return datos;
	}
	
/* 	public static String printTitanes() {
		String datos;
		for (Titan t : titanes) {
			datos = t.getID()+","+t.getNombre()+","+t.getTipoS()+"\n";
			}
		return datos;
	} */
}