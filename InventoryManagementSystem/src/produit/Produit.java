package produit;
import java.io.Serializable;

public class Produit implements Serializable {

    private static final long serialVersionUID = 1L; // Ajoutez cet ID pour éviter des problèmes de compatibilité

    private int id;
    private String nom;
    private String categorie;
    private int quantite;
    private double prix;

    // Constructeurs
    public Produit() {}

    public Produit(String nom, String categorie, int quantite, double prix) {
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Produit(int id, String nom, String categorie, int quantite, double prix) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prix = prix;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    // toString pour un affichage lisible
    @Override
    public String toString() {
        return "Produit [id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", quantite=" + quantite + ", prix=" + prix + "]";
    }
}
