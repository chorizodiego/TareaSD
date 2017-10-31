import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class DistritoDatos {
	private String nombre;
	private String IP_multi;
	private int puerto_multi;
	private String IP_peti;
	private int puerto_peti;

	public DistritoDatos(String n, String ipm, int pm, String ipp, int pp){

		this.nombre = n;
		this.IP_multi = ipm;
		this.puerto_multi = pm;
		this.IP_peti = ipp;
		this.puerto_peti = pp;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getIP_multi() {
		return IP_multi;
	}

	public int getPuerto_multi() {
		return puerto_multi;
	}

	public String getIP_peti() {
		return IP_peti;
	}

	public int getPuerto_peti() {
		return puerto_peti;
	}
	
}