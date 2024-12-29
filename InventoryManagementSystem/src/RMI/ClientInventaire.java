package RMI;

import java.rmi.registry.LocateRegistry;

import java.rmi.registry.Registry;
import java.util.Scanner;

import produit.Produit;

import java.util.List;
import java.rmi.Naming;
public class ClientInventaire {

    public static void main(String[] args) {
    	try {
             // Connexion au serveur RMI
             GestionInventaire gestionInventaire = (GestionInventaire) Naming.lookup("rmi://localhost:1099/GestionInventaire");
             System.out.println("Connexion au serveur réussie !");

             // Menu principal
             Scanner scanner = new Scanner(System.in);
             int choix;

             do {
                 System.out.println("\n--- Menu Gestion d'Inventaire ---");
                 System.out.println("1. Ajouter un produit");
                 System.out.println("2. Mettre à jour un produit");
                 System.out.println("3. Supprimer un produit");
                 System.out.println("4. Rechercher un produit par ID");
                 System.out.println("5. Rechercher des produits par nom");
                 System.out.println("6. Lister tous les produits");
                 System.out.println("0. Quitter");
                 System.out.print("Votre choix : ");
                 choix = scanner.nextInt();
                 scanner.nextLine(); // Consommer la nouvelle ligne

                 switch (choix) {
                     case 1:
                         System.out.print("Nom du produit : ");
                         String nom = scanner.nextLine();
                         System.out.print("Catégorie : ");
                         String categorie = scanner.nextLine();
                         System.out.print("Quantité : ");
                         int quantite = scanner.nextInt();
                         System.out.print("Prix : ");
                         double prix = scanner.nextDouble();

                         Produit produit = new Produit(nom, categorie, quantite, prix);
                         if (gestionInventaire.ajouterProduit(produit)) {
                             System.out.println("Produit ajouté avec succès !");
                         } else {
                             System.out.println("Échec de l'ajout du produit.");
                         }
                         break;

                     case 2:
                         System.out.print("ID du produit à mettre à jour : ");
                         int idMAJ = scanner.nextInt();
                         scanner.nextLine(); // Consommer la nouvelle ligne
                         System.out.print("Nom du produit : ");
                         nom = scanner.nextLine();
                         System.out.print("Catégorie : ");
                         categorie = scanner.nextLine();
                         System.out.print("Quantité : ");
                         quantite = scanner.nextInt();
                         System.out.print("Prix : ");
                         prix = scanner.nextDouble();

                         Produit produitMAJ = new Produit(idMAJ, nom, categorie, quantite, prix);
                         if (gestionInventaire.mettreAJourProduit(produitMAJ)) {
                             System.out.println("Produit mis à jour avec succès !");
                         } else {
                             System.out.println("Échec de la mise à jour du produit.");
                         }
                         break;

                     case 3:
                         System.out.print("ID du produit à supprimer : ");
                         int idSuppression = scanner.nextInt();
                         if (gestionInventaire.supprimerProduit(idSuppression)) {
                             System.out.println("Produit supprimé avec succès !");
                         } else {
                             System.out.println("Échec de la suppression du produit.");
                         }
                         break;

                     case 4:
                         System.out.print("ID du produit à rechercher : ");
                         int idRecherche = scanner.nextInt();
                         Produit produitTrouve = gestionInventaire.rechercherProduitParId(idRecherche);
                         if (produitTrouve != null) {
                             System.out.println("Produit trouvé : " + produitTrouve);
                         } else {
                             System.out.println("Aucun produit trouvé avec cet ID.");
                         }
                         break;

                     case 5:
                         System.out.print("Nom (ou partie du nom) à rechercher : ");
                         String nomRecherche = scanner.nextLine();
                         List<Produit> produitsTrouves = gestionInventaire.rechercherProduitsParNom(nomRecherche);
                         if (!produitsTrouves.isEmpty()) {
                             System.out.println("Produits trouvés :");
                             for (Produit p : produitsTrouves) {
                                 System.out.println(p);
                             }
                         } else {
                             System.out.println("Aucun produit trouvé avec ce nom.");
                         }
                         break;

                     case 6:
                         List<Produit> produits = gestionInventaire.listerTousLesProduits();
                         if (!produits.isEmpty()) {
                             System.out.println("Liste des produits :");
                             for (Produit p : produits) {
                                 System.out.println(p);
                             }
                         } else {
                             System.out.println("Aucun produit dans l'inventaire.");
                         }
                         break;

                     case 0:
                         System.out.println("Fermeture du client.");
                         break;

                     default:
                         System.out.println("Choix invalide. Veuillez réessayer.");
                 }
             } while (choix != 0);

             scanner.close();

         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
