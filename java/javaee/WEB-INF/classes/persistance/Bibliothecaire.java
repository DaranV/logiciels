package persistance;

import mediatek2020.items.Utilisateur;
public class Bibliothecaire implements Utilisateur {

	private String name;
	private String email;
	private String mdp;
	private int id;
	
	public Bibliothecaire(int id, String name,String email,String mdp) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.setMdp(mdp);
	}
	
	@Override
	public Object[] data() {
		return new Object[] {name,email,id};
	}

	@Override
	public boolean isBibliothecaire() {
		return true;
	}

	@Override
	public String name() {
		return name;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getId() {
		return id;
	}

}
