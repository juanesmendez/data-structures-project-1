package mundo;

public class Taxi {
	private String id;
	private String nombreCompania;
	
	public Taxi(String id, String nombreCompania) {
		this.id = id;
		this.nombreCompania = nombreCompania;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}
	
	public String toString() {
		return "id: "+this.id+" nombre compañia: "+this.nombreCompania;
	}
}
