package RMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Dao.ProduitDao;
import produit.Produit;

public class GestionInventaireImpl extends UnicastRemoteObject implements GestionInventaire{
	private ProduitDao produitDAO;
	protected GestionInventaireImpl(ProduitDao produitDAO) throws RemoteException {
        super();
        this.produitDAO = produitDAO;
    }
	@Override
	public boolean ajouterProduit(Produit produit) throws RemoteException {
	    try {
	        boolean result = produitDAO.ajouterProduit(produit);
	        if (result) {
	            System.out.println("Produit ajouté avec succès : " + produit);
	        } else {
	            System.err.println("Échec de l'ajout du produit : " + produit);
	        }
	        return result;
	    } catch (Exception e) {
	        System.err.println("Erreur lors de l'ajout du produit : " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}
	@Override
	public boolean mettreAJourProduit(Produit produit) throws RemoteException {
		// TODO Auto-generated method stub
		return produitDAO.mettreAjourProduit(produit);
	}
	@Override
	public boolean supprimerProduit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return produitDAO.supprimerProduit(id);
	}
	@Override
	public Produit rechercherProduitParId(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return produitDAO.rechercherProduitParId(id);
	}
	@Override
	public List<Produit> rechercherProduitsParNom(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		return produitDAO.rechecherProduitsParNom(nom);
	}
	@Override
	public List<Produit> listerTousLesProduits() throws RemoteException {
		// TODO Auto-generated method stub
		return produitDAO.listerTousProduits();
	}
	

}
