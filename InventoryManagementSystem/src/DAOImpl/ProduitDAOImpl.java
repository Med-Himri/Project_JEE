package DAOImpl;

import produit.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import Dao.ProduitDao;
import produit.Produit;

public class ProduitDAOImpl implements ProduitDao{
	
	private Connection connection;
	

	public ProduitDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	public boolean ajouterProduit(Produit produit) {
	    String sql = "INSERT INTO produits (nom, categorie, quantite, prix) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, produit.getNom());
	        stmt.setString(2, produit.getCategorie());
	        stmt.setInt(3, produit.getQuantite());
	        stmt.setDouble(4, produit.getPrix());

	        int rowsInserted = stmt.executeUpdate();
	        return rowsInserted > 0; // Retourne true si une ligne a été insérée
	    } catch (SQLException e) {
	        e.printStackTrace(); // Pour diagnostiquer l'erreur
	        return false;
	    }
	}


	@Override
	public boolean mettreAjourProduit(Produit produit) {
		// TODO Auto-generated method stub
		String sql ="UPDATE produits SET nom = ?, categorie = 2, quantite = ?, prix= ? WHERE id =?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, produit.getNom());
			stmt.setString(2, produit.getCategorie());
			stmt.setInt(3, produit.getQuantite());
			stmt.setInt(3, produit.getQuantite());
			stmt.setInt(5, produit.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerProduit(int id) {
		// TODO Auto-generated method stub
		 String query = "DELETE FROM produits WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, id);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	@Override
	public Produit rechercherProduitParId(int id) {
	    Produit produit = null;
	    try {
	        String sql = "SELECT * FROM produits WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            produit = new Produit(
	                resultSet.getInt("id"),
	                resultSet.getString("nom"),
	                resultSet.getString("categorie"),
	                resultSet.getInt("quantite"),
	                resultSet.getDouble("prix")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return produit;
	}


	@Override
	public List<Produit> rechecherProduitsParNom(String nom) {
		// TODO Auto-generated method stub
		List<Produit> produits = new ArrayList<>();
		try {
	        String sql = "SELECT * FROM produits WHERE nom LIKE ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, "%" + nom + "%");  // Utilisation du wildcard "%" pour rechercher une partie du nom
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Produit produit = new Produit(
	                resultSet.getInt("id"),
	                resultSet.getString("nom"),
	                resultSet.getString("categorie"),
	                resultSet.getInt("quantite"),
	                resultSet.getDouble("prix")
	            );
	            produits.add(produit);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return produits;
	}

	@Override
	public List<Produit> listerTousProduits() {
		// TODO Auto-generated method stub
		List<Produit> produits = new ArrayList<>();
		String sql = "SELECT * FROM produits";
		try (Statement stmt = connection.createStatement();ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()) {
				produits.add(new Produit(rs.getInt("id"), rs.getString("nom"),rs.getString("categorie"),rs.getInt("quantite"),rs.getDouble("prix")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produits;
	}
	

}
