package model;

public class Atsiliepimai {
	int id;
	String miestas;
	String vardas;
	String elPastas;
	String atsiliepimas;
	
	public Atsiliepimai() {
		super();
	}


	public Atsiliepimai(String miestas, String vardas, String elPastas, String atsiliepimas) {
		super();
		this.miestas = miestas;
		this.vardas = vardas;
		this.elPastas = elPastas;
		this.atsiliepimas = atsiliepimas;
	}


	public Atsiliepimai(int id, String miestas, String vardas, String elPastas, String atsiliepimas) {
		super();
		this.id = id;
		this.miestas = miestas;
		this.vardas = vardas;
		this.elPastas = elPastas;
		this.atsiliepimas = atsiliepimas;
	}


	public int getId() {
		return id;
	}


	private void setId(int id) {
		this.id = id;
	}


	public String getMiestas() {
		return miestas;
	}


	public void setMiestas(String miestas) {
		this.miestas = miestas;
	}


	public String getVardas() {
		return vardas;
	}


	public void setVardas(String vardas) {
		this.vardas = vardas;
	}


	public String getElPastas() {
		return elPastas;
	}


	public void setElPastas(String elPastas) {
		this.elPastas = elPastas;
	}


	public String getAtsiliepimas() {
		return atsiliepimas;
	}


	public void setAtsiliepimas(String atsiliepimas) {
		this.atsiliepimas = atsiliepimas;
	}
	
}
