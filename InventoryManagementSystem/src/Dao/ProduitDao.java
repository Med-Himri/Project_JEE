package Dao;
import produit.Produit;
import java.util.List;
//nous cree un interface pour fair les operations CRUD 

public interface ProduitDao {
	//ajoter un produit
	
	boolean ajouterProduit(Produit porduit);
	//mettre a jour un produit
	
	boolean mettreAjourProduit(Produit produit);
	//supprimer un produit
	
	boolean supprimerProduit(int id);
	//rechercher un produit par ID
	
	Produit rechercherProduitParId(int id);
	//rechercher un produit par un nom
	
	List <Produit> rechecherProduitsParNom(String nom);
	//lester tout les produits
	
	List <Produit> listerTousProduits();
}
