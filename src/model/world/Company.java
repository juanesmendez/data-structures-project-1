package model.world;

public class Company implements Comparable<Company>{
	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int compareTo(Company arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
