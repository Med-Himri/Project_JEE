package RMI;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.rmi.Naming;
import DAOImpl.ProduitDAOImpl;
import Database.DatabaseConnection;

public class ServeurInventaire {

    public static void main(String[] args) {
        try {
            // Démarrer le registre RMI
        	LocateRegistry.createRegistry(1099);
            System.out.println("Registre RMI démarré.");

            // Créer la connexion à la base de données
            Connection connection = DatabaseConnection.getConnection();

            // Créer l'implémentation RMI
            ProduitDAOImpl produitDAO = new ProduitDAOImpl(connection);
            GestionInventaire gestionInventaire = new GestionInventaireImpl(produitDAO);

            // Enregistrer l'objet distant dans le registre RMI
            Naming.rebind("rmi://localhost:1099/GestionInventaire", gestionInventaire);
            System.out.println("Serveur d'inventaire prêt.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
