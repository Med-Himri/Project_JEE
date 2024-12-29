package RMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import produit.Produit;

public interface GestionInventaire extends Remote{
	boolean ajouterProduit(Produit produit) throws RemoteException;
	
	boolean mettreAJourProduit(Produit produit) throws RemoteException;
	
	boolean supprimerProduit(int id) throws RemoteException;
	
	Produit rechercherProduitParId(int id) throws RemoteException;
	
	List<Produit> rechercherProduitsParNom(String nom) throws RemoteException;
	
    List<Produit> listerTousLesProduits() throws RemoteException;
	

}
