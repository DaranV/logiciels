package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.Utilisateur;

public class DocumentStatusManager {

	private static PreparedStatement emprunterPreparedStatement;
	private static PreparedStatement retourPreparedStatement;
	private static PreparedStatement retourDocumentPerduPreparedStatement;
	
	
	public static synchronized void emprunter(Document doc, Utilisateur user) throws EmpruntException {
		Connection connection = BDConnectionGenerator.getConnectionInstance();
		
		try {
			emprunterPreparedStatement = (emprunterPreparedStatement == null) ? connection.prepareStatement("Insert into emprunt(IdUser,IdDoc_Emprunter) VALUES(?,?)") : emprunterPreparedStatement;
			
			emprunterPreparedStatement.setInt(1,(Integer) user.data()[2]);
			emprunterPreparedStatement.setInt(2,(Integer) doc.data()[0]);
			
			emprunterPreparedStatement.executeQuery();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			//La base lève une exception quand on emprunte un livre pas disponible
			//Car on a une contrainte UNIQUE sur IdDoc_Emprunter il ne peut donc pas y avoir de doublon 
			throw new EmpruntException();
		}
	}
	
	
	public static synchronized void rendre(Document doc, Utilisateur user) {
		
		Connection connection = BDConnectionGenerator.getConnectionInstance();
		System.out.println("------------------- ON ENTRE -----------------");
		try {
			retourPreparedStatement = (retourPreparedStatement == null) ? connection.prepareStatement("DELETE FROM emprunt WHERE IdUser=? AND IdDoc_Emprunter=?") : retourPreparedStatement;
			
			retourPreparedStatement.setInt(1,(Integer) user.data()[2]);
			retourPreparedStatement.setInt(2,(Integer) doc.data()[0]);
			
			retourPreparedStatement.executeQuery();
			System.out.println("----------- REQUETE FAIT --------------");
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void rendre(Document doc) {
		Connection connection = BDConnectionGenerator.getConnectionInstance();
		System.out.println("------------------- ON ENTRE -----------------");
		try {
			retourDocumentPerduPreparedStatement = (retourDocumentPerduPreparedStatement == null) ? connection.prepareStatement("DELETE FROM emprunt WHERE IdDoc_Emprunter=?") : retourDocumentPerduPreparedStatement;
			
			retourDocumentPerduPreparedStatement.setInt(1,(Integer) doc.data()[0]);
			
			retourDocumentPerduPreparedStatement.executeQuery();
			System.out.println("----------- REQUETE FAIT --------------");
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
