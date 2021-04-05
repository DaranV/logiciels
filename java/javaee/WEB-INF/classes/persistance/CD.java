package persistance;

import java.sql.Date;

import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

public class CD extends MediathequeDocument{

	public CD(Integer id,String nom,Date d,Boolean status,String imageURL,Integer idEmpunteur) {
		super(id,nom,d,status,imageURL,idEmpunteur);
	}




	@Override
	public Object[] data() {
		return new Object[] {getId(),getNom(),"CD",getDateCreation().toString(),isDisponible(),getImageURL(),getIdEmpunteur()};
	}

	@Override
	public void reserver(Utilisateur arg0) throws ReservationException {
		// TODO Auto-generated method stub

	}

}
