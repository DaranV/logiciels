package persistance;

import mediatek2020.items.Utilisateur;

public class Abonne implements Utilisateur {

	private int id;
	private String name;
	private String email;
	private String mdp;
	
	public Abonne(Integer id,String name,String email,String mdp) {
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
		return false;
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

	public void setId(int id) {
		this.id = id;
	}

}
