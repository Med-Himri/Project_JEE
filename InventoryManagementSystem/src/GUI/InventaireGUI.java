package GUI;

import RMI.GestionInventaire;
import produit.Produit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InventaireGUI extends JFrame {
    private final GestionInventaire gestionInventaire;

    public InventaireGUI(GestionInventaire gestionInventaire) {
        this.gestionInventaire = gestionInventaire;

        // Set FlatLaf theme
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configure the main frame
        setTitle("Système de Gestion d'Inventaire");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Ajouter Produit", createAddProductPanel());
        tabbedPane.addTab("Lister Produits", createListProductsPanel());
        tabbedPane.addTab("Mettre à Jour Produit", createUpdateProductPanel());
        tabbedPane.addTab("Supprimer Produit", createDeleteProductPanel());

        // Add tabs to the frame
        add(tabbedPane);
    }

    private JPanel createAddProductPanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Nom :");
        JTextField nameField = new JTextField(10);
        JLabel categoryLabel = new JLabel("Catégorie :");
        JTextField categoryField = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantité :");
        JTextField quantityField = new JTextField(20);
        JLabel priceLabel = new JLabel("Prix :");
        JTextField priceField = new JTextField(20);

        JButton addButton = new JButton("Ajouter Produit");
        addButton.addActionListener((ActionEvent e) -> {
            try {
                String nom = nameField.getText();
                String categorie = categoryField.getText();
                int quantite = Integer.parseInt(quantityField.getText());
                double prix = Double.parseDouble(priceField.getText());
                Produit produit = new Produit(nom, categorie, quantite, prix);

                if (gestionInventaire.ajouterProduit(produit)) {
                    JOptionPane.showMessageDialog(this, "Produit ajouté avec succès !");
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du produit.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Veuillez vérifier les données saisies.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel).addComponent(nameField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(categoryLabel).addComponent(categoryField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(quantityLabel).addComponent(quantityField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(priceLabel).addComponent(priceField))
                .addComponent(addButton, GroupLayout.Alignment.CENTER)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel).addComponent(nameField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(categoryLabel).addComponent(categoryField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(quantityLabel).addComponent(quantityField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(priceLabel).addComponent(priceField))
                .addComponent(addButton)
        );

        return panel;
    }

    private JPanel createListProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton = new JButton("Rafraîchir");
        refreshButton.addActionListener((ActionEvent e) -> {
            try {
                List<Produit> produits = gestionInventaire.listerTousLesProduits();
                String[][] data = new String[produits.size()][5];
                for (int i = 0; i < produits.size(); i++) {
                    Produit p = produits.get(i);
                    data[i] = new String[]{String.valueOf(p.getId()), p.getNom(), p.getCategorie(),
                            String.valueOf(p.getQuantite()), String.valueOf(p.getPrix())};
                }
                table.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"ID", "Nom", "Catégorie", "Quantité", "Prix"}));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des produits.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createUpdateProductPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel idLabel = new JLabel("ID :");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Nom :");
        JTextField nameField = new JTextField();
        JLabel categoryLabel = new JLabel("Catégorie :");
        JTextField categoryField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantité :");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Prix :");
        JTextField priceField = new JTextField();
        JButton updateButton = new JButton("Mettre à Jour");

        updateButton.addActionListener((ActionEvent e) -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String nom = nameField.getText();
                String categorie = categoryField.getText();
                int quantite = Integer.parseInt(quantityField.getText());
                double prix = Double.parseDouble(priceField.getText());
                Produit produit = new Produit(id, nom, categorie, quantite, prix);

                if (gestionInventaire.mettreAJourProduit(produit)) {
                    JOptionPane.showMessageDialog(this, "Produit mis à jour avec succès !");
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur : Produit introuvable.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(new JLabel());
        panel.add(updateButton);

        return panel;
    }

    private JPanel createDeleteProductPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID :");
        JTextField idField = new JTextField(10);
        JButton deleteButton = new JButton("Supprimer");

        deleteButton.addActionListener((ActionEvent e) -> {
            try {
                int id = Integer.parseInt(idField.getText());
                if (gestionInventaire.supprimerProduit(id)) {
                    JOptionPane.showMessageDialog(this, "Produit supprimé avec succès !");
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur : Produit introuvable.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(deleteButton);

        return panel;
    }

    public static void main(String[] args) {
        try {
            GestionInventaire gestionInventaire = (GestionInventaire) java.rmi.Naming.lookup("rmi://localhost:1099/GestionInventaire");
            SwingUtilities.invokeLater(() -> new InventaireGUI(gestionInventaire).setVisible(true));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion au serveur RMI.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
