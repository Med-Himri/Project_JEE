
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	//cree un method pour obtenir une connexion a la base de donnees
	public static Connection getConnection() throws SQLException {
		//url , le mot de passe et l'utilisateur de la pase de donnees
		String url="jdbc:mysql://localhost:3307/inventaire";
		String user="root";
		String password ="";
		
		try {
			// ici nous chargement driver JDBC c'est il ya un erreur affichier un message
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC non trouvé.");
			e.printStackTrace();
		}
		// en va retourne une connexion à la base
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void main (String[] args) {
		//un method pour test de connexion 
		try (Connection c =getConnection()){
			if (c != null) {
				System.out.println("Connexion reussie !");
			} else {
				System.out.println("Connexion echouee !");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
