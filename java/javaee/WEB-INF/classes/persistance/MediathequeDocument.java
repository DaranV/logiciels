package persistance;

import java.sql.Date;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

public abstract class MediathequeDocument implements Document {


	private String nom;
	private int id;
	private Date dateCreation;
	private boolean status;
	private String imageURL;
	private int idEmpunteur;

	public MediathequeDocument(Integer id,String nom,Date d,Boolean status,String imageURL,Integer idEmpunteur) {
		this.id=id;
		this.nom = nom;
		dateCreation = d;
		this.status = status;
		this.imageURL = imageURL;
		this.idEmpunteur = idEmpunteur;
	}



	@Override
	public void rendre(Utilisateur user) throws RetourException {
		System.out.println("------------- ENTRE DANS RETOUR ---------------");
		if(user != null) {
			DocumentStatusManager.rendre(this, user);	
		}else {
			DocumentStatusManager.rendre(this);
		}

	}


	@Override
	public void emprunter(Utilisateur user) throws EmpruntException {
		DocumentStatusManager.emprunter(this,user);

	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getId() {
		return id;
	}




	public Date getDateCreation() {
		return dateCreation;
	}

	public boolean isStatus() {
		return status;
	}


	public boolean isDisponible() {
		return status;
	}




	public String getImageURL() {
		return imageURL;
	}



	public int getIdEmpunteur() {
		return idEmpunteur;
	}






}
