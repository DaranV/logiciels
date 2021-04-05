package persistance;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mediatek2020.Mediatheque;
import mediatek2020.PersistentMediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.Utilisateur;

public class MediathequeData implements PersistentMediatheque{
	private PreparedStatement getDocumentStatement = null;
	private PreparedStatement getAbonneStatement= null;
	private PreparedStatement getBibliothecaireStatement= null;
	private PreparedStatement getAllDocument = null;
	private PreparedStatement addDocument = null;


	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	@Override
	public synchronized Document  getDocument(int arg0) {

		Connection connection = BDConnectionGenerator.getConnectionInstance();

		try {
			
			getDocumentStatement  = (getDocumentStatement == null) ? connection.prepareStatement("Select * from document left join emprunt on document.IdDoc = emprunt.IdDoc_Emprunter  where IdDoc = ?") : getDocumentStatement;


			getDocumentStatement.setInt(1,arg0);

			ResultSet res = getDocumentStatement.executeQuery();



			while(res.next()) {

				String nom = res.getString("NomDoc");
				int id = res.getInt("IdDoc");
				Date creation = res.getDate("DateCreation");
				String type = res.getString("TypeDoc");
				boolean status =  res.getString("IdDoc_Emprunter") == null;
				String imageURL = res.getString("ImageURL");
				int idEmprunteur = res.getInt("IdUser");
				//Si l'iD de l'emprunterur etait null
				if(res.wasNull()) {
					idEmprunteur = -1;
				}


				try {

					return (Document) Class.forName("persistance."+type).getDeclaredConstructor(Integer.class,String.class,Date.class,Boolean.class,String.class,Integer.class).newInstance(id,nom,creation,status,imageURL,idEmprunteur);

				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			res.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}


		return null;



	}

	/**
	 * args0 : nom
	 * args1 : mdp
	 */
	@Override
	public synchronized Utilisateur getUser(String arg0, String arg1) {

		Connection connection = BDConnectionGenerator.getConnectionInstance();

		try {
			getAbonneStatement  = (getAbonneStatement == null) ? connection.prepareStatement("Select * from Abonne where login = ? AND mdp = ?") : getAbonneStatement;
			getBibliothecaireStatement = (getBibliothecaireStatement == null) ? connection.prepareStatement("Select * from Bibliothecaire where login = ? AND mdp = ?") : getBibliothecaireStatement;
			


			getAbonneStatement.setString(1,arg0);
			getAbonneStatement.setString(2,arg1);


			getBibliothecaireStatement.setString(1,arg0);
			getBibliothecaireStatement.setString(2,arg1);

			ResultSet resAbonne = getAbonneStatement.executeQuery();
			ResultSet resBibliothecaire = getBibliothecaireStatement.executeQuery();



			if(resBibliothecaire.next()) {	
				String name = resBibliothecaire.getString("login");
				String email = resBibliothecaire.getString("mail");
				String mdp = resBibliothecaire.getString("mdp");
				int id = resBibliothecaire.getInt("idBiblio");
				return (Utilisateur) new Bibliothecaire(id,name, email, mdp);
			}

			if(resAbonne.next()) {
				String name = resAbonne.getString("login");
				String email = resAbonne.getString("mail");
				String mdp = resAbonne.getString("mdp");;
				int id = resAbonne.getInt("idAbonne");
				return (Utilisateur) new Abonne(id,name, email, mdp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public synchronized void nouveauDocument(int arg0, Object... arg1) {
		
		System.out.println("-------Nouveau document -----------" + arg1[0] + "-----------------");
		System.out.println("-------Nouveau document -----------" + arg1[1] + "-----------------");
		System.out.println("-------Nouveau document -----------" + arg1[2] + "-----------------");
		
		Connection connection = BDConnectionGenerator.getConnectionInstance();
		 int nextID_from_seq = 0;
		
		Date dateCreation = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		try {
			
			String sql = "select doc_ID.nextval from DUAL";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    nextID_from_seq = rs.getInt(1);
			}
			
			
			
			addDocument = connection.prepareStatement("INSERT INTO DOCUMENT(IdDoc,NomDoc,TypeDoc,DateCreation,ImageURL) VALUES (?, ?, ?, ?, ?) ");
			
			addDocument.setInt(1, nextID_from_seq); // IdDoc
			addDocument.setString(2, (String) arg1[0]); // NomDoc
			addDocument.setString(3, (String) arg1[2]); // TypeDoc
			addDocument.setDate(4, dateCreation); // DateCreation
			addDocument.setString(5, (String) arg1[1]); // URL
			
			
			ResultSet res = addDocument.executeQuery();
			
			System.out.println(" ------ ajout du document effectué -----");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public synchronized List<Document> tousLesDocuments() {
		Connection connection = BDConnectionGenerator.getConnectionInstance();
		List<Document> documents = new ArrayList<Document>();

		try {

			getAllDocument  = (getAllDocument == null) ? connection.prepareStatement("Select  * from document left join emprunt on document.IdDoc = emprunt.IdDoc_Emprunter") : getAllDocument;



			ResultSet res = getAllDocument.executeQuery();
			//			System.out.println("+++++++++++++++++++++++++++++++++++" +  res.next() + "++++++++++++++++++++++++++++++++++++++");


			while(res.next()) {

				String nom = res.getString("NomDoc");
				String type = res.getString("TypeDoc");
				int id = res.getInt("IdDoc");
				Date creation = res.getDate("DateCreation");
				boolean status =  res.getString("IdDoc_Emprunter") == null;
				String imageURL = res.getString("ImageURL");
				
				
				int idEmprunteur = res.getInt("IdUser");
				//Si l'iD de l'emprunterur etait null
				if(res.wasNull()) {
					idEmprunteur = -1;
				}


				try {

					documents.add((Document)Class.forName("persistance."+type).getDeclaredConstructor(Integer.class,String.class,Date.class,Boolean.class,String.class,Integer.class).newInstance(id,nom,creation,status,imageURL,idEmprunteur));

				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}

			}




		} catch (SQLException e) {
			e.printStackTrace();
		}


		return documents;
	}

}
