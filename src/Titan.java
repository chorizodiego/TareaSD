import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;

public class Titan {
	private int id;
	private String nombre;
	private int tipo;
	private String tipoS;
	
	public Titan(int id, String nombre, int t){

		this.id = id;
		this.nombre = nombre;
		this.tipo = t;
		if (tipo == 1){
			this.tipoS = "Normal";
		}
		else if (tipo == 2) {
			this.tipoS = "Excéntrico";
		}
		else {
			this.tipoS = "Cambiante";
		}
	}

	public int getID(){
		return id;
	}

	public String getNombre(){
		return nombre;
	}

	public int getTipo(){
		return tipo;
	}
	
	public String getTipoS(){
		return tipoS;
	}
	
}